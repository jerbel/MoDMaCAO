package org.modmacao.cm.bash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Link;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.Ipnetworkinterface;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.eclipse.emf.common.util.EList;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.placement.Placementlink;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class BashHelper {
	Properties props;
	
	Resource resource;
	String ipaddress;
	String user;
	String options;
	String keypath;
	String task;
	String softwareComponentPath;
	List<String> softwareComponents;
	
	public BashHelper(Resource resource, String task) {
		loadProperties();
		
		this.resource = resource;
		ipaddress = getIPAddress(this.resource);
		this.user = getProperties().getProperty("user");
		options = null;
		keypath = getProperties().getProperty("private_key_path");
		softwareComponentPath = getProperties().getProperty("bash_soft_comp_path");
		this.task = task;
		softwareComponents = getSoftwareComponents(resource);
		
		if (ipaddress.equals("127.0.0.1")) {
			options = "--connection=local";
		}
	}
	
	/*
	 * copied
	 */
	public Properties getProperties() {
		if (props == null)
			loadProperties();
		
		return props;
	}
	
	/*
	 * copied
	 */
	private void loadProperties() {
		props = new Properties();
		InputStream input = null;
		
		try {
    		String filename = "bash.properties";
    		
    		// try to load bundle
    		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
    		if (bundle != null) {
    			URL url = FrameworkUtil.getBundle(this.getClass()).getResource(filename);
    			input = url.openConnection().getInputStream();
    		}

    		if (input == null) {
    			// try to read properties without BundleLoader
    			input = this.getClass().getClassLoader().getResourceAsStream(filename);	
    		}
    		
    		props.load(input);
    		
    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally {
        	if(input!=null){
	        	try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	
	/*
	 * copied
	 */
	public String getTitle(Resource resource) {
		if (resource.getTitle() != null)
			return resource.getTitle();
			
		for (AttributeState attribute: resource.getAttributes()) {
			if (attribute.getName().equals("occi.core.title"));
				return attribute.getValue();
		}
		
		return null;
	}
	
	/*
	 * copied
	 */
	public String getIPAddress(Resource resource) {
		EList<Link> links = resource.getLinks();
		Networkinterface networklink = null;
		Placementlink hosting = null;
		String ipaddress = null;

		for (Link link:links) {
			if (link instanceof Placementlink) {
				BashCMTool.LOGGER.info("Found placementlink for " + getTitle(resource));
				hosting = (Placementlink) link;
				break;
			}	
		}
		if (hosting == null) {
			BashCMTool.LOGGER.warn("No hosting found for component " + getTitle(resource) + ". Falling back to localhost.");
			ipaddress = "127.0.0.1";
			return ipaddress;					
		} else {
			Compute target = (Compute) hosting.getTarget();
			links = target.getLinks();

			List<Link> endpointCandidates = new LinkedList<Link>();
			
			for (Link link:links) {
				if (link instanceof Networkinterface) {
					BashCMTool.LOGGER.info("Found network interface for " + target);
					endpointCandidates.add(link);
					for (MixinBase mixin: link.getParts()) {
						if (mixin instanceof Ansibleendpoint) {
							BashCMTool.LOGGER.info("Found explicitly specified Ansible endpoint for " + target);
							networklink = (Networkinterface) link;
							endpointCandidates.clear();
							break;
						}
					}
				}
				if (networklink != null) {
					break;
				}
			}
			
			if (endpointCandidates.size() > 0) {
				networklink = (Networkinterface) endpointCandidates.get(0);
			}
			
			if (networklink == null) {
				BashCMTool.LOGGER.error("No network interface found for " + target);	
			} else {
				// Retrieving object to ensure ip address is correct
				networklink.occiRetrieve();
				List<AttributeState> attributes  = new LinkedList<AttributeState>();
				attributes.addAll(networklink.getAttributes());
				for (MixinBase base: networklink.getParts()) {
					if (base instanceof Ipnetworkinterface) {
						ipaddress = ((Ipnetworkinterface) base).getOcciNetworkinterfaceAddress();
					}
				}
				
//				for (AttributeState attribute: attributes ) {
//					LOGGER.debug(attribute.getName() + ":" + attribute.getValue());
//					if (attribute.getName().equals("occi.networkinterface.address")) {
//						LOGGER.info("Found IP address for " + networklink);
//						ipaddress = attribute.getValue();
//						LOGGER.info("IP address is " + ipaddress);
//						break;
//					}
//				}
			}
		}

		return ipaddress;
	}
	
	private List<String> getSoftwareComponents(Resource resource) {
		List<String> softwareComponents = new ArrayList<String>();
		for (MixinBase mixin : resource.getParts()) {
			BashCMTool.LOGGER.debug("Mixin has schema: " + mixin.getMixin().getScheme());
			if (mixin.getMixin().getScheme().matches(".*(schemas\\.modmacao\\.org).*") || mixin instanceof modmacao.Component){
				BashCMTool.LOGGER.info("Found mixin " + mixin.getMixin().getName());
				softwareComponents.add(mixin.getMixin().getName().toLowerCase());
			}
		}
		return softwareComponents;
	}
	
	public BashReturnState executeSoftwareComponents() throws IOException, InterruptedException{
//		Process process = null;
		String message = null;
		
		BashCMTool.LOGGER.info("Executing software component " + softwareComponents + " with task " + task + " on host " + ipaddress + " with user " + user + ".");
		BashCMTool.LOGGER.debug("executeSoftwareComponents() debug message");
		
		try {
			if(softwareComponents.isEmpty())
				return new BashReturnState(0, message);;
				
			JSch jsch = new JSch();
			JSch.setConfig("StrictHostKeyChecking", "no");
			jsch.addIdentity(keypath);
			
			Session session = jsch.getSession(user, ipaddress);
			session.connect();
			
			Channel channel = session.openChannel("exec");
			String command = getCommand(softwareComponentPath + "/" + softwareComponents.get(0) + "/" + task);
			((ChannelExec)channel).setCommand(command);
			channel.connect();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			String line;
//		System.out.println("Input Stream:");
			while ((line = bufferedReader.readLine()) != null)
				message += line + "\n";
		} catch (JSchException e) {
			e.printStackTrace();
			return new BashReturnState(-1, message);
		}
		
//		if(options == null)
//			process = new ProcessBuilder("ssh","-i",keypath,user+"@"+ipaddress,"<",this.getProperties().getProperty("bash_soft_comp_path")+"/"+softwareComponents.get(0)+"/"+task).start();
//		else {
//			process = new ProcessBuilder("ssh","-i",keypath,user+"@"+ipaddress,"<",this.getProperties().getProperty("bash_soft_comp_path")+"/"+softwareComponents.get(0)+"/"+task, options).start();
//		}
//		
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(new BufferedReader(new InputStreamReader(process.getInputStream()))
//					  .lines().collect(Collectors.joining(System.lineSeparator())));
//		
//		process.waitFor();
//				
//		message = buffer.toString();
		
		BashCMTool.LOGGER.info("Executing software component " + softwareComponents + " with task " + task + " on host " + ipaddress + " with user " + user + ".");
		
		return new BashReturnState(0, message);
	}
	
	private static String getCommand(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	if(!line.equals("#!/bin/bash") & !line.equals("")) {
			        sb.append(line);
			        sb.append(";");
		    	}
		        line = br.readLine();
		    }
		    return sb.toString();
		} finally {
		    br.close();
		}
	}
}

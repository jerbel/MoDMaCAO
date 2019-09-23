package org.modmacao.cm.bash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Entity;
import org.eclipse.cmf.occi.core.Link;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.Ipnetworkinterface;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.eclipse.emf.common.util.EList;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.cm.ansible.VariablesGenerator;
import org.modmacao.occi.platform.Component;
import org.modmacao.placement.Placementlink;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class BashHelper {
	
	static final String VAR_FILE_NAME = "/home/ubuntu/martserverbashproject/vars.yml";
	static final String EXTEND_VAR_FILE_NAME = "/home/ubuntu/martserverbashproject/vars2.yml";
	static final String REMOTE_SERVER_VAR_FILE_DESTINATION = "";
	static final String LOCALHOST_IP = "127.0.0.1";
	
	Properties props;
	
	Resource resource;
	String ipaddress;
	String user;
	String keypath;
	String task;
	String softwareComponentPath;
	List<String> softwareComponents;
	String propertiesFilePath;
	
	public BashHelper(Resource resource, String task, String propertiesFilePath) {
		this.propertiesFilePath = propertiesFilePath;
		loadProperties();
		
		this.resource = resource;
		ipaddress = getIPAddress(this.resource);
		this.user = getProperties().getProperty("user");
		keypath = getProperties().getProperty("private_key_path");
		softwareComponentPath = getProperties().getProperty("bash_soft_comp_path");
		this.task = task;
		softwareComponents = getSoftwareComponents(resource);
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
    		String filename = propertiesFilePath;
    		
    		BashCMTool.LOGGER.info("Try to load properties from " + propertiesFilePath);
    		
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
    		
    		BashCMTool.LOGGER.info("Loaded InputStream: " + input);
    		
    		props.load(input);
    		
    		BashCMTool.LOGGER.info("Loaded properties: " + props);
    		
    	} catch (IOException ex) {
//    		ex.printStackTrace();
    		BashCMTool.LOGGER.error("IOException: " + ex);
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
	public static String getTitle(Resource resource) {
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
	public static String getIPAddress(Resource resource) {
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
			ipaddress = LOCALHOST_IP;
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
//				networklink.occiRetrieve();
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
		if(ipaddress == null)
			BashCMTool.LOGGER.error("No IP address found for " + resource);
		else
			BashCMTool.LOGGER.info("Loaded ipaddress: " + ipaddress);

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
	
	
	/**
	 * Creates a ssh connection and runs the commands contained in the @softwareComponents.get(0) on the remote server.
	 * @return
	 */
	public BashReturnState executeSoftwareComponents() {
		String message = "";
		
		try {
			if(softwareComponents.isEmpty()) {
				BashCMTool.LOGGER.error("Resource " + resource + " has no software components!");
				return new BashReturnState(0, message);
			}
			
			if(ipaddress == null) {
				BashCMTool.LOGGER.error("Resource " + resource + " has no IP address!");
				return new BashReturnState(0, message);
			}
			
			createVariableFile(Paths.get(VAR_FILE_NAME), resource);
			createExtendedVariableFile(Paths.get("."), resource);
				
			JSch jsch = new JSch();
			JSch.setConfig("StrictHostKeyChecking", "no");
			jsch.addIdentity(keypath);
			
			Session session = jsch.getSession(user, ipaddress);
			
			session.connect();
			
			Channel channel = session.openChannel("exec");
			
			String command = getCommand(softwareComponentPath + "/" + softwareComponents.get(0) + "/" + task);
			
			transferVarFile(VAR_FILE_NAME);
			transferVarFile(EXTEND_VAR_FILE_NAME);
			
			BashCMTool.LOGGER.info("Command that has to be executed on remote server:\n" + command);
			((ChannelExec)channel).setCommand(command);
			channel.connect();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null)
				message += line + "\n";
		} catch (JSchException e) {
//			e.printStackTrace();
			BashCMTool.LOGGER.error("Error when trying to connect to " + user + "@" + ipaddress,e);
			return new BashReturnState(-1, message);
		} catch (IOException e) {
			BashCMTool.LOGGER.error("Can't read bufferedReader of ssh connection!",e);
		}
		
		return new BashReturnState(0, message);
	}
	
	/*
	 * Loads the content of a file.
	 * Removes the #!/bin/bash line and adds semicolons at the end of the line.
	 * @param path Path of the file which content has to be loaded.
	 * @return Adjusted bash code that can be runned on a shell.
	 */
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
	
	/**
	 * Creates an variables file at the given path from the AttributeStates of a given OCCI Entity.
	 * @param variablefile The Path where this variable file should be created.
	 * @param entity The entity for whichs AttributeStates the variable file should be created.
	 * @return The path where this variable file was created.
	 * @throws IOException
	 */
	public static Path createVariableFile(Path variablefile, Entity entity) {
		BashCMTool.LOGGER.info("Start to create variableFile");
		String lb = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		List<AttributeState> attributes  = new LinkedList<AttributeState>();
		
		// Collect all attribute states
		attributes.addAll(entity.getAttributes());
		for (MixinBase base: entity.getParts()) {
			attributes.addAll(base.getAttributes());
		}
		if (entity instanceof Component) {
			AttributeState modifiedAttribute = OCCIFactory.eINSTANCE.createAttributeState();
			modifiedAttribute.setName("ip_address");
			modifiedAttribute.setValue(getIPAddress((Resource) entity));
			attributes.add(modifiedAttribute);
		}
		
		// If the entity is a Resource, collect all attribute states of the connected Resources
		if (entity instanceof Resource) {
			Resource resource = (Resource) entity;
			for (Link link: resource.getLinks()) {
				Resource target = link.getTarget();
				List<AttributeState> collectedAttributes  = new LinkedList<AttributeState>();
				collectedAttributes.addAll(target.getAttributes());
				for (MixinBase base: target.getParts()) {
					collectedAttributes.addAll(base.getAttributes());
				}
				for (AttributeState attribute: collectedAttributes) {
					AttributeState modifiedAttribute = OCCIFactory.eINSTANCE.createAttributeState();
					modifiedAttribute.setName("id" + getTitle(target).replaceAll("[- ]","_") +  '_' + attribute.getName());
					modifiedAttribute.setValue(attribute.getValue());
					attributes.add(modifiedAttribute);
				}
				if (target instanceof Component) {
					AttributeState modifiedAttribute = OCCIFactory.eINSTANCE.createAttributeState();
					modifiedAttribute.setName("id" + getTitle(target).replaceAll("[- ]","_") + '_' + "ip_address");
					modifiedAttribute.setValue(getIPAddress(target));
					attributes.add(modifiedAttribute);
				}
			}
		}
		
		for (AttributeState attribute: attributes) {
			//  Ansible does not allow variable names with points, so we replace them with underscores
			String name = attribute.getName().replace('.', '_');
			sb.append(name);
			sb.append(": ");
			sb.append(attribute.getValue());
			sb.append(lb);
		}
		
		BashCMTool.LOGGER.info("Content of the variable file:\n" + sb.toString());
		
		try {
			FileUtils.writeStringToFile(variablefile.toFile(), sb.toString(), (Charset) null);
		} catch (IOException e) {
			BashCMTool.LOGGER.error("Fail to save variables file!", e);
		}
		
		return variablefile;
	}
	
	public Path createExtendedVariableFile(Path variablepath, Entity entity) {
		BashCMTool.LOGGER.info("Start to create ExtendedVariableFile");
		VariablesGenerator gen;
		try {
			gen = new VariablesGenerator(entity, variablepath.toFile(), new ArrayList<String>());
			gen.doGenerate(null);
		} catch (IOException e) {
			BashCMTool.LOGGER.error("Failed to create variable file using VariablesGenerator!", e);
		}
		
		return Paths.get(variablepath.toString(), EXTEND_VAR_FILE_NAME); 
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	private void transferVarFile(String file) {
//		String command = "scp " + "-i " + keypath + " " + file + " " + user + "@" + ipaddress + ":" + REMOTE_SERVER_VAR_FILE_DESTINATION;
		Process process;
		try {
			process = new ProcessBuilder("scp", "-i", keypath, file, user + "@" + ipaddress + ":" + REMOTE_SERVER_VAR_FILE_DESTINATION).start();
			BashCMTool.LOGGER.info("Created process: " + process);
			BashCMTool.LOGGER.info("Try to transfer " + file + " to remote Server (" + ipaddress + ")");
			
			StringBuffer buffer = new StringBuffer();
			buffer.append(new BufferedReader(new InputStreamReader(process.getInputStream()))
						  .lines().collect(Collectors.joining(System.lineSeparator())));
			
			process.waitFor();
			
			BashCMTool.LOGGER.info("Buffer message of key transfer:\n" + buffer.toString());
		} catch (IOException e) {
			BashCMTool.LOGGER.error("Fail to transfer variable file!", e);
		} catch (InterruptedException e) {
			BashCMTool.LOGGER.error("Fail to wait for process!", e);
		}
	}
}

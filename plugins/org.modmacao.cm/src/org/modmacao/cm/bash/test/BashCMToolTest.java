package org.modmacao.cm.bash.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Mixin;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.cmf.occi.infrastructure.Ipnetworkinterface;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modmacao.ansibleconfiguration.AnsibleconfigurationFactory;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.cm.bash.BashCMTool;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;
import org.modmacao.occi.platform.impl.PlatformFactoryImpl;
import org.modmacao.placement.PlacementFactory;
import org.modmacao.placement.Placementlink;

import com.jcraft.jsch.*;

import modmacao.impl.ModmacaoFactoryImpl;

public class BashCMToolTest {
	
	BashCMTool cmtool = new BashCMTool();
	Component cut;
	Application aut;
	
	
	@Before
	public void setUP() {
		// Create component
		cut = new PlatformFactoryImpl().createComponent();
		
		cut.setTitle("shard1");
		MixinBase modmacaoComponentMixinBase = new ModmacaoFactoryImpl().createComponent();
		Mixin mixin = OCCIFactory.eINSTANCE.createMixin();
		mixin.setScheme("http://schemas.modmacao.org/modmacao#");
		mixin.setName("example_component");
		modmacaoComponentMixinBase.setMixin(mixin);
				
		cut.getParts().add(modmacaoComponentMixinBase);

		
		// create compute 
		Compute vm1 = InfrastructureFactory.eINSTANCE.createCompute();
		vm1.setTitle("vm1");
			
		Networkinterface nic = InfrastructureFactory.eINSTANCE.createNetworkinterface();
		Ipnetworkinterface ipNetworkMixinBase = InfrastructureFactory.eINSTANCE.createIpnetworkinterface();
	
		Ansibleendpoint ansibleendpoint = AnsibleconfigurationFactory.eINSTANCE.createAnsibleendpoint();
		
		AttributeState ipaddress = OCCIFactory.eINSTANCE.createAttributeState();
		ipaddress.setName("occi.networkinterface.address");
		// we set ip both as AttributeState and member variable, since otherwise we might encounter inconsistencies
		ipaddress.setValue("localhost");
		ipNetworkMixinBase.setOcciNetworkinterfaceAddress("localhost");
		ipNetworkMixinBase.getAttributes().add(ipaddress);
		nic.getParts().add(ipNetworkMixinBase);
		nic.getParts().add(ansibleendpoint);
		
		vm1.getLinks().add(nic);
		
		// set link between vm and component
		Placementlink link = PlacementFactory.eINSTANCE.createPlacementlink();
		link.setTarget(vm1);
		cut.getLinks().add(link);	
	}
	
	@Test
	public void testComponentDeploy() {
		assertEquals(0, cmtool.deploy(cut));
	}
	
//	@Test
	public void testSSHConnection() {
		System.out.println("testSSHConnection:");
		try {
			Runtime runtime = Runtime.getRuntime();
			
			String[] commands = {"ssh","-i","/home/lennart/.ssh/key","lennart"+"@"+"localhost"};
			
			Process process = runtime.exec(commands);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			System.out.println("Input Stream:");
			while ((line = bufferedReader.readLine()) != null)
			     System.out.println(line);
			
			process.waitFor();
			
			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			line = "";
			System.out.println("Error Stream:");
			while ((line = bufferedReader2.readLine()) != null)
			     System.out.println(line);
			
			Assert.assertTrue(process.exitValue() == 0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testSSHExampleComponentExec() {
		System.out.println("testSSHExampleComponentExec:");
		try {
			Runtime runtime = Runtime.getRuntime();
			
			String[] commands = {"ssh","-i","/home/lennart/.ssh/key","lennart"+"@"+"localhost","<","/home/lennart/git/MoDMaCAO/plugins/org.modmacao.all.extensions.example/bash_scripts/example_component/DEPLOY.sh"};
			
			Process process = runtime.exec(commands);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			System.out.println("Input Stream:");
			while ((line = bufferedReader.readLine()) != null)
			     System.out.println(line);
			
			process.waitFor();
			
			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			line = "";
			System.out.println("Error Stream:");
			while ((line = bufferedReader2.readLine()) != null)
			     System.out.println(line);
			
			Assert.assertTrue(process.exitValue() == 0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testJsch() {
		System.out.println("testJsch:");
		try {
		
			JSch jsch = new JSch();
			
			//wirft sonst Fehlermeldung "UnknownHostKey: ... RSA key fingerprin Is ..."
			JSch.setConfig("StrictHostKeyChecking", "no");
			
			jsch.addIdentity("/home/lennart/.ssh/localhost_key");
			
			String user = "lennart";
			String host = "localhost";
		
		
			Session session = jsch.getSession(user, host);
			
			session.connect();
			
			Channel channel = session.openChannel("exec");
			String command = getCommand("/home/lennart/git/MoDMaCAO/plugins/org.modmacao.all.extensions.example/bash_scripts/example_component/DEPLOY.sh");
			System.out.println("Command: " + command);
	        ((ChannelExec)channel).setCommand(command);
	        channel.connect();
	        
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			String line;
			System.out.println("Input Stream:");
			while ((line = bufferedReader.readLine()) != null)
			     System.out.println(line);
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-2");
		}
	}
	
	public String getCommand(String path) throws IOException {
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

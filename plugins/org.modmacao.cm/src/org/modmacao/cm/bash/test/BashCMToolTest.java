package org.modmacao.cm.bash.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import com.jcraft.jsch.*;

public class BashCMToolTest {
	
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
	
	@Test
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

package org.modmacao.cm.ansible.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class DockerHostEnvTest {
	
	final String DOCKER_HOST = "asd";

	@Test
	public void test() throws IOException, InterruptedException {
		String dockerEnvCommand = "/usr/local/bin/docker-machine ls && /usr/local/bin/docker-machine env " + DOCKER_HOST + " && eval $(/usr/local/bin/docker-machine env DefaultJUnitMachine) && /usr/local/bin/docker-machine ls";
		
		String[] dockerCommandArray = {"bash","-c", dockerEnvCommand};
		
		Process process = new ProcessBuilder(dockerCommandArray).start();
		
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(new BufferedReader(new InputStreamReader(process.getInputStream()))
//					  .lines().collect(Collectors.joining(System.lineSeparator())));
		
		process.waitFor();
				
//		System.out.println(buffer.toString());
		
		process.destroy();
		System.out.println(process.exitValue());
	}

}

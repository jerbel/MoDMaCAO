package org.modmacao.cm.simulation;

import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.cmf.occi.core.Resource;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.cm.ansible.AnsibleHelper;
import org.modmacao.cm.ansible.AnsibleReturnState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComponentSimulation extends AbsSimulation {
	
	private Resource res;
	private int simWeight;
	
	public ComponentSimulation(Resource resource) {
		this.res = resource;
		simWeight = getSimWeight(res);
		
	}

	@Override
	public AnsibleReturnState startSimulation() {
		if(simWeight  == 0) {
			System.out.println("State sim only triggered");
		} else if (simWeight == 1) {
			System.out.println("Simulate Execution Time");
			simulateRandomExecutionTime();
		} else if (simWeight == 2) {
			System.out.println("Simulate Load");
		}
		
		return new AnsibleReturnState(0, "Performed dummy simulation for: " + res.getTitle());
		
	}

	private void simulateRandomExecutionTime() {
		int ran = getRandomInt(getSimMin(), getSimMax());
		LOGGER.info("Simulate task on: " + res.getTitle() + " | Running: " + ran + "ms");
		try {
			Thread.sleep(ran);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getSimMin() {
		return Integer.parseInt(new AnsibleHelper().getProperties().getProperty("simulation_min"));
	}
	
	private int getSimMax() {
		return Integer.parseInt(new AnsibleHelper().getProperties().getProperty("simulation_max"));
	}
	
	private static int getRandomInt(int min, int max) {
		return  ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
}

package org.modmacao.cm.simulation;

import java.util.NoSuchElementException;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.modmacao.cm.ansible.AnsibleReturnState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsSimulation {
	static Logger LOGGER = LoggerFactory.getLogger(AbsSimulation.class);
	public abstract AnsibleReturnState startSimulation();
	
	public static int getSimWeight(Resource res) {
		for(AttributeState attr: getSimulationMixin(res).getAttributes()) {
			if(attr.getName().toLowerCase().equals("simulation.weight")) {
				return Integer.parseInt(attr.getValue());
			}
			
		}
		throw new NoSuchElementException("Weight value could not be found"
				+ " in Simulation Mixin of Resource: " + res);
	}
	
	
	public static MixinBase getSimulationMixin(Resource res) {
		for(MixinBase mixB: res.getParts()) {
			if(mixB.getMixin().getTerm().equals("simulation")) {
				return mixB;
			}
		}
		return null;
	}

}

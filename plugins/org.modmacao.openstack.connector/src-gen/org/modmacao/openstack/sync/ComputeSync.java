package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.ComputeStatus;
import org.modmacao.openstack.connector.OpenStackHelper;
import org.occiware.mart.server.exception.ConfigurationException;
import org.occiware.mart.server.model.ConfigurationManager;
import org.occiware.mart.server.model.EntityManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.Server.Status;

import openstackruntime.Runtimeid;

public class ComputeSync extends AbsSync {

	public ComputeSync(OSClient<?> os) {
		this.os = os;
	}

	public void sync() {
		List<? extends Server> sList = os.compute().servers().list();
		addMissingServer(sList);
		removeNonExistentComputeNodes(sList);
	}
	
	private void removeNonExistentComputeNodes(List<? extends Server> sList) {
		List<String> toRemove = new ArrayList<String>();
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof Compute) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(serveridExistsInCloud(rid, sList) == false) {
							toRemove.add(res.getLocation());
						}
					}
				}
			}
		}
		
		for(String str: toRemove) {
			try {
				LOGGER.info("Removing: " + str);
				EntityManager.removeOrDissociateFromConfiguration(str, "anonymous");
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addMissingServer(List<? extends Server> sList) {
		for(Server s: sList) {
			if(isInRuntimeModel(s.getId()) == false) {
				//Dont add resource to model while connector is still running
				if(s.getStatus().equals(Status.BUILD) == false) {				
					LOGGER.info("Add missing Server to runtime model: "+ s);
					addServerToRuntimeModel(s);
				}
			}
		}
	}

	private  boolean serveridExistsInCloud(Runtimeid rid, List<? extends Server> sList) {
		for(Server s: sList) {
			if(rid.getOpenstackRuntimeId().equals(s.getId())) {
				return true;
			}
		}
		return false;
	}
	
	private  void addServerToRuntimeModel(Server s) {
		Flavor flav = s.getFlavor();
		Compute comp = ifac.createCompute();
		comp.setTitle(s.getName());
		adjustState(comp, s);
		appendRid(comp, s.getId());
		comp.setOcciComputeCores(flav.getVcpus());
		comp.setOcciComputeHostname(s.getName());
		comp.setOcciComputeMemory((float) flav.getRam());
		
		if(os.compute().servers().get(s.getId()) != null) {
			addEntityToRuntimeModel(comp);
		}
	}


	private void adjustState(Compute comp, Server s) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.compute.state");
		if(s.getStatus().value().equals("active")) {
			comp.setOcciComputeState(ComputeStatus.ACTIVE);
			state.setValue("active");
		} else {
			comp.setOcciComputeState(ComputeStatus.INACTIVE);
			state.setValue("inactive");
		}
		comp.getAttributes().add(state);
	}
}

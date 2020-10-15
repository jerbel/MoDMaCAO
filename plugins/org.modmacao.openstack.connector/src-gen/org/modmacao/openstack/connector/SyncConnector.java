/*******************************************************************************
 * Copyright (c) 2019 University of Goettingen.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     - Johannes Erbel <johannes.erbel@cs.uni-goettingen.de>
 *******************************************************************************/

package org.modmacao.openstack.connector;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.Server.Status;
import org.openstack4j.model.network.Network;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openstackruntime.OpenstackruntimeFactory;
import openstackruntime.Runtimeid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.Entity;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.ComputeStatus;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.cmf.occi.infrastructure.NetworkStatus;
import org.occiware.mart.server.exception.ConfigurationException;
import org.occiware.mart.server.model.ConfigurationManager;
import org.occiware.mart.server.model.EntityManager;

/**
 * Slave objects
 *
 * @author erbel
 *
 */
public class PostConstructor implements Runnable {
	private static final long FIVE_SECONDS = 5000;
	static Logger LOGGER = LoggerFactory.getLogger(ComputeConnector.class);
	private OpenStackHelper helper = OpenStackHelper.getInstance();
	public static Boolean running = false;

	public PostConstructor() {
		LOGGER.info("PostConstructor Created");
	}

	@Override
	public void run() {
		String endpoint = helper.getProperties().getProperty("openstack_endpoint");
		String username = helper.getProperties().getProperty("openstack_username");
		String tenant = helper.getProperties().getProperty("openstack_tenant");
		String password = helper.getProperties().getProperty("openstack_password");
		if(running == false) {
			running = true;
			while(true){
				OSClient<?> os = OSFactory.builderV2()
						.endpoint(endpoint)
			            .credentials(username, password)
			            .tenantName(tenant)
			            .authenticate();
				LOGGER.info("Compute Node Synchronization");
				try {
					Thread.sleep(FIVE_SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				List<? extends Server> sList = os.compute().servers().list();
				addMissingServer(sList);
				removeNonExistentComputeNodes(sList);
				
				List<? extends Network> nList = os.networking().network().list();
				addMissingNetworks(nList);
				removeNonExistentNetworkNodes(nList);
				
				//addMissingNetworkinterfaces(sList);
				//removeNonExistentNetworkinterfaces(sList);
				
				
				//LOGGER.info("Network: " + os.networking().network().list());
			}
		}
		running = false;
	}

	private void removeNonExistentNetworkNodes(List<? extends Network> nList) {
		List<String> toRemove = new ArrayList<String>();
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof Compute) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(networkidExistsInCloud(rid, nList) == false) {
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

	private void addMissingNetworks(List<? extends Network> nList) {
		for(Network n: nList) {
			if(isInRuntimeModel(n.getId()) == false) {
				LOGGER.info("Adding missing Network to runtime model: "+ n);
				addNetworkToRuntimeModel(n);
			}
		}
		
	}

	private void addNetworkToRuntimeModel(Network n) {
		OCCIFactory cfac = OCCIFactory.eINSTANCE;
		InfrastructureFactory fac = InfrastructureFactory.eINSTANCE;
		org.eclipse.cmf.occi.infrastructure.Network nw = fac.createNetwork();
		String title = n.getName();
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.network.state");
		if(n.isAdminStateUp()) {
			nw.setOcciNetworkState(NetworkStatus.ACTIVE);
			state.setValue("active");
		} else {
			nw.setOcciNetworkState(NetworkStatus.INACTIVE);
			state.setValue("inactive");
		}
		
		
		
		
		
		String uuid = nw.getId();
		String kind = nw.getKind().getScheme() + nw.getKind().getTerm();
		String summary = "Created by postconstructor!";
		
		OpenstackruntimeFactory oFac = OpenstackruntimeFactory.eINSTANCE;
		Runtimeid rid = oFac.createRuntimeid();
		rid.setOpenstackRuntimeId(nw.getId());
		AttributeState as = cfac.createAttributeState();
		as.setName("openstack.runtime.id");
		as.setValue(nw.getId());
		rid.getAttributes().add(as);
		
		nw.getParts().add(rid);
		
		List<String> mixins = getMixinNames(nw);
		Map<String, String> attributes = getAttributeMap(nw);
		
		
		String location = "/" + nw.getKind().getTerm() + "/urn:uuid:" + uuid + "/";
		String owner = "anonymous";

		try {
			EntityManager.addResourceToConfiguration(uuid, title, summary, kind, mixins, attributes, location, owner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	private boolean serveridExistsInCloud(Runtimeid rid, List<? extends Server> sList) {
		for(Server s: sList) {
			if(rid.getOpenstackRuntimeId().equals(s.getId())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean networkidExistsInCloud(Runtimeid rid, List<? extends Network> nList) {
		for(Network n: nList) {
			if(rid.getOpenstackRuntimeId().equals(n.getId())) {
				return true;
			}
		}
		return false;
	}

	private void addServerToRuntimeModel(Server s) {
		OCCIFactory cfac = OCCIFactory.eINSTANCE;
		InfrastructureFactory fac = InfrastructureFactory.eINSTANCE;
		Compute comp = fac.createCompute();
		String title = s.getName();
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.compute.state");
		if(s.getStatus().value().equals("active")) {
			comp.setOcciComputeState(ComputeStatus.ACTIVE);
			state.setValue("active");
		} else {
			comp.setOcciComputeState(ComputeStatus.INACTIVE);
			state.setValue("inactive");
		}
		
		
		
		
		
		String uuid = comp.getId();
		String kind = comp.getKind().getScheme() + comp.getKind().getTerm();
		String summary = "Created by postconstructor!";
		
		OpenstackruntimeFactory oFac = OpenstackruntimeFactory.eINSTANCE;
		Runtimeid rid = oFac.createRuntimeid();
		rid.setOpenstackRuntimeId(s.getId());
		AttributeState as = cfac.createAttributeState();
		as.setName("openstack.runtime.id");
		as.setValue(s.getId());
		rid.getAttributes().add(as);
		
		comp.getParts().add(rid);
		
		List<String> mixins = getMixinNames(comp);
		Map<String, String> attributes = getAttributeMap(comp);
		
		
		String location = "/" + comp.getKind().getTerm() + "/urn:uuid:" + uuid + "/";
		String owner = "anonymous";

		try {
			EntityManager.addResourceToConfiguration(uuid, title, summary, kind, mixins, attributes, location, owner);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isInRuntimeModel(String id) {
		if(isBlacklisted(id)) {
			return true;
		}
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof Compute) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(rid.getOpenstackRuntimeId().equals(id)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	
	private boolean isBlacklisted(String id) {
		if(id.equals("5a3ba352-9341-4d52-b7f0-96c75f9ceffa")
				|| id.equals("4d74302f-5cef-4bcd-ab12-5ac2455a68ab")
				|| id.equals("75a4639e-9ce7-4058-b859-8a711b0e2e7b")
				|| id.equals("82ef5768-0cac-4f77-920e-c871fbfd7f3f")
				|| id.equals("7603cefe-affb-4765-8a81-be74d9f7aebe")) {
				return true;
		} else {
			return false;
		}
	}

	private Map<String, String> getAttributeMap(Entity ent) {
		Map<String, String> map = new HashMap<>();
		for (AttributeState attr : ent.getAttributes()) {
			map.put(attr.getName(), attr.getValue());
		}
		for (MixinBase mixB : ent.getParts()) {
			for (AttributeState attr : mixB.getAttributes()) {
				map.put(attr.getName(), attr.getValue());
			}
		}
		return map;
	}

	private List<String> getMixinNames(Entity ent) {
		List<String> list = new ArrayList<>();
		for (MixinBase mixB : ent.getParts()) {
			list.add(mixB.getMixin().getScheme().toLowerCase() + mixB.getMixin().getTerm());
		}
		return list;
	}
}

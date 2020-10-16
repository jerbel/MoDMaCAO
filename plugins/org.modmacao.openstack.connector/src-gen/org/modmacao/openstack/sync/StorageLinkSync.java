package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.Link;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Ipnetworkinterface;
import org.eclipse.cmf.occi.infrastructure.NetworkInterfaceStatus;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.eclipse.cmf.occi.infrastructure.Storagelink;
import org.occiware.mart.server.model.ConfigurationManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.storage.block.Volume;

import openstackruntime.Runtimeid;

public class StorageLinkSync extends AbsSync {
	public StorageLinkSync(OSClient<?> os) {
		this.os = os;
	}

	public void sync() {
		List<? extends Volume> vList = filterTenant(os.blockStorage().volumes().list(), TENANTID);
		for(Volume v: vList) {
			addMissingStorageLinks(v);
			removeNonExistentStorageLinks(v);
		}
	}

	private void removeNonExistentStorageLinks(Volume v) {
			List<String> toRemove = new ArrayList<String>();
			Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
			for(Resource res: config.getResources()) {
				if(res instanceof org.eclipse.cmf.occi.infrastructure.Compute) {
					for(Link l: res.getLinks()) {
						if(l instanceof Storagelink) {
							for(MixinBase mixB: l.getParts()) {
								if(mixB instanceof Runtimeid) {
									Runtimeid rid = (Runtimeid) mixB;
									if(os.networking().port().get(rid.getOpenstackRuntimeId()) == null) {
										toRemove.add(l.getLocation());
									}
								}
							}
						}
					}
				}
			}
		removeFromRuntimeModel(toRemove);	
	}

	private void addMissingStorageLinks(Volume v) {
		//atachaments
		/*
		for(Port p: getNetworkPorts(n)) {
			if(isInRuntimeModel(p.getId()) == false){
				if(isInRuntimeModel(p.getDeviceId())
						&& isInRuntimeModel(p.getNetworkId())){
						addNWIToRuntimeModel(p);
				}
			}
		}
		*/
	}

	private List<? extends Volume> filterTenant(List<? extends Volume> list, String tenantid) {
		List<Volume> vList = new ArrayList<>();
		for(Volume v : list) {
			if(v.getTenantId().equals(tenantid)) {
				vList.add(v);
			}
		}
		return vList;
	}
	
	private List<Port> getNetworkPorts(Network n) {
		List<Port> pList = new ArrayList<>();
		for(Port p: os.networking().port().list()) {
			if(p.getNetworkId().equals(n.getId())) {
				pList.add(p);
			}
		}
		return pList;
	}
	
	private void addNWIToRuntimeModel(Port p) {
		org.eclipse.cmf.occi.infrastructure.Networkinterface nwi = ifac.createNetworkinterface();
		nwi.setTitle(p.getName());
		adjustInterfaceState(nwi, p);
		appendRid(nwi, p.getId());
		addIPInterfaceMixin(nwi, p);
		nwi.setOcciNetworkinterfaceMac(p.getMacAddress());
		
		nwi.setSource(getResourceModelRepresentation(p.getDeviceId()));
		nwi.setTarget(getResourceModelRepresentation(p.getNetworkId()));
		
		if(os.networking().port().get(p.getId()) != null) {
			addEntityToRuntimeModel(nwi);
		}
	}
	
	private void addIPInterfaceMixin(Networkinterface nwi, Port p) {
		Ipnetworkinterface ipn = ifac.createIpnetworkinterface();
		for (IP ip: p.getFixedIps()) {
			ipn.setOcciNetworkinterfaceAddress(ip.getIpAddress());
		}
		nwi.getParts().add(ipn);
	}

	private void adjustInterfaceState(Networkinterface nwi, Port p) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.networkinterface.state");
		switch (p.getState()) {
		case ERROR:
			nwi.setOcciNetworkinterfaceState(NetworkInterfaceStatus.ERROR);
			state.setValue("error");
			break;
		case ACTIVE:
			nwi.setOcciNetworkinterfaceState(NetworkInterfaceStatus.ACTIVE);
			state.setValue("active");
			break;
		case BUILD:
		case DOWN:
		case PENDING_CREATE:
		case PENDING_DELETE:
		case PENDING_UPDATE:
		case UNRECOGNIZED:
		default:
			nwi.setOcciNetworkinterfaceState(NetworkInterfaceStatus.INACTIVE);
			state.setValue("inactive");
			break;	
		}
		nwi.getAttributes().add(state);
	}
}

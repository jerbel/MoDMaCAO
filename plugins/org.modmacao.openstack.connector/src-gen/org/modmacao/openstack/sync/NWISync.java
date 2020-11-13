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
import org.occiware.mart.server.model.ConfigurationManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Port;

import openstackruntime.Runtimeid;

public class NWISync extends AbsSync {
	public NWISync(OSClient<?> os) {
		this.os = os;
	}

	public void sync() {
		List<? extends Network> nList = filterTenantAndBlacklisted(os.networking().network().list(), TENANTID);
		for(Network n: nList) {
			addMissingInterfaces(n);
			removeNonExistentInterfaces(n);
		}
	}
	
	private void removeNonExistentInterfaces(Network n) {
			List<String> toRemove = new ArrayList<String>();
			Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
			for(Resource res: config.getResources()) {
				if(res instanceof org.eclipse.cmf.occi.infrastructure.Compute) {
					for(Link l: res.getLinks()) {
						if(l instanceof Networkinterface) {
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

	private void addMissingInterfaces(Network n) {
		for(Port p: getNetworkPorts(n)) {
			if(isInRuntimeModel(p.getId()) == false){
				if(isBlacklisted(p.getDeviceId()) == false
					&& isBlacklisted(p.getNetworkId()) == false) {
						if(isInRuntimeModel(p.getDeviceId())
							&& isInRuntimeModel(p.getNetworkId())){
								addNWIToRuntimeModel(p);
						}
				}
			}
		}
	}

	private List<? extends Network> filterTenantAndBlacklisted(List<? extends Network> list, String tenantid) {
		List<Network> nList = new ArrayList<>();
		for(Network n : list) {
			if(isBlacklisted(n.getId()) == false && n.getTenantId().equals(tenantid)) {
				nList.add(n);
			}
		}
		return nList;
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
		
		//nwi.setSource(getResourceModelRepresentation(p.getDeviceId()));
		//nwi.setTarget(getResourceModelRepresentation(p.getNetworkId()));
		String srcLocation = getResourceModelRepresentation(p.getDeviceId()).getLocation();
		String tarLocation = getResourceModelRepresentation(p.getNetworkId()).getLocation();
		
		if(os.networking().port().get(p.getId()) != null) {
			addLinkToRuntimeModel(nwi, srcLocation, tarLocation);
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

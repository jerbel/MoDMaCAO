package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Allocation;
import org.eclipse.cmf.occi.infrastructure.Ipnetwork;
import org.eclipse.cmf.occi.infrastructure.NetworkStatus;
import org.occiware.mart.server.exception.ConfigurationException;
import org.occiware.mart.server.model.ConfigurationManager;
import org.occiware.mart.server.model.EntityManager;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.SubnetBuilder;

import openstackruntime.Runtimeid;

public class NetworkSync extends AbsSync {

	public NetworkSync(OSClient<?> os) {
		this.os = os;
	}

	public void sync() {
		//Map<String, String> filter = new HashMap<String, String>();
		List<? extends Network> nList = filterTenant(os.networking().network().list(), TENANTID);
		addMissingNetworks(nList);
		removeNonExistentNetworkNodes(nList);
	}
	
	private List<? extends Network> filterTenant(List<? extends Network> list, String tenantid) {
		List<Network> nList = new ArrayList<>();
		for(Network n : list) {
			if(n.getTenantId().equals(tenantid)) {
				nList.add(n);
			}
		}
		return nList;
	}

	private void removeNonExistentNetworkNodes(List<? extends Network> nList) {
		List<String> toRemove = new ArrayList<String>();
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof org.eclipse.cmf.occi.infrastructure.Network) {
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
		
		removeFromRuntimeModel(toRemove);	
	}


	private void addMissingNetworks(List<? extends Network> nList) {
		for(Network n: nList) {
			if(isInRuntimeModel(n.getId()) == false) {
				addNetworkToRuntimeModel(n);
			}
		}
		
	}

	private void addNetworkToRuntimeModel(Network n) {
		org.eclipse.cmf.occi.infrastructure.Network nw = ifac.createNetwork();
		nw.setTitle(n.getName());
		adjustState(nw, n);
		appendRid(nw, n.getId());
		addIPMixin(nw, n);
		
		if(os.networking().network().get(n.getId()) != null) {
			addEntityToRuntimeModel(nw);
		}
	}
	
	private void addIPMixin(org.eclipse.cmf.occi.infrastructure.Network nw, Network n) {
		for(Subnet snw: n.getNeutronSubnets()) {
			Ipnetwork ipn = ifac.createIpnetwork();
			ipn.setOcciNetworkAddress(snw.getCidr());
			LOGGER.info("CIDR: " + snw.getCidr());
			if(snw.isDHCPEnabled()) {
				ipn.setOcciNetworkAllocation(Allocation.DYNAMIC);
			} else {
				ipn.setOcciNetworkAllocation(Allocation.STATIC);
			}
			ipn.setOcciNetworkGateway(snw.getGateway());
			nw.getParts().add(ipn);
		}
	}

	private void adjustState(org.eclipse.cmf.occi.infrastructure.Network nw, Network n) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.network.state");
		if(n.isAdminStateUp()) {
			nw.setOcciNetworkState(NetworkStatus.ACTIVE);
			state.setValue("active");
		} else {
			nw.setOcciNetworkState(NetworkStatus.INACTIVE);
			state.setValue("inactive");
		}	
		nw.getAttributes().add(state);
	}

	private boolean networkidExistsInCloud(Runtimeid rid, List<? extends Network> nList) {
		for(Network n: nList) {
			if(rid.getOpenstackRuntimeId().equals(n.getId())) {
				return true;
			}
		}
		return false;
	}

}

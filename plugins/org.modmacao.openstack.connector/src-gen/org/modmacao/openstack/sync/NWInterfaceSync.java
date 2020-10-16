package org.modmacao.openstack.sync;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Addresses;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;

public class NWInterfaceSync extends AbsSync {

	public NWInterfaceSync(OSClientV2 os) {
		// TODO Auto-generated constructor stub
	}

	public void sync() {
		List<? extends Server> slist = os.compute().servers().list();
		
		
		for(Server s: slist) {
			Addresses addr = s.getAddresses();
			LOGGER.info("Addresses: " + addr);
		}
		
		List<? extends Network> nlist = os.networking().network().list();
		
		for(Network n: nlist) {
			for(Subnet sn: n.getNeutronSubnets()) {
				
			}
		}
		
		//addMissingNetworkinterfaces(sList);
		//removeNonExistentNetworkinterfaces(sList);
	}
	
	

}

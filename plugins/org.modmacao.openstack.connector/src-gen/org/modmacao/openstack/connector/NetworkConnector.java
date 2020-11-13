/**
 * Copyright (c) 2016-2017 Inria
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * - Philippe Merle <philippe.merle@inria.fr>
 * - Faiez Zalila <faiez.zalila@inria.fr>
 * - Fabian Korte <fabian.korte@cs.uni-goettingen.de>
 *
 * Generated at Fri Mar 02 15:56:56 CET 2018 from platform:/plugin/org.eclipse.cmf.occi.infrastructure/model/Infrastructure.occie by org.eclipse.cmf.occi.core.gen.connector
 */
package org.modmacao.openstack.connector;

import java.util.List;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.infrastructure.Allocation;
import org.eclipse.cmf.occi.infrastructure.Ipnetwork;
import org.eclipse.cmf.occi.infrastructure.NetworkStatus;
import org.modmacao.openstack.sync.AbsSync;
import org.modmacao.openstack.sync.Block;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.NetworkBuilder;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.openstack4j.model.network.options.PortListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openstackruntime.OpenstackruntimeFactory;
import openstackruntime.Runtimeid;


/**
 * Connector implementation for the OCCI kind:
 * - scheme: http://schemas.ogf.org/occi/infrastructure#
 * - term: network
 * - title: Network Resource
 */
public class NetworkConnector extends org.eclipse.cmf.occi.infrastructure.impl.NetworkImpl
{
	private OSClientV2 os = null;
	private Network network = null;
	/**
	 * Initialize the logger.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(NetworkConnector.class);

	// Start of user code Networkconnector_constructor
	/**
	 * Constructs a network connector.
	 */
	NetworkConnector()
	{
		LOGGER.debug("Constructor called on " + this);
	}
	// End of user code
	//
	// OCCI CRUD callback operations.
	//
	
	// Start of user code NetworkocciCreate
	/**
	 * Called when this Network instance is completely created.
	 */
	@Override
	public void occiCreate()
	{
		Block b = new Block();
		AbsSync.addBlock(b);
		LOGGER.debug("occiCreate() called on " + this);
		
		os = OpenStackHelper.getInstance().getOSClient();
		// first check if runtime id is present; if yes try to connect to runtime state
		String runtimeID = OpenStackHelper.getInstance().getRuntimeID(this);
		
		if (runtimeID != null){
			network = getRuntimeObject();
			if (network == null) {
				this.setOcciNetworkState(occiNetworkState.ERROR);
				this.setOcciNetworkStateMessage("Runtime id set, but unable to connect to runtime object.");
			} else {
				setNetworkStatus();
			}
			AbsSync.removeBlock(b);
			return;
		}
		
		if (network == null) {
			NetworkBuilder builder = Builders.network();
			builder.adminStateUp(true);
			
			if (this.getTitle() == null) {
				// Check if an attribute state with title is present and set
				// title accordingly
				for (AttributeState attribute: this.getAttributes()) {
					if (attribute.getName().equals("occi.core.title"))
						this.setTitle(attribute.getValue());
				}	
			} 
			builder.name(this.getTitle());
			
			
			
			try {
				LOGGER.info("Building network");
				network = os.networking().network().create(builder.build());

				for (MixinBase mixin: this.getParts()) {
					LOGGER.info("Building sub-network");
					if (mixin instanceof Ipnetwork) {
						Ipnetwork ipn = (Ipnetwork) mixin;
						SubnetBuilder snbuilder = Builders.subnet();
						snbuilder.name(this.getTitle() + "-subnet");
						snbuilder.cidr(ipn.getOcciNetworkAddress());
						snbuilder.ipVersion(IPVersionType.V4);
						snbuilder.networkId(network.getId());
						snbuilder.enableDHCP(true);
						if(ipn.getOcciNetworkGateway() != null 
								&& ipn.getOcciNetworkGateway().equals("") == false) {
							LOGGER.info("Gateway: " + ipn.getOcciNetworkGateway());
							snbuilder.gateway(ipn.getOcciNetworkGateway());
						}
						//Currently only dynamic is supported to ensure compatibility with test models
						ipn.setOcciNetworkAllocation(Allocation.DYNAMIC);
						os.networking().subnet().create(snbuilder.build());
					}
				}
					
				Runtimeid runtimeMixin = OpenstackruntimeFactory.eINSTANCE.createRuntimeid();
				runtimeMixin.setOpenstackRuntimeId(network.getId());

				this.getParts().add(runtimeMixin);
			} catch (Exception e) {
				LOGGER.debug("Problems creating network: " + e.getMessage());
				e.printStackTrace();
			}
		}
		this.occiRetrieve();
		AbsSync.removeBlock(b);
	}
	// End of user code

	// Start of user code Network_occiRetrieve_method
	/**
	 * Called when this Network instance must be retrieved.
	 */
	@Override
	public void occiRetrieve()
	{
		LOGGER.debug("occiRetrieve() called on " + this);
		os = OpenStackHelper.getInstance().getOSClient();
		
		network = getRuntimeObject();
		
		if (network == null) {
			this.setOcciNetworkState(NetworkStatus.ERROR);
			this.setOcciNetworkStateMessage("Unable to retrieve runtime object");
			return;
		}
		
		setNetworkStatus();
		
		this.setOcciNetworkStateMessage("OpenStack Network State: " + network.getStatus());
	}
	// End of user code

	// Start of user code Network_occiUpdate_method
	/**
	 * Called when this Network instance is completely updated.
	 */
	@Override
	public void occiUpdate()
	{
		LOGGER.debug("occiUpdate() called on " + this);
		// TODO: Implement this callback or remove this method.
	}
	// End of user code

	// Start of user code NetworkocciDelete_method
	/**
	 * Called when this Network instance will be deleted.
	 */
	@Override
	public void occiDelete()
	{
		Block b = new Block();
		AbsSync.addBlock(b);
		LOGGER.debug("occiDelete() called on " + this);
		os = OpenStackHelper.getInstance().getOSClient();
		
		network = getRuntimeObject();
		if(network.getId().equals(OpenStackHelper.getInstance().getDefaultNetwork())) {
			LOGGER.warn("DELETE Request for default network detected! Remove network only from model!");
			AbsSync.removeBlock(b);
			return;
		}
		if (network == null) {
			LOGGER.error("Runtime object for network " + this.getTitle() + " not found!");
		}
		else {
			LOGGER.info("Deleting network: " + network.getName() + "(" + network.getId() +")");
			
			
			PortListOptions opt = PortListOptions.create();
			opt = opt.networkId(network.getId());
			List<? extends Port> nwPorts = os.networking().port().list(opt);
			LOGGER.info("Found ports: " + nwPorts);
			
			for(Port p: nwPorts) {
				os.networking().port().delete(p.getId());
			}
			
			
			for(Subnet sn: network.getNeutronSubnets()) {
				opt = opt.networkId(sn.getId());
				List<? extends Port> snwPorts = os.networking().port().list(opt);
				LOGGER.info("Found subnetwork ports: " + snwPorts);
				for(Port p: snwPorts) {
					os.networking().port().delete(p.getId());
				}
			}

			os.networking().network().delete(network.getId());
		}
		OpenStackHelper.getInstance().removeRuntimeID(this);
		
		this.setOcciNetworkState(NetworkStatus.INACTIVE);
		this.setOcciNetworkStateMessage("DELETED");
		AbsSync.removeBlock(b);
	}
	// End of user code

	//
	// Network actions.
	//

	// Start of user code Network_Kind_Up_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.ogf.org/occi/infrastructure/network/action#
     * - term: up
     * - title: Up a network.
	 */
	@Override
	public void up()
	{
		LOGGER.debug("Action up() called on " + this);

		// Network State Machine.
		switch(getOcciNetworkState().getValue()) {

		case NetworkStatus.INACTIVE_VALUE:
			LOGGER.debug("Fire transition(state=inactive, action=\"up\")...");
			// TODO Implement transition(state=inactive, action="up")
			if(true){
				setOcciNetworkState(NetworkStatus.ACTIVE);
			}
			else	
				if(true){
					setOcciNetworkState(NetworkStatus.ERROR);
				}
			break;

		default:
			break;
		}
	}
	// End of user code
	// Start of user code Network_Kind_Down_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.ogf.org/occi/infrastructure/network/action#
     * - term: down
     * - title: Down a network.
	 */
	@Override
	public void down()
	{
		LOGGER.debug("Action down() called on " + this);

		// Network State Machine.
		switch(getOcciNetworkState().getValue()) {
		
		case NetworkStatus.ACTIVE_VALUE:
			LOGGER.debug("Fire transition(state=active, action=\"down\")...");
			// TODO Implement transition(state=active, action="down")
			if(true){
				setOcciNetworkState(NetworkStatus.INACTIVE);
			}
			else	
				if(true){
					setOcciNetworkState(NetworkStatus.ERROR);
				}
			break;

		default:
			break;
		}
	}
	
	private void setNetworkStatus() {
		Network network = getRuntimeObject();		
		switch(network.getStatus()){
		case ERROR:
			this.setOcciNetworkState(NetworkStatus.ERROR);
			break;
		case ACTIVE:
			this.setOcciNetworkState(NetworkStatus.ACTIVE);
			break;
		case BUILD:
		case DOWN:
		case PENDING_CREATE:
		case PENDING_DELETE:
		case PENDING_UPDATE:
		case UNRECOGNIZED:
		default:
			this.setOcciNetworkState(NetworkStatus.INACTIVE);
		
		}
		
	}
	
	private Network getRuntimeObject() {
		String runtimeid = OpenStackHelper.getInstance().getRuntimeID(this);
		if (runtimeid == null) {
			return null;
		}
		network = os.networking().network().get(runtimeid);
		return network;
	}	

	// End of user code
}	

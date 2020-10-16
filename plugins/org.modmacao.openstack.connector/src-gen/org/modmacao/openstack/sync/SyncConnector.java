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

package org.modmacao.openstack.sync;

import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.modmacao.openstack.connector.ComputeConnector;
import org.modmacao.openstack.connector.OpenStackHelper;

/**
 * Slave objects
 *
 * @author erbel
 *
 */
public class SyncConnector implements Runnable {
	private static final long FIVE_SECONDS = 205000;
	static Logger LOGGER = LoggerFactory.getLogger(ComputeConnector.class);
	private OpenStackHelper helper = OpenStackHelper.getInstance();
	public static Boolean running = false;


	public SyncConnector() {
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
				OSClientV2 os = OSFactory.builderV2()
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
				
				try {
					ComputeSync csync = new ComputeSync(os);
					csync.sync();
					
					NetworkSync nsync = new NetworkSync(os);
					nsync.sync();
					
					NWISync nwisync = NWISync(os);
					nwisync.sync();
					
					//StorageSync ssync = new StorageSync(os);
					//ssync.sync();
					
				} catch(Exception e) {
					LOGGER.error("Something went wrong on synchronization process");
					e.printStackTrace();
				}
			}
		}
		running = false;
	}

	private NWISync NWISync(OSClientV2 os) {
		// TODO Auto-generated method stub
		return null;
	}
}

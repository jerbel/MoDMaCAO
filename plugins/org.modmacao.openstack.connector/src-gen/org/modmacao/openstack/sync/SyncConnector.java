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
	static Logger LOGGER = LoggerFactory.getLogger(ComputeConnector.class);
	private static OpenStackHelper helper = OpenStackHelper.getInstance();
	public static Boolean running = false;
	private int sleep;
	private boolean activation;

	public SyncConnector() {
		sleep = getSleepValue();
		activation = getActivationValue();
	}

	private boolean getActivationValue() {
		String act = helper.getProperties().getProperty("openstack_sync_activation");
		if(act.equals("true")) {
			LOGGER.info("	- Openstack Infra Sync Activated!");
			return true;
		}else {
			LOGGER.info("	- Openstack Infra Sync Activated!");
			return false;
		}
	}

	private int getSleepValue() {
		String sleep = helper.getProperties().getProperty("openstack_sync_cycle");
		LOGGER.info("	- Infra Sync Sleep set to: " + sleep);
		if(sleep.matches("(0|[1-9]\\d*)")) {
			return Integer.parseInt(sleep);
		} else {
			return 60000;
		}
			
	}

	@Override
	public void run() {
		if(activation) {
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
					
					LOGGER.info("Start Infra Synchronization");
					try {
						ComputeSync csync = new ComputeSync(os);
						csync.sync();
						
						NetworkSync nsync = new NetworkSync(os);
						nsync.sync();
						
						NWISync nwisync = new NWISync(os);
						nwisync.sync();
						
						StorageSync ssync = new StorageSync(os);
						ssync.sync();
						
						StorageLinkSync slsync = new StorageLinkSync(os);
						slsync.sync();
						
					} catch(Exception e) {
						LOGGER.error("Something went wrong on synchronization process");
						e.printStackTrace();
					}
					LOGGER.info("Finished Infra Synchronization. Sleeping for: " + sleep);
					
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			running = false;
		}
	}
}

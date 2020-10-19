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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	private List<String> blacklist;

	public SyncConnector() {
		sleep = getSleepValue();
		activation = getActivationValue();
		blacklist = getBlacklist();
		AbsSync.BLACKLIST = blacklist;
		
	}

	private List<String> getBlacklist() {
		String blString = helper.getProperties().getProperty("openstack_sync_blacklist");
		List<String> bl = new ArrayList<String>(Arrays.asList(blString.split("\\s*,\\s*")));
		for(String str: bl) {
			LOGGER.info("	- Ignoring Id on Sync: " + str);
		}
		return bl;
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
		return Integer.parseInt(sleep);
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

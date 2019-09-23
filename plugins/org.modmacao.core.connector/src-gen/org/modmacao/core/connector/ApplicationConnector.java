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
 * - Fabian Korte <fabian.korte@uni-goettingen.de>
 *
 * Generated at Fri Jan 12 16:41:42 CET 2018 from platform:/resource/org.modmacao.occi.platform/model/platform.occie by org.eclipse.cmf.occi.core.gen.connector
 */
package org.modmacao.core.connector;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.cmf.occi.core.Link;
import org.modmacao.cm.ConfigurationManagementTool;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.cm.bash.BashCMTool;
import org.modmacao.occi.platform.Component;
import org.modmacao.occi.platform.Componentlink;
import org.modmacao.occi.platform.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Connector implementation for the OCCI kind:
 * - scheme: http://schemas.modmacao.org/occi/platform#
 * - term: application
 * - title: Application
 */
public class ApplicationConnector extends org.modmacao.occi.platform.impl.ApplicationImpl
{
	private ConfigurationManagementTool cmtool = new AnsibleCMTool();
	
	/**
	 * Initialize the logger.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationConnector.class);

	// Start of user code Applicationconnector_constructor
	/**
	 * Constructs a application connector.
	 */
	ApplicationConnector()
	{
		LOGGER.debug("Constructor called on " + this);
		// TODO: Implement this constructor.
	}
	// End of user code
	//
	// OCCI CRUD callback operations.
	//
	
	// Start of user code ApplicationocciCreate
	/**
	 * Called when this Application instance is completely created.
	 */
	@Override
	public void occiCreate()
	{
		LOGGER.debug("occiCreate() called on " + this);
		// TODO: Implement this callback or remove this method.
	}
	// End of user code

	// Start of user code Application_occiRetrieve_method
	/**
	 * Called when this Application instance must be retrieved.
	 */
	@Override
	public void occiRetrieve()
	{
		LOGGER.debug("occiRetrieve() called on " + this);
		// TODO: Implement this callback or remove this method.
	}
	// End of user code

	// Start of user code Application_occiUpdate_method
	/**
	 * Called when this Application instance is completely updated.
	 */
	@Override
	public void occiUpdate()
	{
		LOGGER.debug("occiUpdate() called on " + this);
		// TODO: Implement this callback or remove this method.
	}
	// End of user code

	// Start of user code ApplicationocciDelete_method
	/**
	 * Called when this Application instance will be deleted.
	 */
	@Override
	public void occiDelete()
	{
		LOGGER.debug("occiDelete() called on " + this);
		// TODO: Implement this callback or remove this method.
	}
	// End of user code

	//
	// Application actions.
	//

	// Start of user code Application_Kind_Stop_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.modmacao.org/occi/platform/application/action#
     * - term: stop
     * - title: Stop the application.
	 */
	@Override
	public void stop()
	{
		int status = -1;
		LOGGER.debug("Action stop() called on " + this);

		// Application State Machine.
		switch(getOcciAppState().getValue()) {

		case Status.ACTIVE_VALUE:
			LOGGER.debug("Fire transition(state=active, action=\"stop\")...");
			for (Component component: this.getConnectedComponents()) {
				component.stop();
			}
			status = cmtool.stop(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.INACTIVE))
				setOcciAppState(Status.INACTIVE);
			else
				setOcciAppState(Status.ERROR);	
			break;

		default:
			break;
		}
	}
	// End of user code
	// Start of user code Application_Kind_Start_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.modmacao.org/occi/platform/application/action#
     * - term: start
     * - title: Start the application.
	 */
	@Override
	public void start()
	{
		int status = -1;
		LOGGER.debug("Action start() called on " + this);
		
		// Application State Machine.
		switch(getOcciAppState().getValue()) {

		case Status.INACTIVE_VALUE:
			LOGGER.debug("Fire transition(state=inactive, action=\"start\")...");
			for (Component component: this.getConnectedComponents()) {
				component.start();
			}
			status = cmtool.start(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.ACTIVE))
				setOcciAppState(Status.ACTIVE);
			else
				setOcciAppState(Status.ERROR);	
			break;


		case Status.UNDEPLOYED_VALUE:
			LOGGER.debug("Fire transition(state=undeployed, action=\"start\")...");
			// First deploy components
			for (Component component: this.getConnectedComponents()) {
				component.deploy();
			}
			this.deploy();
			
			// then configure them
			for (Component component: this.getConnectedComponents()) {
				component.configure();
			}
			this.configure();
			
			// then start them
			for (Component component: this.getConnectedComponents()) {
				component.start();
			}
			status = cmtool.start(this);
			
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.ACTIVE))
				setOcciAppState(Status.ACTIVE);
			else
				setOcciAppState(Status.ERROR);
			break;

		default:
			break;
		}
	}
	// End of user code
	// Start of user code Application_Kind_undeploy_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.modmacao.org/occi/platform/application/action#
     * - term: undeploy
     * - title: 
	 */
	@Override
	public void undeploy()
	{
		int status = -1;
		LOGGER.debug("Action undeploy() called on " + this);

		// Application State Machine.
		switch(getOcciAppState().getValue()) {

		case Status.ACTIVE_VALUE:
			LOGGER.debug("Fire transition(state=active, action=\"undeploy\")...");
			for (Component component: this.getConnectedComponents()) {
				component.stop();
			}
			this.stop();
			
			for (Component component: this.getConnectedComponents()) {
				component.undeploy();
			}
			status = cmtool.undeploy(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.UNDEPLOYED))
				setOcciAppState(Status.UNDEPLOYED);
			else
				setOcciAppState(Status.ERROR);
			break;
		case Status.INACTIVE_VALUE:
			LOGGER.debug("Fire transition(state=inactive, action=\"undeploy\")...");
			for (Component component: this.getConnectedComponents()) {
				component.undeploy();
			}
			status = cmtool.undeploy(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.UNDEPLOYED))
				setOcciAppState(Status.UNDEPLOYED);
			else
				setOcciAppState(Status.ERROR);
			break;
		case Status.DEPLOYED_VALUE:
			LOGGER.debug("Fire transition(state=deployed, action=\"undeploy\")...");
			for (Component component: this.getConnectedComponents()) {
				component.undeploy();
			}
			status = cmtool.undeploy(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.UNDEPLOYED))
				setOcciAppState(Status.UNDEPLOYED);
			else
				setOcciAppState(Status.ERROR);
			break;
		case Status.ERROR_VALUE:
			LOGGER.debug("Fire transition(state=error, action=\"undeploy\")...");
			for (Component component: this.getConnectedComponents()) {
				component.undeploy();
			}
			status = cmtool.undeploy(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.UNDEPLOYED))
				setOcciAppState(Status.UNDEPLOYED);
			else
				setOcciAppState(Status.ERROR);
			break;

		default:
			break;
		}
	}
	// End of user code
	// Start of user code Application_Kind_configure_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.modmacao.org/occi/platform/application/action#
     * - term: configure
     * - title: 
	 */
	@Override
	public void configure()
	{
		int status = -1;
		LOGGER.debug("Action configure() called on " + this);

		// Application State Machine.
		switch(getOcciAppState().getValue()) {

		case Status.DEPLOYED_VALUE:
			LOGGER.debug("Fire transition(state=deployed, action=\"configure\")...");
			for (Component component: this.getConnectedComponents()) {
				component.configure();
			}
			
			status = cmtool.configure(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.INACTIVE))
				setOcciAppState(Status.INACTIVE);
			else
				setOcciAppState(Status.ERROR);
			break;

		default:
			break;
		}
	}
	// End of user code
	// Start of user code Application_Kind_deploy_action
	/**
	 * Implement OCCI action:
     * - scheme: http://schemas.modmacao.org/occi/platform/application/action#
     * - term: deploy
     * - title: 
	 */
	@Override
	public void deploy()
	{
		int status = -1;
		LOGGER.debug("Action deploy() called on " + this);

		// Application State Machine.
		switch(getOcciAppState().getValue()) {

		case Status.UNDEPLOYED_VALUE:
			LOGGER.debug("Fire transition(state=undeployed, action=\"deploy\")...");
			for (Component component: this.getConnectedComponents()) {
				component.deploy();
			}
			
			status = cmtool.deploy(this);
			if (status == 0 && assertCompsStatusEquals(getConnectedComponents(), Status.DEPLOYED))
				setOcciAppState(Status.DEPLOYED);
			else
				setOcciAppState(Status.ERROR);
			break;

		default:
			break;
		}
	}
	
	private List<Component> getConnectedComponents() {
		LinkedList<Component> connectedComponents = new LinkedList<Component>();
		for (Link link: this.getLinks()) {
			if (link instanceof Componentlink) {
				connectedComponents.add((Component) link.getTarget());
			}
		}
		return connectedComponents;
		
	}
	
	private boolean assertCompsStatusEquals(List<Component> components, Status status) {
		for (Component component: components) {
			if (component.getOcciComponentState().getValue() != status.getValue()) {
				LOGGER.debug("Missmatching state of component " + component.getTitle() + " detected. "
						+ "Expected " + status.getName() + " but was " 
						+ component.getOcciComponentState().getName() + ".");
				return false;
			}
		}	
		return true;
	}
	
	// end of user code
}	

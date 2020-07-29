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
 *
 * Generated at Fri Jan 12 16:41:42 CET 2018 from platform:/resource/org.modmacao.occi.platform/model/platform.occie by org.eclipse.cmf.occi.core.gen.connector
 */
package org.modmacao.core.connector;

import org.modmacao.occi.platform.App_tpl;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;
import org.modmacao.occi.platform.Componentlink;
import org.modmacao.occi.platform.Database;
import org.modmacao.occi.platform.Databaselink;
import org.modmacao.occi.platform.Res_tpl;

/**
 * Connector EFactory for the OCCI extension:
 * - name: platform
 * - scheme: http://schemas.modmacao.org/occi/platform#
 */
public class ConnectorFactory extends org.modmacao.occi.platform.impl.PlatformFactoryImpl
{
	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: application
	 * - title: Application
	 */
	@Override
	public org.modmacao.occi.platform.Application createApplication() {
		return (Application) new ApplicationConnector();
	}

	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: component
	 * - title: Component
	 */
	@Override
	public org.modmacao.occi.platform.Component createComponent() {
		return (Component) new ComponentConnector();
	}

	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: componentlink
	 * - title: ComponentLink
	 */
	@Override
	public org.modmacao.occi.platform.Componentlink createComponentlink() {
		return (Componentlink) new ComponentlinkConnector();
	}

	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: app_tpl
	 * - title: Application Template
	 */
	@Override
	public org.modmacao.occi.platform.App_tpl createApp_tpl() {
		return (App_tpl) new App_tplConnector();
	}

	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: res_tpl
	 * - title: Resource Template
	 */
	@Override
	public org.modmacao.occi.platform.Res_tpl createRes_tpl() {
		return (Res_tpl) new Res_tplConnector();
	}

	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: database
	 * - title: Database Mixin
	 */
	@Override
	public org.modmacao.occi.platform.Database createDatabase() {
		return (Database) new DatabaseConnector();
	}

	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/occi/platform#
	 * - term: databaselink
	 * - title: Database Link
	 */
	@Override
	public org.modmacao.occi.platform.Databaselink createDatabaselink() {
		return (Databaselink) new DatabaselinkConnector();
	}

}

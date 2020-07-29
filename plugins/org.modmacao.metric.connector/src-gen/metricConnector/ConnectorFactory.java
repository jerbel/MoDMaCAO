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
 * Generated at Tue May 26 19:01:06 CEST 2020 from platform:/resource/metric/model/metric.occie by org.eclipse.cmf.occi.core.gen.connector
 */
package metricConnector;

/**
 * Connector EFactory for the OCCI extension:
 * - name: metric
 * - scheme: http://schemas.modmacao.org/metric#
 */
public class ConnectorFactory extends metric.impl.MetricFactoryImpl
{
	/**
	 * EFactory method for OCCI kind:
	 * - scheme: http://schemas.modmacao.org/metric#
	 * - term: metric
	 * - title: 
	 */
	@Override
	public metric.Metric createMetric() {
		return new MetricConnector();
	}

}

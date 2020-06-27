package org.modmacao.openstack.connector.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openstack4j.openstack.OSFactory;

public class Openstack4JTest {

	@Test
	public void test() {
		OSFactory.builderV2()
		.endpoint("http://controller:5000/v3/")
        .credentials("thiesen", "Wv!69Z")
        .tenantName("bashconnector")
        .authenticate();
	}

}

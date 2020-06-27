package org.modmacao.cm.ansible.test;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.util.Random;

import org.eclipse.cmf.occi.core.Mixin;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.util.OcciRegistry;
import org.eclipse.cmf.occi.docker.Container;
import org.eclipse.cmf.occi.docker.Contains;
import org.eclipse.cmf.occi.docker.Machinevirtualbox;
import org.eclipse.cmf.occi.docker.connector.ContainsConnector;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.junit.Before;
import org.junit.Test;
import org.modmacao.ansibleconfiguration.AnsibleconfigurationFactory;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.occi.platform.Component;
import org.modmacao.occi.platform.impl.PlatformFactoryImpl;
import org.modmacao.placement.PlacementFactory;
import org.modmacao.placement.Placementlink;

import modmacao.impl.ModmacaoFactoryImpl;

public class AnsibleCMToolDockerTest {
	
	public static final String DEFAULT_MACHINE_NAME = "DefaultJUnitMachine";
	
	AnsibleCMTool cmTool = new AnsibleCMTool();
	Machinevirtualbox machinevirtualbox;
	Container container;
	Component component;

	@Before
	public void setIP() {
		setupExtensions();
		
		//machine setup
		machinevirtualbox = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createMachinevirtualbox();
		machinevirtualbox.setName(DEFAULT_MACHINE_NAME);
		
		//container setup
		container = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContainer();
		container.setName(getRandomContainerName());
		
		//connect machine with container using contains link
		Contains contains = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContains();
		contains.setSource(machinevirtualbox);
		container.getRlinks().add(contains);
		
		//adding a networkinterface with a ansibleendpoint to the container
		Networkinterface nic = InfrastructureFactory.eINSTANCE.createNetworkinterface();
		Ansibleendpoint ansibleendpoint = AnsibleconfigurationFactory.eINSTANCE.createAnsibleendpoint();
		nic.getParts().add(ansibleendpoint);
		container.getLinks().add(nic);
		
		//setup component
		component = new PlatformFactoryImpl().createComponent();
		component.setTitle("testComponent");
		MixinBase modmacaoComponentMixinBase = new ModmacaoFactoryImpl().createComponent();
		Mixin mixin = OCCIFactory.eINSTANCE.createMixin();
		mixin.setScheme("http://schemas.modmacao.org/modmacao#");
		mixin.setName("testrole");
		modmacaoComponentMixinBase.setMixin(mixin);
		component.getParts().add(modmacaoComponentMixinBase);
		
		//connect component to container using a placementlink
		Placementlink placementLink = PlacementFactory.eINSTANCE.createPlacementlink();
		placementLink.setTarget(container);
		component.getLinks().add(placementLink);
	}
	
	private String getRandomContainerName() {
		Random random = new Random();
		return "container" + random.nextInt();
	}
	
	private void setupExtensions() {
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/modmacao#",Paths.get("testextensions/modmacao.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/occi/platform#",Paths.get("testextensions/platform.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.ogf.org/occi/infrastructure#",Paths.get("testextensions/Infrastructure.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.ogf.org/occi/core#",Paths.get("testextensions/Core.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/occi/ansible#",Paths.get("testextensions/ansibleconfiguration.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://occiware.org/occi/docker#",Paths.get("/org.eclipse.cmf.occi.docker/model/docker.occie").toString());
	}
	
	@Test
	public void test() {
		machinevirtualbox.occiCreate();
		container.occiCreate();
		container.run();
		cmTool.deploy(component);
	}

}

package org.modmacao.cm.ansible.test;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Mixin;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.OCCIPackage;
import org.eclipse.cmf.occi.core.util.OcciRegistry;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.cmf.occi.infrastructure.InfrastructurePackage;
import org.eclipse.cmf.occi.infrastructure.Ipnetworkinterface;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.junit.Before;
import org.junit.Test;
import org.modmacao.ansibleconfiguration.AnsibleconfigurationFactory;
import org.modmacao.ansibleconfiguration.AnsibleconfigurationPackage;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;
import org.modmacao.occi.platform.PlatformPackage;
import org.modmacao.occi.platform.impl.PlatformFactoryImpl;
import org.modmacao.placement.PlacementFactory;
import org.modmacao.placement.Placementlink;

import modmacao.ModmacaoPackage;
import modmacao.impl.ModmacaoFactoryImpl;

public class AnsibleCMToolTest {
	AnsibleCMTool  cmtool = new AnsibleCMTool();
	Component cut;
	Application aut;
	
	@Before
	public void setUP() {
		setupExtensions();
		// Create component
		cut = new PlatformFactoryImpl().createComponent();
		System.out.println(cut);
		
		cut.setTitle("shard1");
		MixinBase modmacaoComponentMixinBase = new ModmacaoFactoryImpl().createComponent();
		Mixin mixin = OCCIFactory.eINSTANCE.createMixin();
		mixin.setScheme("http://schemas.modmacao.org/modmacao#");
		mixin.setName("testrole");
		modmacaoComponentMixinBase.setMixin(mixin);
				
		cut.getParts().add(modmacaoComponentMixinBase);

		
		// create compute 
		Compute vm1 = InfrastructureFactory.eINSTANCE.createCompute();
		vm1.setTitle("vm1");
			
		Networkinterface nic = InfrastructureFactory.eINSTANCE.createNetworkinterface();
		Ipnetworkinterface ipNetworkMixinBase = InfrastructureFactory.eINSTANCE.createIpnetworkinterface();
	
		Ansibleendpoint ansibleendpoint = AnsibleconfigurationFactory.eINSTANCE.createAnsibleendpoint();
		
		AttributeState ipaddress = OCCIFactory.eINSTANCE.createAttributeState();
		ipaddress.setName("occi.networkinterface.address");
		// we set ip both as AttributeState and member variable, since otherwise we might encounter inconsistencies
		ipaddress.setValue("127.0.0.1");
		ipNetworkMixinBase.setOcciNetworkinterfaceAddress("127.0.0.1");
		ipNetworkMixinBase.getAttributes().add(ipaddress);
		nic.getParts().add(ipNetworkMixinBase);
		nic.getParts().add(ansibleendpoint);
		
		vm1.getLinks().add(nic);
		
		// set link between vm and component
		Placementlink link = PlacementFactory.eINSTANCE.createPlacementlink();
		link.setTarget(vm1);
		cut.getLinks().add(link);	
	}
	
	private void setupExtensions() {
		InfrastructurePackage.eINSTANCE.eClass();
		OCCIPackage.eINSTANCE.eClass();
		PlatformPackage.eINSTANCE.eClass();
		ModmacaoPackage.eINSTANCE.eClass();
		AnsibleconfigurationPackage.eINSTANCE.eClass();

		
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/modmacao#",Paths.get("testextensions/modmacao.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/occi/platform#",Paths.get("testextensions/platform.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.ogf.org/occi/infrastructure#",Paths.get("testextensions/Infrastructure.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.ogf.org/occi/core#",Paths.get("testextensions/Core.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/occi/ansible#",Paths.get("testextensions/ansibleconfiguration.occie").toString());
		
		System.out.println(OcciRegistry.getInstance().getRegisteredExtensions());
	}
	
	@Test
	public void testComponentDeploy() {
		assertEquals(0, cmtool.deploy(cut));
	}
	
//	@Test
//	public void testComponentConfigure() {
//		cut.configure();
//	}
//	
//	@Test
//	public void testComponentStart() {
//		cut.start();
//	}
//	
//	@Test
//	public void testComponentStop() {
//		cut.stop();
//	}
//	
//	@Test
//	public void testComponentUndeploy() {
//		cut.undeploy();
//	}
	
}

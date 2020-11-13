package org.modmacao.cm.ansible.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.cmf.occi.core.Action;
import org.eclipse.cmf.occi.core.Annotation;
import org.eclipse.cmf.occi.core.Attribute;
import org.eclipse.cmf.occi.core.Constraint;
import org.eclipse.cmf.occi.core.Entity;
import org.eclipse.cmf.occi.core.FSM;
import org.eclipse.cmf.occi.core.Kind;
import org.eclipse.cmf.occi.core.Mixin;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.util.OcciRegistry;
import org.eclipse.cmf.occi.docker.Container;
import org.eclipse.cmf.occi.docker.Contains;
import org.eclipse.cmf.occi.docker.Machinevirtualbox;
import org.eclipse.cmf.occi.docker.connector.ContainerConnector;
import org.eclipse.cmf.occi.docker.connector.exceptions.DockerException;
import org.eclipse.cmf.occi.docker.connector.manager.DockerClientManager;
import org.eclipse.cmf.occi.docker.connector.observer.StatsCallBack;
import org.eclipse.cmf.occi.docker.connector.utils.EventCallBack;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.modmacao.ansibleconfiguration.AnsibleconfigurationFactory;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.cm.ansible.VariablesGenerator;
import org.modmacao.occi.platform.Component;
import org.modmacao.occi.platform.impl.PlatformFactoryImpl;
import org.modmacao.placement.PlacementFactory;
import org.modmacao.placement.Placementlink;

import modmacao.impl.ModmacaoFactoryImpl;

public class ContainerIPTest {
	
	public static final String DEFAULT_MACHINE_NAME = "DefaultJUnitMachine";
	public static final String DEFAULT_IMAGE = "ubuntu";

	@Before
	public void setUp() {
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/modmacao#",Paths.get("testextensions/modmacao.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/occi/platform#",Paths.get("testextensions/platform.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.ogf.org/occi/infrastructure#",Paths.get("testextensions/Infrastructure.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.ogf.org/occi/core#",Paths.get("testextensions/Core.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://schemas.modmacao.org/occi/ansible#",Paths.get("testextensions/ansibleconfiguration.occie").toString());
		OcciRegistry.getInstance().registerExtension("http://occiware.org/occi/docker#",Paths.get("/org.eclipse.cmf.occi.docker/model/docker.occie").toString());
	}
	
	private String getRandomContainerName() {
		Random random = new Random();
		return "container" + random.nextInt();
	}
	
	@Test
	public void test() throws DockerException {
		Machinevirtualbox machinevirtualbox = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createMachinevirtualbox();
		machinevirtualbox.setName(DEFAULT_MACHINE_NAME);
		machinevirtualbox.occiCreate();
		
		//container setup
		ContainerConnector container = (ContainerConnector)new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContainer();
		container.setName(getRandomContainerName());
		
		//connect machine with container using contains link
		Contains contains = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContains();
		contains.setSource(machinevirtualbox);
		container.getRlinks().add(contains);
		
		//normally, dockerClientManager would be created when the start action is called on the container
		DockerClientManager dockerClientManager = new DockerClientManager(machinevirtualbox,new EventCallBack(container));
		
		//create Container see ContainerConnector.createContainer()
		dockerClientManager.pullImage(machinevirtualbox,DEFAULT_IMAGE);
		dockerClientManager.createContainer(machinevirtualbox, container);
		
		dockerClientManager.startContainer(machinevirtualbox, container, new StatsCallBack(container));
		
		//dockerClientManager.getContainerIP(container);
		
		dockerClientManager.stopContainer(machinevirtualbox, container);
		dockerClientManager.removeContainer(machinevirtualbox, container);
	}
	
	@Test
	public void testContainerIPRetrieve() {
		Machinevirtualbox machinevirtualbox = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createMachinevirtualbox();
		machinevirtualbox.setName(DEFAULT_MACHINE_NAME);
		machinevirtualbox.occiCreate();
		
		//container setup
		Container container = (ContainerConnector)new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContainer();
		container.setName(getRandomContainerName());
		
		//connect machine with container using contains link
		Contains contains = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContains();
		contains.setSource(machinevirtualbox);
		container.getRlinks().add(contains);
		
		container.occiCreate();
		container.run();
		
		//container.getIpaddress();
	
		container.stop();
		container.occiDelete();
	}

	
	@Test
	public void testVarsGeneration() throws IOException {
		Machinevirtualbox machinevirtualbox = new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createMachinevirtualbox();
		machinevirtualbox.setName(DEFAULT_MACHINE_NAME);
		machinevirtualbox.occiCreate();
		
		//container setup
		Container container = (ContainerConnector)new org.eclipse.cmf.occi.docker.connector.ConnectorFactory().createContainer();
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
		Component component = new PlatformFactoryImpl().createComponent();
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
		placementLink.setKind(new PlacementLinkKindWrapper());
		component.getLinks().add(placementLink);
		
		container.occiCreate();
		container.run();
		
		VariablesGenerator gen = new VariablesGenerator(component, new File("ipTestExtVarsFile"), new ArrayList<String>());
		gen.doGenerate(null);
		
		container.stop();
		container.occiDelete();
	}
	
	public class PlacementLinkKindWrapper implements Kind {
		
		String term = "placementlink";
		
		@Override
		public EList<Action> getActions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EList<Constraint> getConstraints() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FSM getFsm() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setFsm(FSM value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean ActionTermUnicity(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean ConstraintNameUnique(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setName(String value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getTerm() {
			return term;
		}
		
		@Override
		public String getScheme() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setScheme(String value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTitle(String value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public EList<Attribute> getAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean AttributesNameUnique(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean IdentityUnique(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean SchemeEndsWithSharp(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public EList<Annotation> getAnnotations() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EClass eClass() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Resource eResource() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EObject eContainer() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EStructuralFeature eContainingFeature() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EReference eContainmentFeature() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EList<EObject> eContents() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public TreeIterator<EObject> eAllContents() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean eIsProxy() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public EList<EObject> eCrossReferences() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object eGet(EStructuralFeature feature) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object eGet(EStructuralFeature feature, boolean resolve) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void eSet(EStructuralFeature feature, Object newValue) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean eIsSet(EStructuralFeature feature) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void eUnset(EStructuralFeature feature) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EList<Adapter> eAdapters() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean eDeliver() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void eSetDeliver(boolean deliver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void eNotify(Notification notification) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Kind getParent() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setParent(Kind value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public EList<Entity> getEntities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EList<Kind> getSource() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EList<Kind> getTarget() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean occiIsKindOf(Kind kind) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean NoCyclicInheritance(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean AttributesNameNotAlreadyDefinedInParent(DiagnosticChain diagnostics,
				Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean sourceReferenceInvariant(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean CorrectScheme(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean targetReferenceInvariant(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean EntityKindIsRootParent(DiagnosticChain diagnostics, Map<Object, Object> context) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}

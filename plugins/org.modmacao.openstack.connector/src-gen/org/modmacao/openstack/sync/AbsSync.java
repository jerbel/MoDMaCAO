package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.cmf.occi.core.Attribute;
import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.Entity;
import org.eclipse.cmf.occi.core.Kind;
import org.eclipse.cmf.occi.core.Mixin;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.occiware.mart.server.exception.ConfigurationException;
import org.occiware.mart.server.model.ConfigurationManager;
import org.occiware.mart.server.model.EntityManager;
import org.openstack4j.api.OSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openstackruntime.OpenstackruntimeFactory;
import openstackruntime.Runtimeid;

public abstract class AbsSync {
	protected final OCCIFactory cfac = OCCIFactory.eINSTANCE;
	protected final InfrastructureFactory ifac = InfrastructureFactory.eINSTANCE;
	protected final OpenstackruntimeFactory oFac = OpenstackruntimeFactory.eINSTANCE;
	protected static Logger LOGGER = LoggerFactory.getLogger(SyncConnector.class);
	protected OSClient<?> os;
	//TODO: AUtomatic detection of TENANTID!
	protected static final String TENANTID = "7ee7b6a14eb54f5b9b75cc2ea87786df";
	private static List<Block> blocked = new ArrayList<>();

	protected boolean isInRuntimeModel(String id) {
		if(isBlacklisted(id)) {
			return true;
		}
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof Compute ||
					res instanceof org.eclipse.cmf.occi.infrastructure.Network) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(rid.getOpenstackRuntimeId().equals(id)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean isBlacklisted(String id) {
		if(id.equals("5a3ba352-9341-4d52-b7f0-96c75f9ceffa")
				|| id.equals("4d74302f-5cef-4bcd-ab12-5ac2455a68ab")
				|| id.equals("75a4639e-9ce7-4058-b859-8a711b0e2e7b")
				|| id.equals("82ef5768-0cac-4f77-920e-c871fbfd7f3f")
				|| id.equals("7603cefe-affb-4765-8a81-be74d9f7aebe")) {
				return true;
		} else {
			return false;
		}
	}
	
	protected Map<String, String> getAttributeMap(Entity ent) {
		Map<String, String> map = new HashMap<>();
		addAttributesOfEntity(ent, ent.eClass());
		for (AttributeState attr : ent.getAttributes()) {
			map.put(attr.getName(), attr.getValue());
		}
		for (MixinBase mixB : ent.getParts()) {
			addMixinAttributesOfEntity(ent, mixB.eClass());
			for (AttributeState attr : mixB.getAttributes()) {
				map.put(attr.getName(), attr.getValue());
			}
		}
		return map;
	}

	protected List<String> getMixinNames(Entity ent) {
		List<String> list = new ArrayList<>();
		for (MixinBase mixB : ent.getParts()) {
			list.add(mixB.getMixin().getScheme().toLowerCase() + mixB.getMixin().getTerm());
		}
		return list;
	}
	
	protected void appendRid(Entity ent, String id) {
		Runtimeid rid = oFac.createRuntimeid();
		rid.setOpenstackRuntimeId(id);
		AttributeState as = cfac.createAttributeState();
		as.setName("openstack.runtime.id");
		as.setValue(id);
		rid.getAttributes().add(as);
		
		ent.getParts().add(rid);
	}
	
	protected void addEntityToRuntimeModel(Entity ent) {
		String title = ent.getTitle();
		String uuid = ent.getId();
		String kind = ent.getKind().getScheme() + ent.getKind().getTerm();
		String summary = "Created by postconstructor!";
		
		List<String> mixins = getMixinNames(ent);
		Map<String, String> attributes = getAttributeMap(ent);
		
		
		String location = "/" + ent.getKind().getTerm() + "/" + uuid + "/";
		String owner = "anonymous";
		
		//Remove urn:uuid as it is added by the martserver
		String adjustedUUID = uuid.substring(9 ,uuid.length());

		try {
			LOGGER.info("Block: " + blocked);
			LOGGER.info("Block: " + blocked.size());
			if(blocked.isEmpty()) {
				LOGGER.info("Adding entity to runtime model: "+ ent);
				EntityManager.addResourceToConfiguration(adjustedUUID, title, summary, kind, mixins, attributes, location, owner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void removeFromRuntimeModel(List<String> toRemove) {
		for(String str: toRemove) {
			try {
				LOGGER.info("Block: " + blocked);
				LOGGER.info("Block: " + blocked.size());
				if(blocked.isEmpty()) {
					LOGGER.info("Removing: " + str);
					EntityManager.removeOrDissociateFromConfiguration(str, "anonymous");
				}
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void addBlock(Block b) {
		blocked.add(b);
	}
	
	public static void removeBlock(Block b) {
		blocked.remove(b);
	}
	
	
	private static void addMixinAttributesOfEntity(Entity ent, EClass eClass) {
		try {
			if (eClass.getEPackage().getNsURI().equals("http://schemas.ogf.org/occi/core/ecore") == false) {
				for (EStructuralFeature feat : eClass.getEAllStructuralFeatures()) {
					for (MixinBase part : ent.getParts()) {
						if (part.eClass().getName().equals(eClass.getName())) {
							if (part.eGet(feat) != null && feat.getEType() != null
									&& feat.getEType().getEPackage().getNsURI()
											.equals("http://schemas.ogf.org/occi/core/ecore") == false
									&& part.eIsSet(feat)) {
								AttributeState attr = OCCIFactory.eINSTANCE.createAttributeState();
								attr.setName(createAttrName(feat.getName(), part.getMixin()));
								attr.setValue(part.eGet(feat).toString());

								AttributeState toSwitch = null;
								for (AttributeState registAttr : part.getAttributes()) {
									if (registAttr.getName().equals(attr.getName())) {
										toSwitch = registAttr;
									}
								}
								part.getAttributes().remove(toSwitch);

								AttributeState toSwitch2 = null;
								for (AttributeState registAttr : ent.getAttributes()) {
									if (registAttr.getName().equals(attr.getName())) {
										toSwitch2 = registAttr;
									}
								}
								part.getAttributes().remove(toSwitch2);

								part.getAttributes().add(attr);
							}
						}
					}

				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}
	
	private static String createAttrName(String featureName, Kind kind) {
		String modifiedFeatureName = featureName.replaceAll("([A-Z])", ".$1").toLowerCase();
		for (Attribute as : kind.getAttributes()) {
			if (modifiedFeatureName.equals(as.getName())) {
				return modifiedFeatureName;
			}
		}
		for (Attribute as : getAllParentAttributes(kind)) {
			if (modifiedFeatureName.equals(as.getName())) {
				return modifiedFeatureName;
			}
		}
		return featureName;
	}

	private static List<Attribute> getAllParentAttributes(Kind kind) {
		List<Attribute> parentAttributes = new BasicEList<>();
		if (kind.getParent() == null) {
			return parentAttributes;
		} else {
			for (Attribute attr : kind.getParent().getAttributes()) {
				parentAttributes.add(attr);
			}
			parentAttributes.addAll(getAllParentAttributes(kind.getParent()));
		}
		return parentAttributes;
	}
	
	private static List<Attribute> getAllParentAttributes(Mixin mix) {
		List<Attribute> parentAttributes = new BasicEList<>();
		if (mix.getDepends() == null) {
			return parentAttributes;
		} else {
			for (Mixin parentMix : mix.getDepends()) {
				for (Attribute attr : parentMix.getAttributes()) {
					parentAttributes.add(attr);
				}
				parentAttributes.addAll(getAllParentAttributes(parentMix));
			}
		}
		return parentAttributes;
	}

	private static String createAttrName(String featureName, Mixin mix) {
		String modifiedFeatureName = featureName.replaceAll("([A-Z])", ".$1").toLowerCase();
		for (Attribute as : mix.getAttributes()) {
			if (modifiedFeatureName.equals(as.getName())) {
				return modifiedFeatureName;
			}
		}
		for (Attribute as : getAllParentAttributes(mix)) {
			if (modifiedFeatureName.equals(as.getName())) {
				return modifiedFeatureName;
			}
		}
		return featureName;
	}
	
	private static void addAttributesOfEntity(Entity ent, EClass eClass) {
		try {
			if (eClass.getEPackage().getNsURI().equals("http://schemas.ogf.org/occi/core/ecore") == false) {
				for (EStructuralFeature feat : eClass.getEStructuralFeatures()) {
					if (ent.eGet(feat) != null) {
						AttributeState attr = OCCIFactory.eINSTANCE.createAttributeState();
						attr.setName(createAttrName(feat.getName(), ent.getKind()));
						attr.setValue(ent.eGet(feat).toString());
						AttributeState toSwitch = null;
						for (AttributeState registAttr : ent.getAttributes()) {
							if (registAttr.getName().equals(attr.getName())) {
								toSwitch = registAttr;
							}
						}
						ent.getAttributes().remove(toSwitch);
						ent.getAttributes().add(attr);
					}
				}
				// TODO: consider supertypes
				if (eClass.getESuperTypes().size() == 1) {
					addAttributesOfEntity(ent, eClass.getESuperTypes().get(0));
				}
			}
		} catch (RuntimeException e) {
		}
	}
	
}

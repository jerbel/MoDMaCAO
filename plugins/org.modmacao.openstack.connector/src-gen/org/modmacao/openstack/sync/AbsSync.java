package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.cmf.occi.core.Attribute;
import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.Entity;
import org.eclipse.cmf.occi.core.Kind;
import org.eclipse.cmf.occi.core.Link;
import org.eclipse.cmf.occi.core.Mixin;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.OCCIFactory;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.eclipse.cmf.occi.infrastructure.InfrastructureFactory;
import org.eclipse.cmf.occi.infrastructure.Networkinterface;
import org.eclipse.cmf.occi.infrastructure.Storage;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.modmacao.openstack.connector.OpenStackHelper;
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
	private static OpenStackHelper helper = OpenStackHelper.getInstance();
	protected static final String TENANTID = helper.getProperties().getProperty("openstack_tenant_id");
	private static List<Block> blocked = new ArrayList<>();
	public static List<String> BLACKLIST = getBlacklist();

	protected String getRuntimeId(Entity ent) {
		for(MixinBase mixB: ent.getParts()) {
			if(mixB instanceof Runtimeid) {
				return ((Runtimeid) mixB).getOpenstackRuntimeId();
			}
		}
		return "";
	}
	
	private static List<String> getBlacklist() {
		String blString = helper.getProperties().getProperty("openstack_sync_blacklist");
		List<String> bl = new ArrayList<String>(Arrays.asList(blString.split("\\s*,\\s*")));
		for(String str: bl) {
			LOGGER.info("Infra Sync - Ignoring Id on Sync: " + str);
		}
		return bl;
	}
	
	protected boolean isInRuntimeModel(String id) {
		if(isBlacklisted(id)) {
			return true;
		}
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof Compute 
					|| res instanceof org.eclipse.cmf.occi.infrastructure.Network
					|| res instanceof Storage) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(rid.getOpenstackRuntimeId().equals(id)) {
							return true;
						}
					}
				}
				
				for(Link l: res.getLinks()) {
					if(l instanceof Networkinterface) {
						for(MixinBase mixB: l.getParts()) {
							if(mixB instanceof Runtimeid) {
								Runtimeid rid = (Runtimeid) mixB;
								if(rid.getOpenstackRuntimeId().equals(id)) {
									return true;
								}
							}
						}
					}
				}
				
				
			}
		}
		return false;
	}
	
	protected Resource getResourceModelRepresentation(String id) {
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof Compute 
					|| res instanceof org.eclipse.cmf.occi.infrastructure.Network
					|| res instanceof Storage) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(rid.getOpenstackRuntimeId().equals(id)) {
							return res;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected boolean isBlacklisted(String id) {
		if(BLACKLIST.contains(id)) {
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
		String summary = getSummary(ent);
		
		List<String> mixins = getMixinNames(ent);
		Map<String, String> attributes = getAttributeMap(ent);
		
		
		String location = "/" + ent.getKind().getTerm() + "/" + uuid + "/";
		String owner = "anonymous";
		
		//Remove urn:uuid as it is added by the martserver
		String adjustedUUID = uuid.substring(9 ,uuid.length());

		try {
			if(blocked.isEmpty()) {
				LOGGER.info("Adding entity to runtime model: "+ ent);
				if(ent instanceof Resource) {
					EntityManager.addResourceToConfiguration(adjustedUUID, title, summary, kind, mixins, attributes, location, owner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private String getSummary(Entity ent) {
		for(AttributeState as: ent.getAttributes()) {
			if(as.getName().equals("occi.core.summary")) {
				if(as.getValue().equals("") == false) {
					return as.getValue();
				}
				break;
			}
		}
		return "Created by Sync Connector!";
	}

	protected void addLinkToRuntimeModel(Entity ent, String srcLocation, String tarLocation) {
		String title = ent.getTitle();
		String uuid = ent.getId();
		String kind = ent.getKind().getScheme() + ent.getKind().getTerm();
		
		List<String> mixins = getMixinNames(ent);
		Map<String, String> attributes = getAttributeMap(ent);
		
		
		String location = "/" + ent.getKind().getTerm() + "/" + uuid + "/";
		String owner = "anonymous";
		
		//Remove urn:uuid as it is added by the martserver
		String adjustedUUID = uuid.substring(9 ,uuid.length());

		try {
			if(blocked.isEmpty()) {
				LOGGER.info("SYNC: Adding Infra entity to runtime model: "+ ent);
				if (ent instanceof Link) {
					EntityManager.addLinkToConfiguration(adjustedUUID, title, kind, mixins, srcLocation, tarLocation, attributes, location, owner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void removeFromRuntimeModel(List<String> toRemove) {
		for(String str: toRemove) {
			try {
				if(blocked.isEmpty()) {
					LOGGER.info("SYNC: Removing Infra entity from runtime model: " + str);
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

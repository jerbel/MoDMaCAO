package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.Link;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Storagelink;
import org.occiware.mart.server.model.ConfigurationManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeAttachment;

public class StorageLinkSync extends AbsSync {
	public StorageLinkSync(OSClient<?> os) {
		this.os = os;
	}

	public void sync() {
		List<? extends Volume> vList = filterTenantAndBlacklisted(os.blockStorage().volumes().list(), TENANTID);
		for(Volume v: vList) {
			addMissingStorageLinks(v);
		}
		removeNonExistentStorageLinks();
	}

	private void removeNonExistentStorageLinks() {
			List<String> toRemove = new ArrayList<String>();
			Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
			for(Resource res: config.getResources()) {
				if(res instanceof org.eclipse.cmf.occi.infrastructure.Compute) {
					for(Link l: res.getLinks()) {
						if(l instanceof Storagelink) {
							if(connectionExistsInCloud(res, l.getTarget()) == false) {
								toRemove.add(l.getLocation());
							}
						}
					}
				}
			}
		removeFromRuntimeModel(toRemove);	
	}

	private boolean connectionExistsInCloud(Resource res, Resource target) {
		String serverID = getRuntimeId(res);
		String volumeID = getRuntimeId(target);
		
		Volume v = os.blockStorage().volumes().get(volumeID);
		for(VolumeAttachment a: v.getAttachments()) {
			if(a.getServerId().equals(serverID)) {
				return true;
			}
		}
		return false;
	}

	private void addMissingStorageLinks(Volume v) {
		for(VolumeAttachment a: v.getAttachments()) {
			if(isInRuntimeModel(a) == false) {
				addStorageLinkToRuntimeModel(a);
			}
		}
	}

	private void addStorageLinkToRuntimeModel(VolumeAttachment a) {
		Storagelink stl = ifac.createStoragelink();
		adjustStlState(stl, a);
		addDeviceId(stl, a);
		addMountpoint(stl, a);
		
		String srcLocation = getResourceModelRepresentation(a.getServerId()).getLocation();
		String tarLocation = getResourceModelRepresentation(a.getVolumeId()).getLocation();
		
		addLinkToRuntimeModel(stl, srcLocation, tarLocation);		
	}

	private boolean isInRuntimeModel(VolumeAttachment a) {
		String serverID = a.getServerId();
		String volID = a.getVolumeId();
		if(isBlacklisted(serverID) || isBlacklisted(volID)) {
			return true;
		}
		
		Resource comp = getResourceModelRepresentation(serverID);
		Resource storage = getResourceModelRepresentation(volID);
		
		if(comp == null || storage == null) {
			return true;
		}
		
		return connectionExists(comp, storage);
	}

	private boolean connectionExists(Resource comp, Resource storage) {
		for(Link l: comp.getLinks()) {
			if(l.getTarget().getId().equals(storage.getId())) {
				return true;
			}
		}
		return false;
	}

	private List<? extends Volume> filterTenantAndBlacklisted(List<? extends Volume> list, String tenantid) {
		List<Volume> vList = new ArrayList<>();
		for(Volume v : list) {
			if(isBlacklisted(v.getId()) == false && v.getTenantId().equals(tenantid)) {
				vList.add(v);
			}
		}
		return vList;
	}

	private void adjustStlState(Storagelink stl, VolumeAttachment a) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.storagelink.state");
		state.setValue("active");
		stl.getAttributes().add(state);
	}
	
	private void addDeviceId(Storagelink stl, VolumeAttachment a) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.storagelink.deviceid");
		state.setValue(a.getServerId());
		stl.getAttributes().add(state);
	}
	
	private void addMountpoint(Storagelink stl, VolumeAttachment a) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.storagelink.mountpoint");
		state.setValue(a.getDevice());
		stl.getAttributes().add(state);
	}
}

package org.modmacao.openstack.sync;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.AttributeState;
import org.eclipse.cmf.occi.core.Configuration;
import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.infrastructure.Storage;
import org.eclipse.cmf.occi.infrastructure.StorageStatus;
import org.occiware.mart.server.model.ConfigurationManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.block.Volume;

import openstackruntime.Runtimeid;

public class StorageSync extends AbsSync {

	public StorageSync(OSClient<?> os) {
		this.os = os;
	}

	public void sync() {
		//Map<String, String> filter = new HashMap<String, String>();
		List<? extends Volume> vList = filterTenant(os.blockStorage().volumes().list(), TENANTID);
		addMissingVolumes(vList);
		removeNonExistentStorageNodes(vList);
	}
	
	private List<? extends Volume> filterTenant(List<? extends Volume> list, String tenantid) {
		List<Volume> vList = new ArrayList<>();
		for(Volume v : list) {
			if(v.getTenantId().equals(tenantid)) {
				vList.add(v);
			}
		}
		return vList;
	}

	private void removeNonExistentStorageNodes(List<? extends Volume> vList) {
		List<String> toRemove = new ArrayList<String>();
		Configuration config = ConfigurationManager.getConfigurationForOwner("anonymous");
		for(Resource res: config.getResources()) {
			if(res instanceof org.eclipse.cmf.occi.infrastructure.Storage) {
				for(MixinBase mixB: res.getParts()) {
					if(mixB instanceof Runtimeid) {
						Runtimeid rid = (Runtimeid) mixB;
						if(idExistsInCloud(rid, vList) == false) {
							toRemove.add(res.getLocation());
						}
					}
				}
			}
		}
		
		removeFromRuntimeModel(toRemove);	
	}


	private void addMissingVolumes(List<? extends Volume> vList) {
		for(Volume v: vList) {
			if(isInRuntimeModel(v.getId()) == false) {
				addVolumeToRuntimeModel(v);
			}
		}
		
	}

	private void addVolumeToRuntimeModel(Volume v) {
		org.eclipse.cmf.occi.infrastructure.Storage st = ifac.createStorage();
		setName(st,v);
		setSize(st,v);
		adjustState(st, v);
		adjustSummary(st, v);
		appendRid(st, v.getId());
		
		if(os.blockStorage().volumes().get(v.getId()) != null) {
			addEntityToRuntimeModel(st);
		}
	}

	private void adjustSummary(Storage st, Volume v) {
		for(AttributeState as: st.getAttributes()) {
			if(as.getName().equals("occi.core.summary")) {
				as.setValue(v.getDescription());
			}
		}
	}

	private void setName(Storage st, Volume v) {
		@SuppressWarnings("deprecation")
		String name = v.getDisplayName();
		
		if(name == null || name.isEmpty()) {
			name = Float.toString(v.getSize());
		}
		
		st.setTitle(name);
		for(AttributeState as: st.getAttributes()) {
			if(as.getName().equals("occi.core.title")) {
				as.setValue(name);
			}
		}
	}
	
	private void setSize(Storage st, Volume v) {
		float size = v.getSize();
		st.setOcciStorageSize(size);
		for(AttributeState as: st.getAttributes()) {
			if(as.getName().equals("occi.storage.size")) {
				as.setValue(Float.toString(size));
			}
		}
	}

	private void adjustState(Storage st, Volume v) {
		AttributeState state = cfac.createAttributeState();
		state.setName("occi.storage.state");
		switch (v.getStatus()) {
			case ERROR:
				st.setOcciStorageState(StorageStatus.ERROR);
				state.setValue("error");
				break;
			case AVAILABLE:
				st.setOcciStorageState(StorageStatus.ONLINE);
				state.setValue("online");
				break;
			case UNRECOGNIZED:
			default:
				st.setOcciStorageState(StorageStatus.OFFLINE);
				state.setValue("offline");
				break;	
		}
		st.getAttributes().add(state);
	}

	private boolean idExistsInCloud(Runtimeid rid, List<? extends Volume> vList) {
		for(Volume v: vList) {
			if(rid.getOpenstackRuntimeId().equals(v.getId())) {
				return true;
			}
		}
		return false;
	}
}

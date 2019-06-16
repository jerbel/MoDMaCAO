package org.modmacao.cm.bash;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.modmacao.cm.ConfigurationManagementTool;
import org.modmacao.cm.ansible.AnsibleCMTool;
import org.modmacao.cm.ansible.AnsibleHelper;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BashCMTool implements ConfigurationManagementTool {
	static Logger LOGGER = LoggerFactory.getLogger(BashCMTool.class);

	@Override
	public int deploy(Application app) {
		List<String> softwareComponents = getSoftwareComponents(app);
		if (softwareComponents.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeSoftwareComponents(app, softwareComponents);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int configure(Application app) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int start(Application app) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stop(Application app) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int undeploy(Application app) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deploy(Component comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int configure(Component comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int start(Component comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stop(Component comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int undeploy(Component comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<String> getSoftwareComponents(Resource resource) {
		List<String> softwareComponents = new ArrayList<String>();
		for (MixinBase mixin : resource.getParts()) {
			LOGGER.debug("Mixin has schema: " + mixin.getMixin().getScheme());
			if (mixin.getMixin().getScheme().matches(".*(schemas\\.modmacao\\.org).*") || mixin instanceof modmacao.Component){
				LOGGER.info("Found mixin " + mixin.getMixin().getName());
				softwareComponents.add(mixin.getMixin().getName().toLowerCase());
			}
		}
		return softwareComponents;
	}
	
	private int executeSoftwareComponents(Resource resource, List<String> softwareComponents) {
		BashHelper helper = new BashHelper(resource);
		
		BashReturnState state = helper.executeSoftwareComponents();
		
		return 0;
	}
}

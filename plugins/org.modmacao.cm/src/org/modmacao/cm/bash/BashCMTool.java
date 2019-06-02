package org.modmacao.cm.bash;

import java.util.List;

import org.eclipse.cmf.occi.core.Resource;
import org.modmacao.cm.ConfigurationManagementTool;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;

public class BashCMTool implements ConfigurationManagementTool {

	@Override
	public int deploy(Application app) {
		List<String> roles = getScripts(app);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeScripts(app, roles);
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

	private List<String> getScripts(Resource resource) {
		return null;
	}
	
	private int executeScripts(Resource resource, List<String> scripts) {
		return 0;
	}
}

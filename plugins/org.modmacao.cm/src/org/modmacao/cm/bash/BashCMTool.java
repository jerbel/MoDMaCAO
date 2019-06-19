package org.modmacao.cm.bash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.modmacao.cm.ConfigurationManagementTool;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BashCMTool implements ConfigurationManagementTool {
	static Logger LOGGER = LoggerFactory.getLogger(BashCMTool.class);
	
	static final String CONFIGURE_SCRIPT = "CONFIGURE.sh";
	static final String DEPLOY_SCRIPT = "DEPLOY.sh";
	static final String START_SCRIPT = "START.sh";
	static final String STOP_SCRIPT = "STOP.sh";
	static final String UNDEPLOY_SCRIPT = "UNDEPLOY.sh";

	@Override
	public int deploy(Application app) {
//		List<String> softwareComponents = getSoftwareComponents(app);
//		if (softwareComponents.isEmpty())
//			return 0;
		
		int status = -1;
		
		try {
			status = executeSoftwareComponents(app,DEPLOY_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int configure(Application app) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(app,CONFIGURE_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int start(Application app) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(app,START_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int stop(Application app) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(app,STOP_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int undeploy(Application app) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(app,UNDEPLOY_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int deploy(Component comp) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(comp,DEPLOY_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int configure(Component comp) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(comp,CONFIGURE_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int start(Component comp) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(comp,START_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int stop(Component comp) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(comp,STOP_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int undeploy(Component comp) {
		int status = -1;
		
		try {
			status = executeSoftwareComponents(comp,UNDEPLOY_SCRIPT);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	/*
	 * copied
	 */
//	private List<String> getSoftwareComponents(Resource resource) {
//		List<String> softwareComponents = new ArrayList<String>();
//		for (MixinBase mixin : resource.getParts()) {
//			LOGGER.debug("Mixin has schema: " + mixin.getMixin().getScheme());
//			if (mixin.getMixin().getScheme().matches(".*(schemas\\.modmacao\\.org).*") || mixin instanceof modmacao.Component){
//				LOGGER.info("Found mixin " + mixin.getMixin().getName());
//				softwareComponents.add(mixin.getMixin().getName().toLowerCase());
//			}
//		}
//		return softwareComponents;
//	}
	
	private int executeSoftwareComponents(Resource resource, String task) throws IOException, InterruptedException {
		BashHelper helper = new BashHelper(resource, task);
		
		BashReturnState state = helper.executeSoftwareComponents();
		
		if (state.getStateMessage() != null) {
			LOGGER.info("Received state message.");
			LOGGER.info(state.getStateMessage());
			if (resource instanceof Component) {
				((Component) resource).setOcciComponentStateMessage(state.getStateMessage());
			} else if (resource instanceof Application) {
				((Application) resource).setOcciAppStateMessage(state.getStateMessage());	
			}
		}
		System.out.println("EXIT VALUE: " + state.getExitValue());
		return state.getExitValue();
	}
}

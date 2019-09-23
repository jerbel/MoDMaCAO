package org.modmacao.cm.bash;

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
	
	String propertiesFilePath = "bash.properties";
	
	public void setPropertiesFilePath(String propertiesFilePath) {
		this.propertiesFilePath = propertiesFilePath;
	}

	@Override
	public int deploy(Application app) {
//		List<String> softwareComponents = getSoftwareComponents(app);
//		if (softwareComponents.isEmpty())
//			return 0;
		
//		int status = -1;
//		
//		try {
//			status = executeSoftwareComponents(app,DEPLOY_SCRIPT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
		
		return 0;
	}

	@Override
	public int configure(Application app) {
//		int status = -1;
//		
//		try {
//			status = executeSoftwareComponents(app,CONFIGURE_SCRIPT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
		return 0;
	}

	@Override
	public int start(Application app) {
//		int status = -1;
//		
//		try {
//			status = executeSoftwareComponents(app,START_SCRIPT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
		
		return 0;
	}

	@Override
	public int stop(Application app) {
//		int status = -1;
//		
//		try {
//			status = executeSoftwareComponents(app,STOP_SCRIPT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
		
		return 0;
	}

	@Override
	public int undeploy(Application app) {
//		int status = -1;
//		
//		try {
//			status = executeSoftwareComponents(app,UNDEPLOY_SCRIPT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
		
		return 0;
	}

	@Override
	public int deploy(Component comp) {
		return executeSoftwareComponents(comp,DEPLOY_SCRIPT);
	}

	@Override
	public int configure(Component comp) {
		return executeSoftwareComponents(comp,CONFIGURE_SCRIPT);
	}

	@Override
	public int start(Component comp) {
		return executeSoftwareComponents(comp,START_SCRIPT);
	}

	@Override
	public int stop(Component comp) {
		return executeSoftwareComponents(comp,STOP_SCRIPT);
	}

	@Override
	public int undeploy(Component comp) {
		return executeSoftwareComponents(comp,UNDEPLOY_SCRIPT);
	}
	
	private int executeSoftwareComponents(Resource resource, String task) {
		LOGGER.info("Execute software component (" + resource + ")");
		LOGGER.info("Execute task: " + task);
		
		BashHelper helper = new BashHelper(resource, task, propertiesFilePath);
		
		BashReturnState state;
			state = helper.executeSoftwareComponents();
			if (state.getStateMessage() != null) {
				LOGGER.info("Received state message:\n" + state.getStateMessage());
				if (resource instanceof Component) {
					((Component) resource).setOcciComponentStateMessage(state.getStateMessage());
				} else if (resource instanceof Application) {
					((Application) resource).setOcciAppStateMessage(state.getStateMessage());	
				}
			}
			LOGGER.info("EXIT VALUE: " + state.getExitValue());
			return state.getExitValue();
	}
}

package org.modmacao.cm.ansible;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.cmf.occi.core.MixinBase;
import org.eclipse.cmf.occi.core.Resource;
import org.eclipse.cmf.occi.docker.Container;
import org.eclipse.cmf.occi.docker.Machine;
import org.eclipse.cmf.occi.infrastructure.Compute;
import org.modmacao.ansibleconfiguration.Ansibleendpoint;
import org.modmacao.cm.ConfigurationManagementTool;
import org.modmacao.occi.platform.Application;
import org.modmacao.occi.platform.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnsibleCMTool implements ConfigurationManagementTool {
	static Logger LOGGER = LoggerFactory.getLogger(AnsibleCMTool.class);

	@Override
	public int deploy(Application app) {
		List<String> roles = getRoles(app);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(app, roles, "DEPLOY");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int configure(Application app) {
		List<String> roles = getRoles(app);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(app, roles, "CONFIGURE");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;

	}

	@Override
	public int start(Application app) {
		List<String> roles = getRoles(app);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(app, roles, "START");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;
	}

	@Override
	public int stop(Application app) {
		List<String> roles = getRoles(app);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(app, roles, "STOP");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
		return status;

	}

	@Override
	public int undeploy(Application app) {
		List<String> roles = getRoles(app);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(app, roles, "UNDEPLOY");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return status;

	}

	@Override
	public int deploy(Component comp) {
		List<String> roles = getRoles(comp);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(comp, roles, "DEPLOY");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return status;

	}

	@Override
	public int configure(Component comp) {
		List<String> roles = getRoles(comp);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(comp, roles, "CONFIGURE");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return status;

	}

	@Override
	public int start(Component comp) {
		List<String> roles = getRoles(comp);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(comp, roles, "START");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return status;

	}

	@Override
	public int stop(Component comp) {
		List<String> roles = getRoles(comp);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(comp, roles, "STOP");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return status;

	}

	@Override
	public int undeploy(Component comp) {
		List<String> roles = getRoles(comp);
		if (roles.isEmpty())
			return 0;
		
		int status = -1;
		
		try {
			status = executeRoles(comp, roles, "UNDEPLOY");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return status;

	}
	
	private String getUser() {
		return new AnsibleHelper().getProperties().getProperty("ansible_user");
	}
	
	private List<String> getRoles(Resource resource) {
		List<String> roles = new ArrayList<String>();
		for (MixinBase mixin : resource.getParts()) {
			LOGGER.debug("Mixin has schema: " + mixin.getMixin().getScheme());
			if (mixin.getMixin().getScheme().matches(".*(schemas\\.modmacao\\.org).*") || mixin instanceof modmacao.Component){
				LOGGER.info("Found mixin " + mixin.getMixin().getName());
				String rolepath = getRolePath(mixin);
				//roles.add(mixin.getMixin().getName().toLowerCase());
				roles.add(rolepath);
			}
		}
		return roles;
	}
	
	private String getRolePath(MixinBase mixin) {
		String rolepath = mixin.getMixin().getScheme();
		rolepath = rolepath.replaceAll("http://", "");
		rolepath = rolepath.replaceAll("\\.", "_");
		rolepath = rolepath.replaceAll("#", "");
		rolepath = rolepath + "/" + mixin.getMixin().getName().toLowerCase();
		return rolepath;
	}

	private int executeRoles(Resource resource, List<String> roles, String task) throws Exception{
		AnsibleCMTool.LOGGER.info("Start: Create Helper");
		AnsibleHelper helper = new AnsibleHelper();

		String ipaddress = "";
		if(helper.isPlacedOnContainer(resource)) {
			Container container = (Container)helper.getCompute(resource);
			ipaddress = container.getName();
		} else {
			ipaddress = helper.getIPAddress(resource);
		}
    
		String user = this.getUser();
		String options = null;
		String keypath = helper.getProperties().getProperty("private_key_path");
		
		if (ipaddress.equals("127.0.0.1")) {
			options = "--connection=local";
		}
		
		String basedir = "/tmp/" + helper.getTitle(resource).replace(' ', '_') + "_ansible_" + System.currentTimeMillis();
		
		System.out.println("Der Ordner des aktuellen Ansible deploys ist: " + basedir);
		
		Ansibleendpoint endpoint = helper.getAnsibleEndboint(resource);
		
		if (endpoint != null) {
			String remoteuser = endpoint.getAnsibleRemoteuser();
			String privatekey = endpoint.getAnsiblePrivatekey();
			if (remoteuser != null && !remoteuser.equals("")) {
				user = remoteuser;
			}
			if (privatekey != null && !privatekey.equals("")) {
					keypath = privatekey;
			}
		}
		
		
		helper.createConfiguration(Paths.get("ansible.cfg"), 
				Paths.get(keypath));
		List <Path> variablefiles = new ArrayList<Path>();
		
		//variablefiles.add(helper.createVariableFile(Paths.get(basedir, "vars.yaml"), resource));
		variablefiles.add(helper.createExtendedVariableFile(Paths.get(basedir), resource));
			
		Path playbook = null;
		if(helper.isPlacedOnContainer(resource)) {
			playbook = helper.createPlaybookForContainer(ipaddress, roles, user, variablefiles, 
					Paths.get(basedir, "playbook.yml"));
		} else {
			playbook = helper.createPlaybook(ipaddress, roles, user, variablefiles, 
					Paths.get(basedir, "playbook.yml"));
		}
			
		Path inventory = helper.createInventory(ipaddress, Paths.get(basedir, "inventory"));
			
		LOGGER.info("Executing role " + roles + " with task " + task + " on host " + ipaddress + " with user " + user + ".");
		
		AnsibleReturnState state = null;
		
		if(helper.isPlacedOnContainer(resource)) {
			Machine machine = helper.getMachine((Container)helper.getCompute(resource));
			if(machine == null)
				throw new Exception("The container has no machine connected to it");
			String machineName = helper.getMachine((Container)helper.getCompute(resource)).getName();
			state = helper.executePlaybookForContainer(playbook, task, inventory, options, machineName);
		} else {
			state = helper.executePlaybook(playbook, task, inventory, options);
		}
		
		if (state.getStateMessage() != null) {
			LOGGER.info("Received state message.");
			LOGGER.info(state.getStateMessage());
			if (resource instanceof Component) {
				((Component) resource).setOcciComponentStateMessage(state.getStateMessage());
			} else if (resource instanceof Application) {
				((Application) resource).setOcciAppStateMessage(state.getStateMessage());	
			}
		}
		
		return state.getExitValue();
	}

}

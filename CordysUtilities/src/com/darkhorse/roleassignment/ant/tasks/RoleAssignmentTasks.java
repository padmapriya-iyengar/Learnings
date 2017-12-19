package com.darkhorse.roleassignment.ant.tasks;

import com.cordys.deployment.ant.tasks.BaseTask;
import com.darkhorse.roleassignment.ant.handler.RoleAssignmentHandler;
import com.darkhorse.roleassignment.ant.settings.RoleAssignmentSettings;

public class RoleAssignmentTasks extends
		BaseTask<RoleAssignmentSettings, RoleAssignmentHandler> {

	public RoleAssignmentTasks() {
		super(new RoleAssignmentSettings());
		// TODO Auto-generated constructor stub
	}

	public void setRoleName(String roleName) {
		((RoleAssignmentSettings) getSettings()).setRoleName(roleName);
	}

	public void setRolePackage(String rolePackage) {
		((RoleAssignmentSettings) getSettings()).setRolePackage(rolePackage);
	}

	public void setOrganization(String organization) {
		((RoleAssignmentSettings) getSettings()).setOrganization(organization);
	}

	public void setUser(String user) {
		((RoleAssignmentSettings) getSettings()).setUser(user);
	}

	public void setServiceNamespace(String serviceNamespace) {
		((RoleAssignmentSettings) getSettings())
				.setServiceNamespace(serviceNamespace);
	}

	@Override
	public RoleAssignmentHandler createHandler(String paramString,
			RoleAssignmentSettings paramSettingsT) {
		// TODO Auto-generated method stub
		return new RoleAssignmentHandler("cn=cordys,cn=DevInst2,o=alahli.com",
				(RoleAssignmentSettings) getSettings());
	}

}

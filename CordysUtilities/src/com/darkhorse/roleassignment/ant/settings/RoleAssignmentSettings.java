package com.darkhorse.roleassignment.ant.settings;

import com.cordys.deployment.ant.settings.StandardSettings;
import com.darkhorse.Messages;
import com.darkhorse.roleassignment.ant.exception.RoleAssignmentException;

public class RoleAssignmentSettings extends StandardSettings {
	public String roleName;
	public String rolePackage;
	public String organization;
	public String user;
	public String serviceNamespace;

	public String getRoleName() {
		return roleName;
	}

	public String getRolePackage() {
		return rolePackage;
	}

	public String getOrganization() {
		return organization;
	}

	public String getUser() {
		return user;
	}

	public String getServiceNamespace() {
		return serviceNamespace;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRolePackage(String rolePackage) {
		this.rolePackage = rolePackage;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		if (null == this.roleName || ("").equals(this.roleName))
			throw new RoleAssignmentException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Role Name" });
		if (null == this.rolePackage || ("").equals(this.rolePackage))
			throw new RoleAssignmentException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Role Package" });
		if (null == this.organization || ("").equals(this.organization))
			throw new RoleAssignmentException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Organization" });
		if (null == this.user || ("").equals(this.user))
			throw new RoleAssignmentException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "User Details" });
		if (null == this.serviceNamespace || ("").equals(this.serviceNamespace))
			throw new RoleAssignmentException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Namespace" });

		return true;
	}

}

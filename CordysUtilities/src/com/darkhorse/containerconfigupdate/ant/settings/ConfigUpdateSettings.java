package com.darkhorse.containerconfigupdate.ant.settings;

import com.cordys.deployment.ant.settings.StandardSettings;
import com.darkhorse.Messages;
import com.darkhorse.containerconfigupdate.ant.exception.ConfigUpdateException;

public class ConfigUpdateSettings extends StandardSettings {
	public String serviceGroup;
	public String serviceContainer;
	public String organization;
	public String updateParameter;
	public String updateValue;
	public String serviceNamespace;
	public String updateAction;

	public String getServiceGroup() {
		return this.serviceGroup;
	}

	public String getServiceContainer() {
		return this.serviceContainer;
	}

	public String getOrganization() {
		return this.organization;
	}

	public String getUpdateParameter() {
		return this.updateParameter;
	}

	public String getUpdateValue() {
		return this.updateValue;
	}

	public String getServiceNamespace() {
		return this.serviceNamespace;
	}

	public String getUpdateAction() {
		return this.updateAction;
	}

	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public void setServiceContainer(String serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setUpdateParameter(String updateParameter) {
		this.updateParameter = updateParameter;
	}

	public void setUpdateValue(String updateValue) {
		this.updateValue = updateValue;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		if(null == this.serviceGroup || ("").equals(this.serviceGroup))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Group" });
		if(null == this.serviceContainer || ("").equals(this.serviceContainer))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Container" });
		if(null == this.organization || ("").equals(this.organization))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Organization" });
		if(null == this.updateParameter || ("").equals(this.updateParameter))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Update Parameter" });
		if(null == this.updateValue || ("").equals(this.updateValue))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Update Value" });
		if(null == this.serviceNamespace || ("").equals(this.serviceNamespace))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Namespace" });
		if(null == this.updateAction || ("").equals(this.updateAction))
			throw new ConfigUpdateException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Update Action" });

		return true;
	}
}

package com.darkhorse.groupconfigupdate.ant.settings;

import com.cordys.deployment.ant.settings.StandardSettings;
import com.darkhorse.Messages;
import com.darkhorse.containerconfigupdate.ant.exception.ConfigUpdateException;

public class ConfigUpdateSettings extends StandardSettings {
	public String serviceGroup;
	public String organization;
	public String updateParameter;
	public String updateValue;
	public String updateAction;
	public String serviceNamespace;
	public String interfacePackage;

	public String getServiceGroup() {
		return this.serviceGroup;
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

	public String getUpdateAction() {
		return this.updateAction;
	}

	public String getServiceNamespace() {
		return this.serviceNamespace;
	}

	public String getInterfacePackage() {
		return this.interfacePackage;
	}

	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
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

	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	public void setInterfacePackage(String interfacePackage) {
		this.interfacePackage = interfacePackage;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		if (null == this.serviceGroup || ("").equals(this.serviceGroup))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Group" });

		if (null == this.organization || ("").equals(this.organization))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Organization" });

		if (null == this.updateParameter || ("").equals(this.updateParameter))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Update Parameter" });

		if (null == this.updateValue || ("").equals(this.updateValue))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Update Value" });

		if (null == this.updateAction || ("").equals(this.updateAction))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Update Action" });

		if (null == this.serviceNamespace || ("").equals(this.serviceNamespace))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Namespace" });
		if (null == this.interfacePackage || ("").equals(this.interfacePackage))
			throw new ConfigUpdateException(Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Interface Package" });
		
		return true;
	}

}

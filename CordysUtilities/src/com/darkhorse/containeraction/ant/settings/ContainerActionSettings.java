package com.darkhorse.containeraction.ant.settings;

import com.cordys.deployment.ant.settings.StandardSettings;
import com.darkhorse.Messages;
import com.darkhorse.containeraction.ant.exception.ContainerActionException;

public class ContainerActionSettings extends StandardSettings {
	public String serviceGroup;
	public String serviceContainer;
	public String organization;
	public String action;
	public String serviceNamespace;

	public String getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public String getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(String serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getServiceNamespace() {
		return serviceNamespace;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		
		if (null == serviceContainer || ("").equals(serviceContainer))
			throw new ContainerActionException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Container" });
		if (null == serviceGroup || ("").equals(serviceGroup))
			throw new ContainerActionException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Group" });
		if (null == organization || ("").equals(organization))
			throw new ContainerActionException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Organization" });
		if (null == action || ("").equals(action))
			throw new ContainerActionException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Action" });
		if (null == serviceNamespace || ("").equals(serviceNamespace))
			throw new ContainerActionException(
					Messages.NO_VALUE_FOR_PROPERTY,
					new Object[] { "Service Namespace" });

		return true;
	}

}

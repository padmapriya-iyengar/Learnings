package com.darkhorse.containeraction.ant.tasks;

import com.cordys.deployment.ant.tasks.BaseTask;
import com.darkhorse.containeraction.ant.handler.ContainerActionHandler;
import com.darkhorse.containeraction.ant.settings.ContainerActionSettings;

public class ContainerActionTask extends
		BaseTask<ContainerActionSettings, ContainerActionHandler> {

	public ContainerActionTask() {
		super(new ContainerActionSettings());
		// TODO Auto-generated constructor stub
	}

	public void setServiceGroup(String serviceGroup) {
		((ContainerActionSettings) getSettings()).setServiceGroup(serviceGroup);
	}

	public void setServiceContainer(String serviceContainer) {
		((ContainerActionSettings) getSettings())
				.setServiceContainer(serviceContainer);
	}

	public void setOrganization(String organization) {
		((ContainerActionSettings) getSettings()).setOrganization(organization);
	}

	public void setAction(String action) {
		((ContainerActionSettings) getSettings()).setAction(action);
	}

	public void setServiceNamespace(String serviceNamespace) {
		((ContainerActionSettings) getSettings())
				.setServiceNamespace(serviceNamespace);
	}

	@Override
	public ContainerActionHandler createHandler(String paramString,
			ContainerActionSettings paramSettingsT) {
		// TODO Auto-generated method stub
		return new ContainerActionHandler("cn=cordys,cn=DevInst2,o=alahli.com",
				(ContainerActionSettings) getSettings());
	}

}

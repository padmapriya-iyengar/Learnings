package com.darkhorse.containerconfigupdate.ant.task;

import com.cordys.deployment.ant.tasks.BaseTask;
import com.darkhorse.containerconfigupdate.ant.handler.ConfigUpdateHandler;
import com.darkhorse.containerconfigupdate.ant.settings.ConfigUpdateSettings;

public class ConfigUpdateTask extends
		BaseTask<ConfigUpdateSettings, ConfigUpdateHandler> {

	public ConfigUpdateTask() {
		super(new ConfigUpdateSettings());
		// TODO Auto-generated constructor stub
	}

	public void setServiceGroup(String serviceGroup) {
		((ConfigUpdateSettings) getSettings()).setServiceGroup(serviceGroup);
	}

	public void setServiceContainer(String serviceContainer) {
		((ConfigUpdateSettings) getSettings())
				.setServiceContainer(serviceContainer);
	}

	public void setOrganization(String organization) {
		((ConfigUpdateSettings) getSettings()).setOrganization(organization);
	}

	public void setUpdateParameter(String updateParameter) {
		((ConfigUpdateSettings) getSettings())
				.setUpdateParameter(updateParameter);
	}

	public void setUpdateValue(String updateValue) {
		((ConfigUpdateSettings) getSettings()).setUpdateValue(updateValue);
	}

	public void setServiceNamespace(String serviceNamespace) {
		((ConfigUpdateSettings) getSettings())
				.setServiceNamespace(serviceNamespace);
	}

	public void setUpdateAction(String updateAction) {
		((ConfigUpdateSettings) getSettings()).setUpdateAction(updateAction);
	}

	@Override
	public ConfigUpdateHandler createHandler(String paramString,
			ConfigUpdateSettings paramSettingsT) {
		// TODO Auto-generated method stub
		return new ConfigUpdateHandler("cn=cordys,cn=DevInst2,o=alahli.com",
				(ConfigUpdateSettings) getSettings());
	}

}

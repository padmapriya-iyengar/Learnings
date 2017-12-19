package com.darkhorse.groupconfigupdate.ant.task;

import com.cordys.deployment.ant.tasks.BaseTask;
import com.darkhorse.groupconfigupdate.ant.handler.ConfigUpdateHandler;
import com.darkhorse.groupconfigupdate.ant.settings.ConfigUpdateSettings;

public class ConfigUpdateTask extends
		BaseTask<ConfigUpdateSettings, ConfigUpdateHandler> {

	public ConfigUpdateTask() {
		super(new ConfigUpdateSettings());
		// TODO Auto-generated constructor stub
	}

	public void setServiceGroup(String serviceGroup) {
		((ConfigUpdateSettings) getSettings()).setServiceGroup(serviceGroup);
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

	public void setUpdateAction(String updateAction) {
		((ConfigUpdateSettings) getSettings()).setUpdateAction(updateAction);
	}

	public void setServiceNamespace(String serviceNamespace) {
		((ConfigUpdateSettings) getSettings())
				.setServiceNamespace(serviceNamespace);
	}

	public void setInterfacePackage(String interfacePackage) {
		((ConfigUpdateSettings) getSettings())
				.setInterfacePackage(interfacePackage);
	}

	@Override
	public ConfigUpdateHandler createHandler(String arg0,
			ConfigUpdateSettings arg1) {
		// TODO Auto-generated method stub
		return new ConfigUpdateHandler("cn=cordys,cn=DevInst2,o=alahli.com",
				(ConfigUpdateSettings) getSettings());
	}

}

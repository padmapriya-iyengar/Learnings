package com.darkhorse.containeraction.ant.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cordys.deployment.ant.handler.BaseHandler;
import com.cordys.deployment.ant.tasks.BaseTask;
import com.cordys.deployment.ant.util.DomUtil;
import com.darkhorse.containeraction.ant.exception.ContainerActionException;
import com.darkhorse.containeraction.ant.settings.ContainerActionSettings;
import com.darkhorse.Messages;

public class ContainerActionHandler extends BaseHandler {

	private final ContainerActionSettings settings;
	private String serviceContainer = null;
	private String serviceGroup = null;
	private String organization = null;
	private String action = null;
	private String serviceNS = null;

	public ContainerActionHandler(String ldapRoot,
			ContainerActionSettings settings) {
		super(ldapRoot);
		// TODO Auto-generated constructor stub
		this.settings = settings;
		initialize();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		getLogger().debug(
				Messages.START_EXECUTE_ACTION,
				new Object[] { action });
		String containerDN = null;
		Document doc = null;
		Element request = null;
		try {
			containerDN = createContainerDN();
			doc = getDomNodeManager().getDocument();
			request = doc.createElementNS(
					"http://schemas.cordys.com/1.0/monitor", "root");
			DomUtil.createAndAppendTextElement(request, "dn", containerDN);
			if (null != action
					&& !("").equals(action)
					&& (action.equalsIgnoreCase("RESTART")
							|| action.equalsIgnoreCase("START") || action
								.equalsIgnoreCase("STOP"))) {
				getLogger().debug(
						Messages.SOAP_REQUEST_PREPARED,
						new Object[] { action, request});
				createAndSendSOAPRequest(request, serviceNS, action);
				getLogger().debug(
						Messages.SOAP_RESPONSE_RECEIVED,
						new Object[] { action });
			} else {
				BaseTask.printMessage("Invalid action");
				throw new ContainerActionException(Messages.NO_VALID_ACTION,
						new Object[] { action });
			}
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while executing the restart handler");
			getLogger().debug(
					Messages.ACTION_FAILED,
					new Object[] { action });

			throw new ContainerActionException(exp,
					Messages.SOAP_FAULT_RECEIVED, new Object[] { exp.getMessage() });
		}
		getLogger().debug(
				Messages.ACTION_SUCCESSFUL,
				new Object[] { action });
		getLogger().debug(
				Messages.FINISH_EXECUTE_ACTION,
				new Object[] { action });
	}

	public String createContainerDN() {
		String dn = "";

		try {
			serviceContainer = this.settings.getServiceContainer();
			serviceGroup = this.settings.getServiceGroup();
			organization = this.settings.getOrganization();
			action = this.settings.getAction();
			serviceNS = this.settings.getServiceNamespace();

			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceContainer });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceGroup });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { organization });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { action });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceNS });


			dn += "cn=" + serviceContainer + ",cn=" + serviceGroup
					+ ",cn=soap nodes,o=" + organization
					+ ",cn=cordys,cn=defaultInst,o=dhdigital.co.in";
			getLogger().debug(Messages.ACTION_INPROGRESS, new Object[] { dn });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while creating the container DN");

			throw new ContainerActionException(Messages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return dn;
	}

}

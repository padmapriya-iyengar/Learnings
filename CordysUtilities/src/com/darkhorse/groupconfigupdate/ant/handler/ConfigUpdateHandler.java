package com.darkhorse.groupconfigupdate.ant.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cordys.deployment.ant.handler.BaseHandler;
import com.cordys.deployment.ant.tasks.BaseTask;
import com.cordys.deployment.ant.util.DomUtil;
import com.darkhorse.Messages;
import com.darkhorse.groupconfigupdate.ant.exception.ConfigUpdateException;
import com.darkhorse.groupconfigupdate.ant.settings.ConfigUpdateSettings;
import com.eibus.xml.nom.Node;

public class ConfigUpdateHandler extends BaseHandler {
	private final ConfigUpdateSettings settings;
	private String serviceGroup = null;
	private String organization = null;
	private String dn = null;
	private String serviceNS = null;
	private String updateParameter = null;
	private String updateValue = null;
	private String updateAction = null;
	private String interfacePackage = null;

	public ConfigUpdateHandler(String ldapRoot, ConfigUpdateSettings settings) {
		super(ldapRoot);
		// TODO Auto-generated constructor stub
		this.settings = settings;
		initialize();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		getLogger().debug(Messages.START_EXECUTE_ACTION,
				new Object[] { "Update" });
		Element oldGroupConfig = null;
		Element oldTupleElement = null;
		Element oldEntryElement = null;
		Element newEntryElement = null;
		String newMethodSet = null;
		Element newMethodSetElement = null;
		String newLabeledURI = null;
		Element newLabeledURIElement = null;
		Element newMethodSets = null;
		Element newLabeledURIS = null;
		Document doc = null;
		Element request = null;
		Element tuple = null;
		Element oldElement = null;
		Element newElement = null;
		Element oldLdapElement = null;
		Element newLdapElement = null;
		try {
			oldGroupConfig = getLDAPEntry();
			oldTupleElement = (Element) getDomNodeManager().evaluateNode(
					oldGroupConfig, "//GetLDAPObjectResponse/tuple");
			oldEntryElement = (Element) getDomNodeManager().evaluateNode(
					oldTupleElement, "//tuple/old/entry");

			newEntryElement = (Element) oldEntryElement.cloneNode(true);
			newMethodSets = (Element) getDomNodeManager().evaluateNode(
					newEntryElement, "//busmethodsets");
			newLabeledURIS = (Element) getDomNodeManager().evaluateNode(
					newEntryElement, "//labeleduri");

			newMethodSet = "<string>cn=" + updateValue + ",cn=method sets,o="
					+ organization
					+ ",cn=cordys,cn=DevInst2,o=alahli.com</string>";
			newMethodSetElement = (Element) getDomNodeManager().stringToXml(
					newMethodSet);
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { newMethodSet });

			newLabeledURI = "<string>" + interfacePackage + "</string>";
			newLabeledURIElement = (Element) getDomNodeManager().stringToXml(
					newLabeledURI);
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { newLabeledURI });

			if (null != updateParameter && !("").equals(updateParameter)
					&& null != updateValue && !("").equals(updateValue)
					&& null != updateAction && !("").equals(updateAction)) {
				if (updateParameter.equalsIgnoreCase("INTERFACE_UPDATE")
						&& updateAction.equalsIgnoreCase("ADD")) {

					Node.appendToChildren(
							getDomNodeManager().domToNom(newMethodSetElement),
							getDomNodeManager().domToNom(newMethodSets));

					Node.appendToChildren(
							getDomNodeManager().domToNom(newLabeledURIElement),
							getDomNodeManager().domToNom(newLabeledURIS));
				}
				if (updateParameter.equalsIgnoreCase("INTERFACE_UPDATE")
						&& updateAction.equalsIgnoreCase("DELETE")) {

					newMethodSets
							.removeChild((Element) getDomNodeManager()
									.evaluateNode(
											newMethodSets,
											"//busmethodsets/string[text()='cn="
													+ updateValue
													+ ",cn=method sets,o="
													+ organization
													+ ",cn=cordys,cn=DevInst2,o=alahli.com']"));

					newLabeledURIS.removeChild((Element) getDomNodeManager()
							.evaluateNode(
									newLabeledURIS,
									"//labeleduri/string[text()='"
											+ interfacePackage + "']"));
				}

				doc = getDomNodeManager().getDocument();
				request = doc.createElementNS(
						"http://schemas.cordys.com/1.0/ldap", "root");
				tuple = doc.createElement("tuple");
				oldElement = doc.createElement("old");
				newElement = doc.createElement("new");
				request.appendChild(tuple);
				oldLdapElement = (Element) request.getOwnerDocument()
						.importNode(oldEntryElement, true);
				oldElement.appendChild(oldLdapElement);
				tuple.appendChild(oldElement);
				newLdapElement = (Element) request.getOwnerDocument()
						.importNode(newEntryElement, true);
				newElement.appendChild(newLdapElement);
				tuple.appendChild(newElement);
				getLogger().debug(Messages.SOAP_REQUEST_PREPARED,
						new Object[] { "Update", request });
				createAndSendSOAPRequest(request, serviceNS, "Update");
				getLogger().debug(Messages.SOAP_RESPONSE_RECEIVED,
						new Object[] { "Update" });
			}

		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured in Config Update Handler"
					+ exp);
			getLogger().debug(Messages.ACTION_FAILED,
					new Object[] { exp.getMessage() });
			throw new ConfigUpdateException(Messages.SOAP_FAULT_RECEIVED,
					new Object[] { exp.getMessage() });
		}
		getLogger()
				.debug(Messages.ACTION_SUCCESSFUL, new Object[] { "Update" });
		getLogger().debug(Messages.FINISH_EXECUTE_ACTION,
				new Object[] { "Update" });
	}

	public Element getLDAPEntry() {
		Element ldapEntry = null;
		Document doc = null;
		Element request = null;

		try {
			serviceGroup = this.settings.getServiceGroup();
			organization = this.settings.getOrganization();
			serviceNS = this.settings.getServiceNamespace();
			updateParameter = this.settings.getUpdateParameter();
			updateValue = this.settings.getUpdateValue();
			updateAction = this.settings.getUpdateAction();
			interfacePackage = this.settings.getInterfacePackage();

			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceGroup });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { organization });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceNS });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { updateParameter });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { updateValue });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { updateAction });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { interfacePackage });

			dn = "cn=" + serviceGroup + ",cn=soap nodes,o=" + organization
					+ ",cn=cordys,cn=DevInst2,o=alahli.com";
			getLogger().debug(Messages.ACTION_INPROGRESS, new Object[] { dn });

			getLogger().debug(Messages.START_EXECUTE_ACTION,
					new Object[] { "GetLDAPObject" });
			doc = getDomNodeManager().getDocument();
			request = doc.createElementNS("http://schemas.cordys.com/1.0/ldap",
					"root");
			DomUtil.createAndAppendTextElement(request, "dn", dn);
			getLogger().debug(Messages.SOAP_REQUEST_PREPARED,
					new Object[] { "GetLDAPObject", request });
			ldapEntry = createAndSendSOAPRequest(request, serviceNS,
					"GetLDAPObject");
			getLogger().debug(Messages.SOAP_RESPONSE_RECEIVED,
					new Object[] { "GetLDAPObject" });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while fetching the LDAP entry"
					+ exp);
			getLogger().debug(Messages.ACTION_FAILED,
					new Object[] { exp.getMessage() });
			throw new ConfigUpdateException(Messages.SOAP_FAULT_RECEIVED,
					new Object[] { exp.getMessage() });
		}
		getLogger().debug(Messages.ACTION_SUCCESSFUL,
				new Object[] { "GetLDAPObject" });
		getLogger().debug(Messages.FINISH_EXECUTE_ACTION,
				new Object[] { "GetLDAPObject" });
		return ldapEntry;
	}
}

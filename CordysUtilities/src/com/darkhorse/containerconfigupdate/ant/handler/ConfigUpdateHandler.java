package com.darkhorse.containerconfigupdate.ant.handler;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cordys.deployment.ant.handler.BaseHandler;
import com.cordys.deployment.ant.tasks.BaseTask;
import com.cordys.deployment.ant.util.DomUtil;
import com.darkhorse.Messages;
import com.darkhorse.containerconfigupdate.ant.exception.ConfigUpdateException;
import com.darkhorse.containerconfigupdate.ant.settings.ConfigUpdateSettings;

public class ConfigUpdateHandler extends BaseHandler {

	private final ConfigUpdateSettings settings;
	private String serviceContainer = null;
	private String serviceGroup = null;
	private String organization = null;
	private String dn = null;
	private String serviceNS = null;
	private String updateParameter = null;
	private String updateValue = null;
	private String updateAction = null;

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
		Element ldapEntry = null;
		Element oldEntryElement = null;
		Element oldJREConfigCP = null;
		Element newEntryElement = null;
		Element newJREConfigCP = null;
		String oldClassPath = null;
		String newClassPath = null;
		Document doc = null;
		Element request = null;
		Element tuple = null;
		Element oldElement = null;
		Element newElement = null;
		Element oldLdapElement = null;
		Element newLdapElement = null;
		Element oldConfigString = null;
		Element newConfigString = null;
		try {

			ldapEntry = getLDAPEntry();
			oldEntryElement = (Element) getDomNodeManager().evaluateNode(
					ldapEntry, "//tuple/old/entry");
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { toString(oldEntryElement) });
			oldConfigString = (Element) getDomNodeManager().stringToXml(
					getDomNodeManager().evaluateNode(oldEntryElement,
							"//entry/bussoapprocessorconfiguration/string")
							.getTextContent());
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { toString(oldConfigString) });
			newEntryElement = (Element) oldEntryElement.cloneNode(true);
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { toString(newEntryElement) });
			if (null != updateParameter && !("").equals(updateParameter)
					&& "JRECONFIG".equalsIgnoreCase(updateParameter)) {

				getLogger().debug(Messages.ACTION_INPROGRESS,
						new Object[] { updateParameter });

				oldJREConfigCP = (Element) getDomNodeManager().evaluateNode(
						oldConfigString,
						"//jreconfig/param[contains(@value,'-cp')]");
				getLogger().debug(Messages.ACTION_INPROGRESS,
						new Object[] { toString(oldJREConfigCP) });

				newConfigString = (Element) getDomNodeManager().stringToXml(
						getDomNodeManager().evaluateNode(newEntryElement,
								"//entry/bussoapprocessorconfiguration/string")
								.getTextContent());

				getLogger().debug(Messages.ACTION_INPROGRESS,
						new Object[] { toString(newConfigString) });
				newJREConfigCP = (Element) getDomNodeManager().evaluateNode(
						newConfigString,
						"//jreconfig/param[contains(@value,'-cp')]");

				getLogger().debug(Messages.ACTION_INPROGRESS,
						new Object[] { toString(newJREConfigCP) });
				oldClassPath = oldJREConfigCP.getAttribute("value");

				getLogger().debug(Messages.ACTION_INPROGRESS,
						new Object[] { oldClassPath });
				if (null != updateAction && !("").equals(updateAction)
						&& "ADD".equalsIgnoreCase(updateAction)) {

					getLogger().debug(Messages.ACTION_INPROGRESS,
							new Object[] { updateAction });
					newClassPath = oldClassPath.concat(":" + updateValue + ":");

					getLogger().debug(Messages.ACTION_INPROGRESS,
							new Object[] { newClassPath });
				}
				newJREConfigCP.setAttribute("value", newClassPath);

				getLogger().debug(Messages.ACTION_INPROGRESS,
						new Object[] { toString(newJREConfigCP) });
			}
			doc = getDomNodeManager().getDocument();
			request = doc.createElementNS("http://schemas.cordys.com/1.0/ldap",
					"root");
			tuple = doc.createElement("tuple");
			oldElement = doc.createElement("old");
			newElement = doc.createElement("new");
			request.appendChild(tuple);
			oldLdapElement = (Element) request.getOwnerDocument().importNode(
					oldEntryElement, true);
			oldElement.appendChild(oldLdapElement);
			tuple.appendChild(oldElement);
			newLdapElement = (Element) request.getOwnerDocument().importNode(
					newEntryElement, true);
			getDomNodeManager().evaluateNode(newLdapElement,
					"//entry/bussoapprocessorconfiguration/string")
					.setTextContent(toString(newConfigString));
			newElement.appendChild(newLdapElement);
			tuple.appendChild(newElement);
			getLogger().debug(Messages.SOAP_REQUEST_PREPARED,
					new Object[] { "Update", request });
			createAndSendSOAPRequest(request, serviceNS, "Update");
			getLogger().debug(Messages.SOAP_RESPONSE_RECEIVED,
					new Object[] { "Update" });
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
			serviceContainer = this.settings.getServiceContainer();
			serviceGroup = this.settings.getServiceGroup();
			organization = this.settings.getOrganization();
			serviceNS = this.settings.getServiceNamespace();
			updateParameter = this.settings.getUpdateParameter();
			updateValue = this.settings.getUpdateValue();
			updateAction = this.settings.getUpdateAction();

			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceContainer });
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

			dn = "cn=" + serviceContainer + ",cn=" + serviceGroup
					+ ",cn=soap nodes,o=" + organization
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

	public String toString(Element parent) {
		TransformerFactory transformerFactory = null;
		Transformer transformer = null;
		DOMSource source = null;
		StreamResult result = null;
		String strObject = null;
		try {
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			source = new DOMSource(parent);
			result = new StreamResult(new StringWriter());
			transformer.transform(source, result);
			strObject = result.getWriter().toString();
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { strObject });
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while fetching the child element");

			throw new ConfigUpdateException(Messages.UNEXPECTED_EXCEPTION,
					new Object[] { exp.getMessage() });
		}
		return strObject;
	}
}

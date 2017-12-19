package com.darkhorse.roleassignment.ant.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cordys.deployment.ant.handler.BaseHandler;
import com.cordys.deployment.ant.tasks.BaseTask;
import com.cordys.deployment.ant.util.DomUtil;
import com.darkhorse.Messages;
import com.darkhorse.roleassignment.ant.exception.RoleAssignmentException;
import com.darkhorse.roleassignment.ant.settings.RoleAssignmentSettings;
import com.eibus.xml.nom.Node;

public class RoleAssignmentHandler extends BaseHandler {
	private final RoleAssignmentSettings settings;
	private String roleName = null;
	private String rolePackage = null;
	private String organization = null;
	private String user = null;
	private String serviceNamespace = null;

	public RoleAssignmentHandler(String ldapRoot,
			RoleAssignmentSettings settings) {
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
		Element oldRoles = null;
		String newRoleDN = null;
		Element oldEntryElement = null;
		Element oldTupleElement = null;
		String newRoleTag = null;
		Element newRole = null;
		Element newEntryElement = null;
		Element newRoleElement = null;
		Document doc = null;
		Element request = null;
		Element tuple = null;
		Element oldElement = null;
		Element newElement = null;
		Element oldLdapElement = null;
		Element newLdapElement = null;
		try {
			user = this.settings.getUser();

			oldRoles = getOldRolesAssigned(user);
			newRoleDN = "cn=" + roleName + ",cn=" + rolePackage
					+ ",cn=cordys,cn=DevInst2,o=alahli.com";
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { newRoleDN });
			oldTupleElement = (Element) getDomNodeManager().evaluateNode(
					oldRoles, "//SearchLDAPResponse/tuple");
			oldEntryElement = (Element) getDomNodeManager().evaluateNode(
					oldTupleElement, "//tuple/old/entry");

			newEntryElement = (Element) oldEntryElement.cloneNode(true);
			newRoleElement = (Element) getDomNodeManager().evaluateNode(
					newEntryElement, "//role");
			newRoleTag = "<string>" + newRoleDN + "</string>";
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { newRoleTag });
			newRole = (Element) getDomNodeManager().stringToXml(newRoleTag);
			Node.appendToChildren(getDomNodeManager().domToNom(newRole),
					getDomNodeManager().domToNom(newRoleElement));
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
			newElement.appendChild(newLdapElement);
			tuple.appendChild(newElement);
			
			getLogger().debug(Messages.SOAP_REQUEST_PREPARED,
					new Object[] { "Update", request });
			createAndSendSOAPRequest(request, serviceNamespace, "Update");
			getLogger().debug(Messages.SOAP_RESPONSE_RECEIVED,
					new Object[] { "Update" });

		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while executing the role handler: "
					+ exp);
			getLogger().debug(Messages.ACTION_FAILED,
					new Object[] { exp.getMessage() });
			throw new RoleAssignmentException(Messages.SOAP_FAULT_RECEIVED,
					new Object[] { exp.getMessage() });
		}
		getLogger()
				.debug(Messages.ACTION_SUCCESSFUL, new Object[] { "Update" });
		getLogger().debug(Messages.FINISH_EXECUTE_ACTION,
				new Object[] { "Update" });
	}

	public Element getOldRolesAssigned(String user) {
		Element roleAssigned = null;
		String dn = null;
		String filter = null;
		Document doc = null;
		Element request = null;
		try {
			serviceNamespace = this.settings.serviceNamespace;
			organization = this.settings.organization;
			roleName = this.settings.roleName;
			rolePackage = this.settings.rolePackage;

			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { serviceNamespace });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { organization });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { roleName });
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { rolePackage });
			getLogger()
					.debug(Messages.ACTION_INPROGRESS, new Object[] { user });

			dn = "cn=organizational users,o=" + organization
					+ ",cn=cordys,cn=DevInst2,o=alahli.com";
			getLogger().debug(Messages.ACTION_INPROGRESS, new Object[] { dn });
			filter = "&(objectclass=busorganizationaluser)(&(!(cn=SYSTEM))(!(cn=anonymous))(!(cn=wcpLicUser)))(|(description=*"
					+ user + "*)(&(!(description=*))(cn=*" + user + "*)))";
			getLogger().debug(Messages.ACTION_INPROGRESS,
					new Object[] { filter });
			if (null != user && !("").equals(user)) {
				getLogger().debug(Messages.START_EXECUTE_ACTION,
						new Object[] { "SearchLDAP" });
				doc = getDomNodeManager().getDocument();
				request = doc.createElementNS(
						"http://schemas.cordys.com/1.0/ldap", "root");
				DomUtil.createAndAppendTextElement(request, "dn", dn);
				DomUtil.createAndAppendTextElement(request, "scope", "1");
				DomUtil.createAndAppendTextElement(request, "filter", filter);
				DomUtil.createAndAppendTextElement(request, "sort", "ascending");
				DomUtil.createAndAppendTextElement(request, "sortBy", "");
				DomUtil.createAndAppendTextElement(request, "returnValues",
						"false");
				DomUtil.createAndAppendTextElement(request, "return", "");
				getLogger().debug(Messages.SOAP_REQUEST_PREPARED,
						new Object[] { "SearchLDAP", request });
				
				roleAssigned = createAndSendSOAPRequest(request,
						serviceNamespace, "SearchLDAP");
				getLogger().debug(Messages.SOAP_RESPONSE_RECEIVED,
						new Object[] { "SearchLDAP" });
				

			} else
				BaseTask.printMessage("Mandatory values like dn and user missing to fetch the roles assigned");
		} catch (Exception exp) {
			BaseTask.printMessage("Exception occured while fetching the roles assigned already: "
					+ exp.getMessage());
			getLogger().debug(Messages.ACTION_FAILED,
					new Object[] { exp.getMessage() });
			throw new RoleAssignmentException(Messages.SOAP_FAULT_RECEIVED,
					new Object[] { exp.getMessage() });
		}
		getLogger().debug(Messages.ACTION_SUCCESSFUL,
				new Object[] { "SearchLDAP" });
		getLogger().debug(Messages.FINISH_EXECUTE_ACTION,
				new Object[] { "SearchLDAP" });
		return roleAssigned;
	}
}

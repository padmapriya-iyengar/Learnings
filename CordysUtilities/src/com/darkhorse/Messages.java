package com.darkhorse;


import com.eibus.localization.IStringResource;
import com.eibus.tools.internal.MessageBundle;
import com.eibus.tools.internal.MessageBundleGenerator;
import com.eibus.tools.internal.MessageText;

@MessageBundle(value = "Cordys.Deployment.Ant.Messages", idConversion = true)
public class Messages {
	private static int s_fieldIndexer = 0;

	private static IStringResource getMessage() {
		return MessageBundleGenerator.getMessage(Messages.class,
				s_fieldIndexer++);
	}

	@MessageText("An unexpected error occurred: ''{0}''")
	public static final IStringResource UNEXPECTED_EXCEPTION = getMessage();
	
	@MessageText("No value found for property ''{0}''")
	public static final IStringResource NO_VALUE_FOR_PROPERTY = getMessage();
	
	@MessageText("No service container available with the details")
	public static final IStringResource NO_CONTAINER_AVAILABLE = getMessage();
	
	@MessageText("Not authorized to perform the action ''{0}''")
	public static final IStringResource NO_VALID_ACTION = getMessage();
	
	@MessageText("Successfully performed the action ''{0}''")
	public static final IStringResource ACTION_SUCCESSFUL = getMessage();
	
	@MessageText("Failed in performing the action ''{0}''")
	public static final IStringResource ACTION_FAILED = getMessage();
	
	@MessageText("SOAP Fault received, message ''{0}''")
	public static final IStringResource SOAP_FAULT_RECEIVED = getMessage();

	@MessageText("The SOAP Request prepared for the method ''{0}'' is ''{1}''")
	public static final IStringResource SOAP_REQUEST_PREPARED = getMessage();

	@MessageText("The SOAP Response received for the method ''{0}''")
	public static final IStringResource SOAP_RESPONSE_RECEIVED = getMessage();

	@MessageText("Starting to execute action ''{0}''")
	public static final IStringResource START_EXECUTE_ACTION = getMessage();

	@MessageText("Finished execute the action ''{0}''")
	public static final IStringResource FINISH_EXECUTE_ACTION = getMessage();
	
	@MessageText("Container action in progress, value is ''{0}''")
	public static final IStringResource ACTION_INPROGRESS = getMessage();
}

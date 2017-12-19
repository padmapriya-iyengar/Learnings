package com.darkhorse.containeraction.ant.exception;

import com.eibus.localization.IStringResource;
import com.eibus.localization.exception.LocalizableRuntimeException;

public class ContainerActionException extends LocalizableRuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1687768606874564563L;
	public ContainerActionException(IStringResource message, Object[] insertions)
	{
		super(message, insertions);
	}
	public ContainerActionException(Throwable cause, IStringResource message, Object[] insertions)
	{
		super(cause, message, insertions);
	}
	public ContainerActionException(Throwable cause)
	{
		super(cause);
	}
}

package com.darkhorse.groupconfigupdate.ant.exception;

import com.eibus.localization.IStringResource;
import com.eibus.localization.exception.LocalizableRuntimeException;

public class ConfigUpdateException extends LocalizableRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1675676865787697764L;

	public ConfigUpdateException(IStringResource message, Object[] insertions) {
		super(message, insertions);
	}

	public ConfigUpdateException(Throwable cause, IStringResource message,
			Object[] insertions) {
		super(cause, message, insertions);
	}

	public ConfigUpdateException(Throwable cause) {
		super(cause);
	}
}

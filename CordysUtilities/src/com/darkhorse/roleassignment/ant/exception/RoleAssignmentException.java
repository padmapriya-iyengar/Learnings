package com.darkhorse.roleassignment.ant.exception;

import com.eibus.localization.IStringResource;
import com.eibus.localization.exception.LocalizableRuntimeException;

public class RoleAssignmentException extends LocalizableRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1534654676875687678L;

	public RoleAssignmentException(IStringResource message, Object[] insertions) {
		super(message, insertions);
	}

	public RoleAssignmentException(Throwable cause, IStringResource message,
			Object[] insertions) {
		super(cause, message, insertions);
	}

	public RoleAssignmentException(Throwable cause) {
		super(cause);
	}
}

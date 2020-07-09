package com.repast.core.exception;

public class FrameworkDaoException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameworkDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameworkDaoException(String message) {
		this(message,null);
	}

	public FrameworkDaoException(Throwable cause) {
		this("",cause);
	}
}

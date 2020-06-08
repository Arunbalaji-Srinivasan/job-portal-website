package com.neu.jobsearch.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserException(String msg) {
		super("User Exception: " + msg);
	}

	public UserException(String msg, Throwable cause) {
		super("User Exception: " + msg, cause);
	}

}

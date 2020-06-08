package com.neu.jobsearch.exception;

public class OrganizationException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrganizationException(String msg) {
		super("Organization Exception: " + msg);
	}

	public OrganizationException(String msg, Throwable cause) {
		super("Organization Exception: " + msg, cause);
	}

}

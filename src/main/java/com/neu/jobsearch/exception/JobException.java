package com.neu.jobsearch.exception;

public class JobException extends Exception {

	private static final long serialVersionUID = 1L;

	public JobException(String msg) {
		super("Job Exception: " + msg);
	}

	public JobException(String msg, Throwable cause) {
		super("Job Exception: " + msg, cause);
	}

}

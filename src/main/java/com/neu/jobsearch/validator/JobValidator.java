package com.neu.jobsearch.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.jobsearch.model.JobList;

public class JobValidator implements Validator {

	public boolean supports(@SuppressWarnings("rawtypes") Class c) {
		return c.equals(JobList.class);
	}

	public void validate(Object object, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "jobTitle", "error.invalid.job", "Please enter Job name!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "jobDescription", "error.invalid.job", "Please enter Job description!");
	}

}

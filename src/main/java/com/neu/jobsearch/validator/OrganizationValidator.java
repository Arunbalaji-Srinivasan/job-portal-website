package com.neu.jobsearch.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.jobsearch.model.Organization;

public class OrganizationValidator implements Validator {

	public boolean supports(@SuppressWarnings("rawtypes") Class c) {
		return c.equals(Organization.class);
	}

	public void validate(Object object, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "orgName", "error.invalid.org", "Please enter Organization name!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "orgAddress", "error.invalid.org",
				"Please enter Organization address!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "orgDescription", "error.invalid.org",
				"Please enter Organization description!");
	}

}

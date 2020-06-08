package com.neu.jobsearch.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.jobsearch.dao.UserDAO;
import com.neu.jobsearch.exception.UserException;
import com.neu.jobsearch.model.Email;
import com.neu.jobsearch.model.User;

public class UserValidator implements Validator {

	public boolean supports(@SuppressWarnings("rawtypes") Class c) {
		return c.equals(User.class);
	}

	public void validate(Object object, Errors err) {
		User user = (User) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "firstName", "error.invalid.user", "Please enter First Name!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "lastName", "error.invalid.user", "Please enter Last Name!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "username", "error.invalid.user", "Please enter a Username!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password", "error.invalid.password", "Please enter a password!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "email.emailAddress", "error.invalid.email.emailAddress",
				"Please enter your e-mail address!");
		String userNm = user.getUsername();
		String emailAddr = user.getEmail().getEmailAddress();
		UserDAO userdao = new UserDAO();
		try {
			User userName = userdao.fetchByUsername(userNm);
			if (userName != null) {
				err.rejectValue("username", "error.invalid.user", "Username has already been taken! Please try again!");
			}
			Email email = userdao.fetchByEmail(emailAddr);
			if (email != null) {
				err.rejectValue("email.emailAddress", "error.invalid.email.emailAddress",
						"This email has already been registered!");
			}
		} catch (UserException e) {
			System.out.println("User Exception: " + e.getMessage());
		}

	}
}

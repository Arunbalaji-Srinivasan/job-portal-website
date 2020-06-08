package com.neu.jobsearch.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authorization/*")
public class RoleController {

	@RequestMapping(value = "/authorization/error.htm", method = RequestMethod.GET)
	protected ModelAndView postJobsError(HttpServletRequest request) throws Exception {
		return new ModelAndView("authorizationError");
	}

	@RequestMapping(value = "/authorization/JobUpdateError.htm", method = RequestMethod.GET)
	protected ModelAndView jobUpdateError(HttpServletRequest request) throws Exception {
		return new ModelAndView("updateJobError");
	}
}

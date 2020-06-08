package com.neu.jobsearch.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.jobsearch.dao.OrganizationDAO;
import com.neu.jobsearch.dao.JobDAO;
import com.neu.jobsearch.dao.UserDAO;
import com.neu.jobsearch.exception.OrganizationException;
import com.neu.jobsearch.model.Organization;
import com.neu.jobsearch.validator.OrganizationValidator;

@Controller
@RequestMapping("/organization/*")
public class OrganizationController {

	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;

	@Autowired
	@Qualifier("organizationValidator")
	OrganizationValidator validator;

	@Autowired
	@Qualifier("jobDao")
	JobDAO jobDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/organization/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerOrganization() throws Exception {
		return new ModelAndView("registerCompany", "organization", new Organization());
	}

	@RequestMapping(value = "/organization/register.htm", method = RequestMethod.POST)
	protected ModelAndView regNewOrganization(HttpServletRequest request,
			@ModelAttribute("organization") Organization organization, BindingResult solution) throws Exception {
		validator.validate(organization, solution);
		if (solution.hasErrors()) {
			return new ModelAndView("registerCompany", "organization", organization);
		}
		try {
			return new ModelAndView("companyAddSuccess", "organization", orgDao.register(organization));
		} catch (OrganizationException e) {
			return new ModelAndView("error", "errorMessage", "An error occurred while registration!");
		}
	}

	@RequestMapping(value = "/organization/delete.htm", method = RequestMethod.POST)
	protected ModelAndView deleteOrganization(HttpServletRequest request) throws Exception {
		orgDao.delete(orgDao.getById(Integer.parseInt(request.getParameter("orgID"))));
		return new ModelAndView("companyDeleteSuccess", "organizations", orgDao.list());
	}
}

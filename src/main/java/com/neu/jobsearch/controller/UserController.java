package com.neu.jobsearch.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.neu.jobsearch.exception.UserException;
import com.neu.jobsearch.model.Organization;
import com.neu.jobsearch.model.User;
import com.neu.jobsearch.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;

	@Autowired
	@Qualifier("jobDao")
	JobDAO jobDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	protected String goToHome(HttpServletRequest request) throws Exception {
		return "home";
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			User user = userDao.get(request.getParameter("username"), request.getParameter("password"));
			if (user == null) {
				session.setAttribute("errorMessage", "Invalid username and password! Please try again!");
				return new ModelAndView("error");
			}
			session.setAttribute("user", user);
			String title = user.getRole().trim();
			if (title.equals("admin")) {
				return new ModelAndView("adminHome", "organizations", orgDao.list());
			} else if (title.equals("employer")) {
				return new ModelAndView("employerHome", "jobs", jobDao.listCompanies(user.getOrganization()));
			} else if (title.equals("student")) {
				return new ModelAndView("studentHome", "jobs", jobDao.list());
			} else
				return null;
		} catch (UserException e) {
			session.setAttribute("errorMessage", "An error occurred while logging in");
			return new ModelAndView("error");
		}
	}

	@RequestMapping(value = "/user/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerNewUser() throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("organizations", orgDao.list());
		modelMap.put("user", new User());
		return new ModelAndView("registerUser", modelMap);
	}

	@RequestMapping(value = "/user/admin.htm", method = RequestMethod.GET)
	protected ModelAndView adminHome() throws Exception {
		return new ModelAndView("adminHome", "organizations", orgDao.list());	
	}
	
	@RequestMapping(value = "/user/student.htm", method = RequestMethod.GET)
	protected ModelAndView studentHome() throws Exception {
		return new ModelAndView("studentHome", "jobs", jobDao.list());	
	}

	@RequestMapping(value = "/user/employer.htm", method = RequestMethod.GET)
	protected ModelAndView employerHome(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		return new ModelAndView("employerHome", "jobs", jobDao.listCompanies(user.getOrganization()));	
	}

	

	@RequestMapping(value = "/user/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult solution) throws Exception {
		validator.validate(user, solution);
		if (solution.hasErrors()) {
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("organizations", orgDao.list());
			modelMap.put("user", user);
			return new ModelAndView("registerUser", modelMap);
		}
		try {
			String role = request.getParameter("userRole");
			user.setRole(role);
			String org = request.getParameter("userOrganization");
			Organization organization = orgDao.get(org);
			user.setOrganization(organization);
			User userReg = userDao.register(user);
			request.getSession().setAttribute("user", userReg);
			return new ModelAndView("registerSuccess", "user", userReg);
		} catch (UserException e) {
			return new ModelAndView("error", "errorMessage", "An error occurred while logging in");
		}
	}

}

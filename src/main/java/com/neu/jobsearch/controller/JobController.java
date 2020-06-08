package com.neu.jobsearch.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.neu.jobsearch.exception.JobException;
import com.neu.jobsearch.model.Organization;
import com.neu.jobsearch.model.JobList;
import com.neu.jobsearch.model.User;
import com.neu.jobsearch.validator.JobValidator;

@Controller
@RequestMapping("/job/*")
public class JobController {

	@Autowired
	@Qualifier("jobDao")
	JobDAO jobDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;

	@Autowired
	@Qualifier("jobValidator")
	JobValidator validator;

	@Autowired
	private JavaMailSender mailSender;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/job/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewJob(HttpServletRequest request, @ModelAttribute("joblist") JobList jobList,
			BindingResult solution) throws Exception {

		validator.validate(jobList, solution);
		if (solution.hasErrors()) {
			return new ModelAndView("postJob", "joblist", jobList);
		}
		try {
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			jobList.setOrganization(user.getOrganization());
			jobList.setPostedName(user.getFirstName());
			jobList.setPostedID(user.getUsername());
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("jobs", jobDao.listCompanies(user.getOrganization()));
			modelMap.put("joblist", jobDao.register(jobList));
			return new ModelAndView("jobPostSuccess", modelMap);
		} catch (JobException e) {
			return new ModelAndView("error", "errorMessage", "An error occurred while adding a job");
		}
	}
	
	@RequestMapping(value = "/job/viewAllPostings.htm", method = RequestMethod.GET)
	protected ModelAndView viewAllPostings(HttpServletRequest request) throws Exception {
		return new ModelAndView("viewAllPostings", "jobs", jobDao.list());
	}

	@RequestMapping(value = "/job/register.htm", method = RequestMethod.GET)
	protected ModelAndView postJobs(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("orgName", user.getOrganization().getOrgName());
		modelMap.put("joblist", new JobList());
		return new ModelAndView("postJob", modelMap);
	}

	@RequestMapping(value = "/job/update.htm", method = RequestMethod.POST)
	protected ModelAndView updateJob(HttpServletRequest request) throws Exception {
		JobList job = jobDao.fetchJobs(Integer.parseInt(request.getParameter("jobId")));
		job.setJobTitle(request.getParameter("jobTitle"));
		job.setJobDescription(request.getParameter("jobDescription"));
		jobDao.update(job);
		return new ModelAndView("jobUpdateSuccess", "jobList", job);
	}

	@RequestMapping(value = "/job/update.htm", method = RequestMethod.GET)
	protected ModelAndView updateJobLanding(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		JobList job = jobDao.fetchJobs(Integer.parseInt(request.getParameter("jobId")));
		User user = (User) session.getAttribute("user");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("orgName", user.getOrganization().getOrgName());
		modelMap.put("jobList", job);
		return new ModelAndView("updateJob", modelMap);
	}

	@RequestMapping(value = "/job/add.htm", method = RequestMethod.POST)
	protected ModelAndView applyJob(HttpServletRequest request) throws Exception {
		try {
			int jobID = Integer.parseInt(request.getParameter("jobID"));
			boolean flag = false;
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			for (JobList list : user.getJoblists()) {
				if (list.getJobID() == jobID) {
					flag = true;
				}
			}
			if (flag) {
				return new ModelAndView("jobAlreadyApplied", "jobs", null);
			} else {
				JobList job = jobDao.fetchJobs(jobID);
				userDao.update(user, job);
				job.getUsers().add(user);
				jobDao.update(job);
				SimpleMailMessage email = new SimpleMailMessage();
				email.setTo(user.getEmail().getEmailAddress());
				email.setSubject("We got your application!");
				email.setText("Hello " + user.getFirstName() + "," + "\n"
						+ "\nThanks for  submitting your application for the " + job.getJobTitle() + " position." + "\n"
						+ "We’re thrilled that you’re interested in contributing to our mission to bring the world together thorugh software!"
						+ "\n" + "\nJob Description : " + job.getJobDescription() + "\n" + ""
						+ "\nWe look forward to reviewing your profile. We’ll be in touch regarding next steps.\n" + ""
						+ "\n" + "\nRegards," + "\nJob Recruiting");

				mailSender.send(email);
				
				Map<String, Object> modelMap = new HashMap<String, Object>();
				modelMap.put("jobs", jobDao.listCompanies(user.getOrganization()));
				modelMap.put("joblist", job);
				flag = false;
				return new ModelAndView("jobApplySuccess", modelMap);
			}
		} catch (JobException e) {
			return new ModelAndView("error", "errorMessage", "An error occurred while adding a job");
		}
	}

	@RequestMapping(value = "/job/search.htm", method = RequestMethod.GET)
	protected ModelAndView findJobLanding(HttpServletRequest request) throws Exception {
		return new ModelAndView("findJobs");
	}

	@RequestMapping(value = "/job/search.htm", method = RequestMethod.POST)
	protected ModelAndView findJobs(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView();
		String searchString = request.getParameter("searchString");
		String searchUsing = request.getParameter("searchUsing");
		if (searchUsing.equalsIgnoreCase("jobTitle")) {
			model.addObject("jobs", jobDao.listJobsByName(searchString));
		} else if (searchUsing.equalsIgnoreCase("organization")) {
			Organization organization = orgDao.get(searchString);
			if (organization == null) {
				return new ModelAndView("findJobError");
			}
			if (jobDao.listCompanies(organization).isEmpty()) {
				return new ModelAndView("findJobsEmpty");
			}
			model.addObject("jobs", jobDao.listCompanies(organization));
		}
		model.setViewName("findJobSuccess");
		return model;
	}

	@RequestMapping(value = "/job/applied.htm", method = RequestMethod.GET)
	protected ModelAndView appliedJob(HttpServletRequest request) throws Exception {
		return new ModelAndView("jobsApplied");
	}

}

package com.neu.jobsearch.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neu.jobsearch.dao.JobDAO;
import com.neu.jobsearch.model.JobList;
import com.neu.jobsearch.model.User;

public class EmployerInterceptor extends HandlerInterceptorAdapter {

	String error;

	public String getErrorPage() {
		return error;
	}

	public void setErrorPage(String error) {
		this.error = error;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		JobDAO jobDao = new JobDAO();
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (user.getRole().equalsIgnoreCase("employer")) {
				if (request.getRequestURI().equalsIgnoreCase("/controller/job/update.htm")) {
					int id = Integer.parseInt(request.getParameter("jobId"));
					JobList jobList = jobDao.fetchJobs(id);
					if (user.getUsername().equals(jobList.getPostedID())) {
						System.out.println("User Authorized to change the job");
						return true;
					} else {
						response.sendRedirect(request.getContextPath() + "/authorization/JobUpdateError.htm");
						return false;
					}
				}
				System.out.println("Inside employer interceptor");
				return true;
			}
		}
		System.out.println("Auth failed, user is not an employer");
		response.sendRedirect(request.getContextPath() + "/authorization/error.htm");
		return false;

	}

}

package com.neu.jobsearch.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neu.jobsearch.model.User;

public class AdministratorInterceptor extends HandlerInterceptorAdapter {

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
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (user.getRole().equalsIgnoreCase("admin")) {
				System.out.println("Inside admin interceptor");
				return true;
			}
		}
		System.out.println("Auth failed, user is not an admin");
		response.sendRedirect(request.getContextPath() + "/authorization/error.htm");
		return false;
	}

}

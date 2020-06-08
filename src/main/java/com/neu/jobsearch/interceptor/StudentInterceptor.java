package com.neu.jobsearch.interceptor;

import com.neu.jobsearch.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class StudentInterceptor extends HandlerInterceptorAdapter {

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
			if (user.getRole().equalsIgnoreCase("student")) {
				System.out.println("Inside Student Interceptor");
				return true;
			}
		}
		System.out.println("Auth failed, user is not student");
		response.sendRedirect(request.getContextPath() + "/authorization/error.htm");
		return false;

	}

}

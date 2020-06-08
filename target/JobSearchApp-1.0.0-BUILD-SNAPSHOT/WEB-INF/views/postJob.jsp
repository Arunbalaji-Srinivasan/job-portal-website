<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post Jobs</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="orgName" value="${orgName}" />
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/user/employer.htm">Employer
			Home</a>
		<h2>Post a New Job for ${orgName}</h2>
		<form:form action="${contextPath}/job/register.htm"
			modelAttribute="joblist" method="post">
			<table class="login">
				<tr>
					<td>Job Title</td>
					<td><form:input path="jobTitle" size="30" />
						<font color="red"><form:errors path="jobTitle" /></font></td>
				</tr>
				<tr>
					<td>Job Description</td>
					<td><form:input path="jobDescription" size="30" />
						<font color="red"><form:errors path="jobDescription" /></font></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-primary" type="submit"
						value="Post Job" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
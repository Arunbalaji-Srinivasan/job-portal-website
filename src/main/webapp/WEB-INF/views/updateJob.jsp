<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Job Posting</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="orgName" value="${orgName}" />
	<c:set var="job" value="${jobList}" />
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/user/employer.htm">Employer
			Home</a>
		<h4>Update Job Posting for ${orgName}</h4>
		<form:form action="${contextPath}/job/update.htm" method="post">
			<table class="login">
				<tr>
					<td>Job ID</td>
					<td><input type="text" name="jobId" value="${job.jobID}"
						readonly></td>
				</tr>
				<tr>
					<td>Job Title</td>
					<td><input type="text" name="jobTitle" value="${job.jobTitle}"
						required="required"></td>
				</tr>
				<tr>
					<td>Job Description</td>
					<td><input type="text" name="jobDescription" value="${job.jobDescription}"
						required="required"></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-primary" type="submit"
						value="Update Posting" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
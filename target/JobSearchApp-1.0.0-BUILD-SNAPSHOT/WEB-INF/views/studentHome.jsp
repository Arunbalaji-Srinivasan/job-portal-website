<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Home</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="joblist" value="${jobs}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/job/applied.htm">
			View Applied Jobs</a> <a class="btn btn-primary"
			href="${contextPath}/job/search.htm">Find a Job</a> <a
			class="btn btn-danger" href="${contextPath}">Logout</a>
		<h3>Welcome ${user.firstName}!</h3>
		<table class="login" border="3">
			<tr>
				<th>Job ID</th>
				<th>Organization</th>
				<th>Job Title</th>
				<th>Job Description</th>
				<th>Job Posted By</th>
				<th>Job Posted Date</th>
				<th>Apply</th>
			</tr>
			<c:forEach items="${joblist}" var="job">
				<form:form action="${contextPath}/job/add.htm"
					modelAttribute="joblist" method="post">
					<tr>
						<td><input type="text" name="jobID" value="${job.jobID}"
							readonly></td>
						<td>${job.organization.orgName}</td>
						<td>${job.jobTitle}</td>
						<td>${job.jobDescription}</td>
						<td>${job.postedName}</td>
						<td>${job.postedDate}</td>
						<td><input class="btn btn-primary" type="submit"
							name="action" value="Apply"></td>
					</tr>
				</form:form>
			</c:forEach>
		</table>
	</div>
</body>

</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Postings</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="joblist" value="${jobs}" />
	<a class="btn btn-primary"
		style="margin-left: 650px; margin-top: 20px;"
		href="${contextPath}/user/login.htm">Click here to start applying</a>
	<br>
	<br>
	<center>
		<h4>All Active Postings</h4>
	</center>
	<table class="allJobs">
		<tr>
			<th>Job ID</th>
			<th>Organization</th>
			<th>Job Title</th>
			<th>Job Description</th>
			<th>Posted By</th>
			<th>Posted Date</th>
		</tr>
		<c:forEach items="${joblist}" var="job">

			<form:form action="${contextPath}/job/add.htm" modelAttribute="joblist"
				method="post">
				<tr>
					<td>${job.jobID}</td>
					<td>${job.organization.orgName}</td>
					<td>${job.jobTitle}</td>
					<td>${job.jobDescription}</td>
					<td>${job.postedName}</td>
					<td>${job.postedDate}</td>
				</tr>
			</form:form>
		</c:forEach>
	</table>
</body>
</html>
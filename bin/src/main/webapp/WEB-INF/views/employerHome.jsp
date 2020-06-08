<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employer Home</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="joblist" value="${jobs}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/job/register.htm">Post
			a new Job for ${user.organization.orgName}</a>
		<a class="btn btn-danger" href="${contextPath}"> Logout</a>
		<h1>${user.organization.orgName} Jobs currently posted!</h1>
		<table class="login" border="2">
			<tr>
				<th>Job ID</th>
				<th>Job Title</th>
				<th>Job Description</th>
				<th>Posted By Name</th>
				<th>Posted By Username</th>
				<th>Posted Date</th>
				<th>Update Job</th>
				<!-- <th> Delete Job</td> -->
			</tr>
			<c:forEach items="${joblist}" var="job">
				<form:form action="${contextPath}/job/update.htm"
					modelAttribute="joblist" method="get">
					<tr>
						<td><input type="text" name="jobId" value="${job.jobID}"
							readonly></td>
						<td>${job.jobTitle}</td>
						<td>${job.jobDescription}</td>
						<td>${job.postedName}</td>
						<td>${job.postedID}</td>
						<td>${job.postedDate}</td>
						<td><input class="btn btn-primary" type="submit"
							name="action" value="Update"></td>
						<!-- <td><input type="submit" name="action" value="Delete"></td> -->
					</tr>
				</form:form>
			</c:forEach>
		</table>
	</div>
</body>
</html>
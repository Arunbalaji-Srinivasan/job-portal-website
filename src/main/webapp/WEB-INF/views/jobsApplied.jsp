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
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="joblist" value="${user.joblists}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/user/student.htm">Student
			Home</a> <a class="btn btn-primary" href="${contextPath}/job/search.htm">Find
			A Job</a>
		<h3>${user.firstName}, Below are the Jobs you've applied for!</h3>
		<c:choose>
			<c:when test="${empty joblist }">
				<h2>You have not applied for any jobs yet!</h2>
			</c:when>
			<c:otherwise>
				<table class="login" border="3">
					<tr>
						<th>Job ID</th>
						<th>Organization</th>
						<th>Job Title</th>
						<th>Job Description</th>
						<th>Posted Date</th>
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
								<td>${job.postedDate}</td>
							</tr>
						</form:form>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
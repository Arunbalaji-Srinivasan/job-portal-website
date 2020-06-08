<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administrator Page</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
	<a class="btn btn-primary" href="${contextPath}/organization/register.htm">Register A
		New Organization</a>
	<a class="btn btn-danger" href="${contextPath}"> Logout</a>
	<h1>Organizations currently present!</h1>
	<table class="login" border="2">
		<tr>
			<th>Organization ID</th>
			<th>Organization Name</th>
			<th>Organization Address</th>
			<th>Organization Description</th>
			<th>Delete Organization</th>
		</tr>
		<c:forEach items="${organizations}" var="org">
			<form:form action="${contextPath}/organization/delete.htm"
				modelAttribute="joblist" method="post">
				<tr>
					<td><input type="text" name="orgID" value="${org.orgID}"
						readonly></td>
					<td>${org.orgName}</td>
					<td>${org.orgAddress}</td>
					<td>${org.orgDescription}</td>
					<td><input class="btn btn-danger" type="submit" name="action"
						value="Delete"></td>
				</tr>
			</form:form>
		</c:forEach>
	</table>
	</div>
</body>
</html>
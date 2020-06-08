<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Search App</title>

<link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
<link rel="stylesheet"
	href="<c:url value="https://www.w3schools.com/w3css/4/w3.css"/> ">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<div align="center">
	<a class="btn btn-primary" href="user/login.htm">User Login</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-primary"
		href="user/register.htm">Register</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
		class="btn btn-primary" href="job/viewAllPostings.htm">All Active
		Postings</a>
</div>
<br>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply for Job</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/user/student.htm">Student
			Home</a>
		<h2>Applied for this job Successfully!</h2>
				<h2>Please check your e-mail inbox for the confirmation mail!</h2>
	</div>
</body>
</html>
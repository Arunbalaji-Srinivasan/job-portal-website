<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authorization Error</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
	<div align="center">
		<h1>You are not authorized to access this page</h1>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<a class="btn btn-primary" href="${contextPath}">Home</a>
	</div>
</body>
</html>
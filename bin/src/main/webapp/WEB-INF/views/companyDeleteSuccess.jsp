<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Organization</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div align="center">
<a class = "btn btn-primary" href="${contextPath}/user/admin.htm" >Admin Home</a>
<h2> Organization deleted Successfully!</h2>
</div>
</body>
</html>
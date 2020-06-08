<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Search App</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}">Home</a> <a
			class="btn btn-primary" href="${contextPath}/user/register.htm">Register</a>
		<br />
		<h4>Please enter your details below to login!</h4>
		<form action="${contextPath}/user/login.htm" method="post">
			<table class="login">
				<tr>
					<td>Username:</td>
					<td><input name="username" size="30" required="required" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" size="30"
						required="required" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-primary" type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>


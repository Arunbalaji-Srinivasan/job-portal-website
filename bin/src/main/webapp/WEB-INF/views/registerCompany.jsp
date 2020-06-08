<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Organization Registration</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<h3>Register a New Organization</h3>
		<a class="btn btn-primary" href="${contextPath}/user/admin.htm">Home
			</a><br><br>
		<form:form action="${contextPath}/organization/register.htm"
			modelAttribute="organization" method="post">
			<table class="login">
				<tr>
					<td>Organization Name</td>
					<td><form:input path="orgName" size="30"
							required="required" /> <font color="red"><form:errors
								path="orgName" /></font></td>
				</tr>
				<tr>
					<td>Organization Address</td>
					<td><form:input path="orgAddress" size="30"
							required="required" /> <font color="red"><form:errors
								path="orgAddress" /></font></td>
				</tr>
				<tr>
					<td>Organization Description</td>
					<td><form:input path="orgDescription" size="30"
							required="required" /> <font color="red"><form:errors
								path="orgDescription" /></font></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-primary" type="submit"
						value="Register" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
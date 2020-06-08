<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}">Home</a>
		<h2>New User? Register below!</h2>
		<form:form action="${contextPath}/user/register.htm"
			modelAttribute="user" method="post">
			<table class="login">
				<tr>
					<td>First Name:</td>
					<td><form:input path="firstName" size="30" required="required" />
						<font color="red"><form:errors path="firstName" /></font></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lastName" size="30" required="required" />
						<font color="red"><form:errors path="lastName" /></font></td>
				</tr>
				<tr>
					<td>User Name:</td>
					<td><form:input path="username" size="30" required="required" />
						<font color="red"><form:errors path="username" /></font></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" size="30"
							required="required" /> <font color="red"><form:errors
								path="password" /></font></td>
				</tr>
				<tr>
					<td>Email Id:</td>
					<td><form:input path="email.emailAddress" size="30"
							type="email" /> <font color="red"><form:errors
								path="email.emailAddress" /></font></td>
				</tr>
				<tr>
					<td>Role :</td>
					<td><select name="myRole">
							<option value="employer">Employer</option>
							<option value="student">Student</option>
							<option value="admin">Admin</option>
					</select></td>
				</tr>
				<tr>
					<td>Organization :</td>
					<td><select name="myOrganization">
							<c:forEach var="item" items="${organizations}">
								<option value="${item.orgName}">${item.orgName}</option>
							</c:forEach>
					</select></td>
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
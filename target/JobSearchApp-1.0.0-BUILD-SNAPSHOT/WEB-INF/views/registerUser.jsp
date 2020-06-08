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
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#userRole").change(function(){
       var textselected =  document.getElementById("userRole").value ;
       if(textselected === "employer")
        $('#userOrganization').show();
       else
        $('#userOrganization').hide(); 
    });
  });
</script>
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
					<td><form:input path="firstName" size="30" />
						<font color="red"><form:errors path="firstName" /></font></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lastName" size="30" />
						<font color="red"><form:errors path="lastName" /></font></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><form:input path="username" minlength="5" size="30" />
						<font color="red"><form:errors path="username" /></font></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" minlength="5" size="30"
						 /> <font color="red"><form:errors
								path="password" /></font></td>
				</tr>
				<tr>
					<td>Email ID:</td>
					<td><form:input path="email.emailAddress" size="30"
							type="email" /> <font color="red"><form:errors
								path="email.emailAddress" /></font></td>
				</tr>
				<tr>
					<td>Role :</td>
					<td><select id="userRole" name="userRole">
							<option value="employer">Employer</option>
							<option value="student">Student</option>
							<option value="admin">Admin</option>
					</select></td>
				</tr>
				<tr id="userOrganization">
					<td>Organization :</td>
					<td><select name="userOrganization">
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
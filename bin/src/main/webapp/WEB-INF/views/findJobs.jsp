<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search for Jobs</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div align="center">
		<a class="btn btn-primary" href="${contextPath}/user/student.htm">Student
			Home</a>
		<h3>Search for Jobs</h3>
		<form action="${contextPath}/job/search.htm" method="post">
			<table class="login">
				<tr>
					<td>Enter the Search String</td>
					<td><input type="text" name="searchString" required /></td>
				</tr>
				<tr>
					<td>Search using:</td>
					<td><input type="radio" name="searchUsing" value="organization"
						checked="checked"> Organization <input type="radio"
						name="searchUsing" value="jobTitle">Job Title</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="hidden" name="action" value="searchuser" />
						<input class="btn btn-primary" type="submit" name="search"
						value="Search Job" /></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>
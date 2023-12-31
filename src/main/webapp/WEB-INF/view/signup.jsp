<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
	<h1>Signup page</h1>
	<form:form action="process-signup" method="POST" modelAttribute="signupdto">
		First Name : <form:input path="firstName"/>
		<br />
		Last Name : <form:input path="lastName"/>
		<br />
		Gender : <form:input path="gender"/>
		<br />
		Cell Phone : <form:input path="cellPhone"/>
		<br />
		Username : <form:input path="username"/>
		<br />
		Password : <form:password path="password"/>
		<br />
		Repeat password : <form:password path="repeatedPassword"/>
		<br />
		E-Mail: <form:input path="email"/>
		<br />
		repeat E-Mail: <form:input path="repeatedEmail"/>
		<br />
		Street: <form:input path="addressStreet"/>
		<br />
		House Number: <form:input path="addressHouseNo"/>
		<br />
		Zip Code: <form:input path="zipCode"/>
		<br />
		Location: <form:input path="zipCodeLocation"/>
		<br />
		<input type="submit" value="signup">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form:form>
</body>
</html>
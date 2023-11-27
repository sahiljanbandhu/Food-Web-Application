<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center> <h1> Customer Details </h1> </center>

	<form:form action="saveCustomer" modelAttribute="customermodel">
      
      Name <form:input path="customerName" />
		<br>
      
      Mobile No <form:input path="mobileNo" />
		<br>
		City <form:input path="city" />
		<br>

		<input type="submit">



	</form:form>
</body>
</html>
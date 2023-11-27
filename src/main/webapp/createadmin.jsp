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
<center>
<h2>Create Admin</h2>
	<form:form action="saveAdmin" modelAttribute="adminmodel">
      
      Email <form:input path="email" />
		<br>
      
      Password <form:input path="password" />
		<br>
		<input type="submit">



	</form:form>
</center>
</body>
</html>
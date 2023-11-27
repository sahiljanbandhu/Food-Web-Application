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
	<%
	Object id = session.getAttribute("adminid");
	%>
	<form:form action="saveStaff" modelAttribute="staffmodel">
Name <form:input path="staffName" />
		<br>
Email <form:input path="email" />
		<br>
Password <form:input path="password" />
		<br>
Admin Id <input type="number" value="<%=id%>" readonly="readonly">
		<br>
		<input type="submit">
	</form:form>
</body>
</html>
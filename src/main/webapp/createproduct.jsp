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

	<form:form action="saveProduct" modelAttribute="productmodel">
      
      FoodName <form:input path="foodName" />
		<br>
      
      Description <form:input path="description" />
		<br>
		Cost <form:input path="cost" />
		<br>
		FoodType <form:input path="foodType" />
		<br>
		Admin Id <input type="text" readonly="readonly" value="<%=id%>">
		<input type="submit">



	</form:form>
</body>
</html>
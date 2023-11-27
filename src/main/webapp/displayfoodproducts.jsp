<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<table border="3">

		<tr>

			<th>ID</th>
			<th>FOODNAME</th>
			<th>DESCRIPTION</th>
			<th>COST</th>
			<th>FOODTYPE</th>
			<th colspan="2">ACTION</th>
		</tr>
		<c:forEach items="${allproducts}" var="foodProduct">
			<tr>
				<td>${foodProduct.id}</td>
				<td>${foodProduct.foodName}</td>
				<td>${foodProduct.description}</td>
				<td>${foodProduct.cost}</td>
				<td>${foodProduct.foodType}</td>
				<td> <a href="deleteProduct?id=${foodProduct.id}"> <input type="submit" value="DELETE"> </a> </td>
				<td> <a href="update?id=${foodProduct.id}"> <input type="submit" value="UPDATE"> </a> </td>
				
			</tr>



		</c:forEach>
		
		

	</table>
</body>
</html>
<%@page import="java.util.ArrayList"%>
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
	<%
	//ArrayList al = (ArrayList) session.getAttribute("myitems");
	int count = 0;
	%>
	<table border="4">
		<tr>
			<th>S.No</th>
			<th>Food</th>
			<th>Cost</th>
			<th>Quantity</th>
			<th>Total Amount</th>
			<th>Total Amount</th>
		</tr>
		<%
		int value = 0;
		%>
		<c:forEach items="${myitems }" var="item">
		<tr>

			<td><%=++count%></td>
			<td>${item.itemName}</td>
			<td>${item.price}</td>
			<td>${item.quantity}</td>
			<td>${item.totalCost}</td>
			<td><a href="deleteItem?value=<%=value%>"> <input type="submit"
					value="DELETE">
			</a></td>


			</tr>
			<%value++; %>

		</c:forEach>
	</table>
	<br>
	<a href="next"> <input type="submit" value="next">
	</a>

</body>
</html>
<%@page import="com.jsp.util.BillService"%>
<%@page import="com.jsp.dto.FoodItem"%>
<%@page import="java.util.ArrayList"%>
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
	<table border="4">

		<tr>
			<th>Staff Name</th>
			<th>To Customer</th>
			<th>Order Date</th>
			<th>Time</th>

		</tr>
		<tr>

			<form:form modelAttribute="ordermodel">
				<td><form:input path="staffName" readonly="true" /></td>
				<td><form:input path="customerName" readonly="true" /></td>
				<td><form:input path="orderDate" readonly="true" /></td>
				<td><form:input path="time" readonly="true" /></td>

			</form:form>

		</tr>

	</table>
	<br>
	<%
	ArrayList<FoodItem> items = (ArrayList<FoodItem>) session.getAttribute("allitems");

	BillService billService = new BillService();
	double bill = billService.totalBill(items);
	%>
	<table border="4">
		<tr>
			<th>FoodName</th>
			<th>Quantity</th>
			<th>TotalCost</th>


		</tr>
		<%
		for (FoodItem item : items) {
		%>
		<tr>
			<td><%=item.getItemName()%></td>
			<td><%=item.getQuantity()%></td>
			<td><%=item.getTotalCost()%></td>

		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="2">Total Bill</td>
			<td colspan="2"><%=bill%></td>
		</tr>
	</table>
	<center>
		<a href="confirm"><input type="submit" value="Confirm Order">
		</a>
	</center>
</body>
</html>
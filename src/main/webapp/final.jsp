<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Order is Saved</h1>
	</center>
	
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
	<a href="refresh"> <input type="submit" value="Back To Home"> </a>
	
</body>
</html>
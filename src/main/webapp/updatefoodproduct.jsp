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


<h1>${updated}</h1>
	
	<form:form action="foodProductUpdated" modelAttribute="foodProduct">
	
	ID:<form:input path="id" />
		<br>
   Food Name:<form:input path="foodName" />
		<br>
    Food Type:<form:input path="foodType" />
		<br>
		 Description:<form:input path="description" />
		<br>
		 Cost:<form:input path="cost" />
		<br>
		<form:button>Update</form:button>
	</form:form>
</body>
</html>
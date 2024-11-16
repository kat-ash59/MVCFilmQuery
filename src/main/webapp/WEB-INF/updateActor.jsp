<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actor Information</title>
<%@  include file="bootstraphead.jsp" %>
</head>
<body>
	<div class="container">
	<h1>Actor Information</h1>
	<div class="row">
		<c:choose>
		<c:when test="${! empty actor }">
			<form action="updateActor.do">
				<br>
				<h4>Please enter the First Name and/or Last Name for the actor you wish to update the database.</h4>
				<input type="hidden" id="id" name="id" value="${actor.id}">
				<label for="firstName">First Name</label><br>
				<input type="text" id="firstName" name="firstName" value=""/><br>
				<br>
				<label for="lastName">Last Name</label><br>
				<input type="text" id="lastName" name="lastName" value=""/><br>
				<br>
				<input type="submit" value="Update the Actor's information in the Database">
			</form>
			</c:when>
		<c:otherwise>
				<h6>No such Actor found to update!</h6>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
</body>	

<head>

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
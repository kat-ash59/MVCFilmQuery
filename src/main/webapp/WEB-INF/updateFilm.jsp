<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Information</title>
<%@  include file="bootstraphead.jsp" %>
</head>
<body>
	<div class="container">
	<h1>Film Information</h1>
	<div class="row">
		<c:choose>
		<c:when test="${! empty film }">
			<form action="updateFilm.do">
				<br>
				<h4>Please enter the title and/or description for the film you wish to update the database.</h4>
				<input type="hidden" id="id" name="id" value="${film.id}">
				<label for="title">Title</label><br>
				<input type="text" id="title" name="title" value=""/><br>
				<br>
				<label for="description">Description</label><br>
				<input type="text" id="description" name="description" value=""/><br>
				<br>
				<input type="submit" value="Update the film in the Database">
			</form>
			</c:when>
		<c:otherwise>
				<h6>No such Film found to update!</h6>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
</body>	

<head>

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
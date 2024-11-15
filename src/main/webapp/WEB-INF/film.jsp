<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib uri="jakarta.tags.core" prefix="c" %>   


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Information</title>
<%@  include file="bootstraphead.jsp" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
</head>
<body>
	<div class="container">
	<div class="row">
	<c:choose>
		<c:when test="${! empty film }">
			Title: ${film.title}<br>
			Id: ${film.id}<br>
			Description: ${film.description}
			<br>
			<form action="deleteFilm.do">
				<input type="hidden" id="id" name="id" value="${film.id}" /><br>
				<input type="submit" value="Delete"/>
			</form>
			<br>
			<form action="updateGetInfo.do">
				<input type="hidden" id="id" name="id" value="${film.id}" /><br>
				<input type="submit" value="Update"/><br>
			</form>
			<br>
			<br>
			<br>
		</c:when>
		<c:otherwise>
				<h3>No such Film found!</h3>
		</c:otherwise>
	</c:choose>
	<form action="index.do">
		<input type="submit" value="Back"/><br>
	</form>
	</div>
	</div>

</body>	

<head>

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
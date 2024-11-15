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
	<h1>Film Information</h1>
	<c:choose>
		<c:when test="${! empty film }">
			Title: ${film.title}<br>
			Id: ${film.id}<br>
			Description: ${film.description}<br>
			<form action="deleteFilm.do">
				<input type="hidden" id="id" name="id" value="${film.id}" /><br>
				<input type="submit" value="Delete"/>
			</form>
		</c:when>
		<c:otherwise>
				<h3>No such Film found!</h3>
		</c:otherwise>
	</c:choose>
</body>	

<head>

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
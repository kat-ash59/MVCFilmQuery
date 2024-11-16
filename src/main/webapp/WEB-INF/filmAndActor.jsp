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
	<h3>Film Information</h3>
	<c:choose>
	<c:when test="${! empty film }">
			Title: ${film.title}<br>
			Id: ${film.id}<br>
			Description: ${film.description}<br>
			Release Year: ${film.releaseYear}<br>
			Language: ${film.language}<br>
			Rental Rate: ${film.rentalRate}<br>
			Category: ${film.category}<br>
			<br>
			<h6>Actors in the film</h6>
			<c:forEach items="${film.actors}" var="actor">
				Actor id: ${actor.id}
				<ul>
			       	<li>Actor First Name: ${actor.firstName}</li>
			    	<li>Actor Last Name: ${actor.lastName}</li>
				</ul>
				<br>
			</c:forEach>
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
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
			Description: ${film.description}
			<br>
			<div id="outer">
				<div class="inner">
				<form action="deleteFilm.do">
					<input type="hidden" id="id" name="id" value="${film.id}" />
					<input type="submit" value="Delete"/>
				</form>
				</div>
				<br>
				<div class="inner">
				<form action="updateFilmGetInfo.do">
					<input type="hidden" id="id" name="id" value="${film.id}" />
					<input type="submit" value="Update"/><br>
				</form>
				</div>
				<br>
				<div class="inner">
				<form action="findFilmAndActorsByFilmId.do">
					<input type="hidden" id="id" name="id" value="${film.id}" />
					<input type="submit" value="Get Detailed Film and Actor Information"/>
				</form>
				</div>
				<br>
			</div>
			<br>
			<br>
			<br>
		</c:when>
		<c:otherwise>
				<h3>No such Film found!</h3>
		</c:otherwise>
	</c:choose>
	<br>
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
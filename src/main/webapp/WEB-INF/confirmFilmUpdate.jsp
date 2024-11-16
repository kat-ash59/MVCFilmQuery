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
		
		<c:when test="${isSuccess }">
			<h5>You have successfully updated your Film's information in the Database</h5><br>
			The film Id is: ${film.id}<br>
			The New Title is: ${film.title}<br>
			The New Description is: ${film.description}<br>
		</c:when>
		<c:otherwise>
			Unable to update the film!
		</c:otherwise>
	</c:choose>
	<br>
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
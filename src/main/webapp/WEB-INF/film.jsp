<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Information</title>
</head>
<body>
	<h1>Film Information</h1>
	<c:if test="${! empty film }">
		Title: ${film.title}<br>
		     Id: ${film.id}<br>
		     Description: ${film.description}<br>
	</c:if>
</body>	

<head>
<title>Actor Information</title>
</head>
<body>	
	<c:if test="${! empty actor }">
		Id: ${actor.id}<br>
		First Name: ${actor.firstName}<br>
		Last Name: ${actor.lastName}<br>
	</c:if>
</body>
	

</body>
</html>
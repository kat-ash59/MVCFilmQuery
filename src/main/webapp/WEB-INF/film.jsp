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
	<c:if test="${! empty film }">
		Title: ${film.title}<br>
		     Id: ${film.id}<br>
		     Description: ${film.description}<br>
	</c:if>
	<c:if test="${empty film }">
		<h3>No such Film found!</h3>
	</c:if>
</body>	

<head>

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
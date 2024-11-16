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
		
		<c:when test="${isSuccess }">
			<h5>You have successfully updated your Actor's information in the Database</h5><br>
			The Actor's Id is: ${actor.id}<br>
			The New Actor's First Name is: ${actor.firstName}<br>
			The New Actor's Last is: ${actor.lastName}<br>
		</c:when>
		<c:otherwise>
			Unable to update the Actor's information!
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
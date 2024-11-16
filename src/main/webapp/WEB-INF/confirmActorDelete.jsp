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
			Successfully Deleted the Actor!
		</c:when>
		<c:otherwise>
			Unable to Delete the Actor!
		</c:otherwise>
	</c:choose>
	<form action="index.do">
			<input type="submit" value="Back"/>
	</form>
	</div>
	</div>
	
</body>
<head>
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
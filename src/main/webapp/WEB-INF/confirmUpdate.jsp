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
		<c:when test="${isSuccess }">
			Updated the film!
		</c:when>
		<c:otherwise>
			Unable to update the film!
		</c:otherwise>
	</c:choose>
</body>	
<head>

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
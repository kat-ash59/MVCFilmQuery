<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actor Information</title>
<%@  include file="bootstraphead.jsp" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
</head>
<body>
	<h3>Actor Information</h3>
	<div class="container">
	<div class="row">
		<form action="index.do">
			<input type="submit" value="Back"/>
		</form>
		<c:choose>
		<c:when test="${! empty actors }">
			<table class="table-bordered">
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
			</tr>
			<c:forEach items="${actors}" var="actor">
			<tr>
			    <td>${actor.id}</td>
			    <td>${actor.firstName}</td>
			    <td>${actor.lastName}</td>
			</tr>
			</c:forEach>	
			</table>
		</c:when>
		<c:otherwise>
			<h3>No Actors found!</h3>
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
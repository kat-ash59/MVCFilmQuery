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
	<h3>Film Information</h3>
	<div class="container">
	<div class="row">
		<form action="index.do">
			<input type="submit" value="Back"/>
		</form>
		<c:choose>
		<c:when test="${! empty films }">
			<table class="table-bordered">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
			</tr>
			<c:forEach items="${films}" var="film">
			<tr>
			    <td>${film.id}</td>
			    <td>${film.title}</td>
			    <td>${film.description}</td>
			</tr>
			</c:forEach>	
			</table>
		</c:when>
		<c:otherwise>
			<h3>No such Film found!</h3>
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
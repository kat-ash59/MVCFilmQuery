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
	<h1>Film Information</h1>
	
		<form action="index.do">
			<input type="submit" value="Back"/>
		</form>
		<c:if test="${! empty films }">
			<table>
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
		</c:if>
		

	
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
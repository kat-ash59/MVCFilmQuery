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
	<div class="container">
	<div class="row">
	<h3>Actor Information</h3>
	<c:choose>
		<c:when test="${! empty actor }">
			Id: ${actor.id}<br>
			First Name: ${actor.firstName}<br>
			Last Name: ${actor.lastName}
			<br>
<!-- 
			<div id="outer">
				<div class="inner">
				<form action="deleteActor.do">
					<input type="hidden" id="id" name="id" value="${actor.id}" />
					<input type="submit" value="Delete"/>
				</form>
				</div>
				<br>
				<div class="inner">
				<form action="updateActorGetInfo.do">
					<input type="hidden" id="id" name="id" value="${actor.id}" />
					<input type="submit" value="Update"/><br>
				</form>
				</div>
				<br>
				<div class="inner">
				<form action="findFilmsActorAppearedIn.do">
					<input type="hidden" id="id" name="id" value="${actor.id}" />
					<input type="submit" value="Get Detailed Film and Actor Information"/>
				</form>
				</div>
				<br>
			</div>
			<br>
			<br>
			<br>
			-->
		</c:when>
		<c:otherwise>
				<h3>No such Actor found!</h3>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SDPT Films</title>
<%@  include file="bootstraphead.jsp" %>
</head>
<body>
	<h1 style="text-align: center">MVC Film Query Application</h1>
	<br>
	<div class="container">
		<div class="row">
			
			The link directly below will take you to a page that will give you access to multiple 
			things about the Films in our Database.  You can find all the films, find a film
			by it's id, find a film by a keyword that matches somewhere in your title or description
			or you can add a film. Once you find a film you will be able to update, delete, or get
			more detailed information about that film.  Enjoy!
			<strong>
			<a href="film.html">Get Film Information</a>
			</strong>
			<br>
			The link directly below take you to a page that will give you access to multiple 
			things about the Actors in our Database.  You can find all the actors, find an actor
			by their sag membership id, find an actor by a keyword that matches somewhere in their
			First Name or Last Name or you can add an actor.
			 Once you find an actor you will be able to update, delete the actor.  Enjoy!
			<strong>
			<a href="actor.html">Get Actor Information</a>
			</strong>
		</div>
	</div>
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
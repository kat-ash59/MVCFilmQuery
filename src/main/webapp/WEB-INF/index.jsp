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
	<h3>Get Information about Films in the Database</h3>
	<div class="container">
		<div class="row">
		<strong>Find all the films in the database:</strong><br>
		<form action="findAllFilms.do" >
			<input type="submit" value="Find all films in database">
			<br>
		</form>
		</div>
		<br>
		<br>
		<div class="row">
		<strong>Find a film by it's film id:</strong><br>
		<form action="findFilmById.do" >
			<br>
			<label for="id">Enter Id Here</label><br>
			<input type="text" id="id" name="id" value="0">
			<input type="submit" name="doThis" value="Find film by Id">
			<br>
			<br>
			<br>
		</form>
		</div>
		<div class="row">
		<strong>Find a list of films containing the keyword which may be in the title or description:<br>
		If nothing is entered will not return a list of films</strong><br>
		<form action="findFilmsByKeyword.do" >
			<br>
			<label for="keyword">Enter Keyword Here: </label><br>
			<input type="text" id="keyword" name="keyword" value="">
			<input type="submit" value="Find all films that have the keyword in title or description">
			<br>
			<br>
			<br>
		</form>
		</div>
		<div class="row">
		<strong>You will be asked to enter the title (must have) and description (optional) 
		for the film you wish to enter into the database.</strong><br>
		<form action="createFilm.do" >
			<br>
			<label for="Title">Enter Title Here:</label><br>
			<input type="text" id="Title" name="Title" value=""/><br>
			<label for="Description">Enter Description Here:</label><br>
			<input type="text" id="Description" name="Description" value=""/><br>
			<input type="submit" value="Add new Film to the Database">
			<br>
			<br>
			<br>
		</form>
		</div>
		<br>
	</div>

	<h3>Get Information about Actors in the Database</h3>
	<br>
	<div class="container">
		<div class="row">
			<strong>Find all the actors in the database</strong>
			<form action="findAllActors.do" >
				<input type="submit" name="doThat" value="Find  all actors in database">
			</form>
		</div>
		<br>
		<div class="row">
		<strong>You will be asked to enter the First and Last Name 
		for the actor you wish to enter into the database.</strong><br>
		<form action="addActor.do" >
			<br>
			<label for="firstName">Enter First Name Here:</label><br>
			<input type="text" id="firstName" name="firstName" value=""/>
			<br>
			<label for="lastName">Enter Last Name Here:</label><br>
			<input type="text" id="lastName" name="lastName" value=""/>
			<br>
			<input type="submit" value="Add new Actor to the Database">
			<br>
			<br>
			<br>
		</form>
		</div>
		<br>
		<div class="row">
			<strong>Find actors by their Id</strong>
			<form action="findActorById.do" >
				<input type="text" name="id" value="0">
				<input type="submit" name="doThat" value="Find actor by Id">
			</form>
		</div>
		<br>
		<br>
	</div>
<%@  include file="bootstrapfooter.jsp" %>
</body>
</html>
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
	<div class="container">
	<h1>Get Information about Films</h1>
		<div class="row">
		<form action="findFilmById.do" >
			<label for="id">Enter Id Here</label><br>
			<input type="text" id="id" name="id">
			<input type="submit" name="doThis" value="Find film by Id">
			<br>
			<br>
			<br>
		</form>
		</div>
		<div class="row">
		<form action="findAllFilms.do" >
			<input type="submit" value="Find all films in database">
			<br>
			<br>
			<br>
		</form>
		</div>
		<div class="row">
		<form action="findFilmsByKeyword.do" >
			<label for="keyword">Enter Keyword Here</label><br>
			<input type="text" id="keyword" name="keyword">
			<input type="submit" value="Find all films that have the keyword in title or description">
			<br>
			<br>
			<br>
		</form>
		</div>
		<div class="row">
		<form action="createFilm.do" >
			<pre>Please enter the title and description for the film you wish to enter into the database.</pre>
			<label for="Title">Title</label><br>
			<input type="text" id="Title" name="Title"/><br>
			<label for="Description">Description</label><br>
			<input type="text" id="Description" name="Description"/><br>
			<input type="submit" value="Add new Film to the Database">
			<br>
			<br>
			<br>
		</form>
		</div>
		<!-- 
		<div class="row">
		<form action="deleteFilm.do" >
			<input type="submit" value="Delete Film from the Database">
			<br>
			<br>
			<br>
		</form>
		</div>
		-->
		<div class="row">
		<form action="updateFilm.do">
			<input type="button" name="somethingElse" value="Edit Film Title or Description">
		</form>
		</div>
	<br>
	</div>
</body>

<!-- 
<body>
	<h1>Get Information about Actors</h1>
	<br>
	<form action="findActorById.do" >
		<input type="text" name="id">
		<input type="submit" name="doThat" value="Find actor by Id">
	</form>
<%@  include file="bootstrapfooter.jsp" %>
</body>
 -->
</html>
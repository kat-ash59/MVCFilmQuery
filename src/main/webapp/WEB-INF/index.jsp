<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SDPT Films</title>
</head>
<body>
	<h1>Film Information</h1>
		<form action="findFilmById.do" >
			<input type="text" name="id">
			<input type="submit" name="doThis" value="Find film by Id">
			<br>
		</form>
		<form action="updateFilm.do">
			<input type="button" name="somethingElse" value="Edit Film Title or Description">
		</form>
	<br>
	<h1>Actor Information</h1>
		
	</form>

	<br>
	<form action="findActorById.do" >
		<input type="text" name="id">
		<input type="submit" name="doThat" value="Find actor by Id">
	</form>


</body>
</html>
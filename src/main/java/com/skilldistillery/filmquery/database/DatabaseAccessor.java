package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor 
{
	
	public Actor findActorByActorId(int actorId);
		
	public List<Actor> findActorsByFilmId(int filmId);

	public int countNumberOfAllActors();

	public int countNumberOfAllFilms();
	
	public Film findFilmById(int filmId);

	public List<Film> findFilmsByKeyword(String keyword);

	public String getLanguageByFilmId(int filmId);
	
	public String getFilmCategoryByFilmId(int filmId);
	
	public Film findFilmAndActorsByFilmId(int filmId);

	public Film createFilm(String title, String description);

	public boolean deleteFilm(int filmId);

	public List<Film> getListOfAllFilms();

	public boolean updateFilm(Film film);
	
}  // end interface DatabaseAccessor

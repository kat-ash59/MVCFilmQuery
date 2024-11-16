package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor 
{
	public List<Film> getListOfAllFilms();
	
	public Film findFilmAndActorsByFilmId(int filmId);
	
	public List<Actor> findActorsByFilmId(int filmId);
	
	public Film findFilmById(int filmId);

	public List<Film> findFilmsByKeyword(String keyword);

	public String getLanguageByFilmId(int filmId);
	
	public String getFilmCategoryByFilmId(int filmId);
	
	public Film createFilm(String title, String description);

	public boolean deleteFilm(int filmId);

	public boolean updateFilm(Film film);

	public List<Actor> getListOfAllActors();
	
	public Actor findActorByActorId(int actorId);

	public Actor createActor(String firstName, String lastName);
	

	
}  // end interface DatabaseAccessor

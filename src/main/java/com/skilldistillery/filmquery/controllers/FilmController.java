package com.skilldistillery.filmquery.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

@Controller
public class FilmController 
{

	private final DatabaseAccessor dao;
	
	public FilmController(DatabaseAccessor dao)
	{
		this.dao = dao;
	}
	
	@RequestMapping(path= {"/","index.do"})
	public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping(path="findFilmById.do", method=RequestMethod.GET, params="id")
	public ModelAndView findFilmAndActorsByFilmId(@RequestParam("id") int filmId)
	{
		Film film = null;
		ModelAndView mv = new ModelAndView();
		
		try
		{
			film = dao.findFilmAndActorsByFilmId(filmId);
			mv.addObject("film", film);
			mv.setViewName("WEB-INF/film.jsp");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return mv;
	}  // end findFilmByFilmID
	
	
	@RequestMapping(path="updateFilm.do", method=RequestMethod.GET, params="id")
	public ModelAndView updateFilm(@RequestParam("id") int filmId)
	{
		Film film = null;
		ModelAndView mv = new ModelAndView();
		
		try
		{
			film = dao.findFilmById(filmId);
			mv.addObject("actor", film);
			mv.setViewName("WEB-INF/film.jsp");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end edit film id
	
	
	@RequestMapping(path="findActorById.do", method=RequestMethod.GET, params="id")
	public ModelAndView findActorById(@RequestParam("id") int actorId)
	{
		Actor actor = null;
		ModelAndView mv = new ModelAndView();
		
		try
		{
			actor = dao.findActorByActorId(actorId);
			mv.addObject("actor", actor);
			mv.setViewName("WEB-INF/film.jsp");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end find actor by actor id
	

	
	

}

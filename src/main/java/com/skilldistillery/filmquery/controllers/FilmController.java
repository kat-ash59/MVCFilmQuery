package com.skilldistillery.filmquery.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping(path="findFilmById.do", params="id")
	public ModelAndView findFilmAndActorsByFilmId(@RequestParam("id") int filmId)
	{
		Film film = null;
		ModelAndView mv = new ModelAndView();
		
		try
		{
			film = dao.findFilmAndActorsByFilmId(filmId);
			mv.addObject("film", film);
			mv.setViewName("film");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return mv;
	}  // end findFilmByFilmID
	

	
	@GetMapping(path="findAllFilms.do")
	public ModelAndView findAllFilmsInDB()
	{
		List<Film> filmList = new ArrayList<Film>();
		ModelAndView mv = new ModelAndView();
		
		try
		{
			filmList = dao.getListOfAllFilms();
			mv.addObject("films", filmList);
			mv.setViewName("films");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end search for get list of all films
	

	@GetMapping(path="findFilmsByKeyword.do", params="keyword")
	public ModelAndView findFilmsByKeyword(@RequestParam("keyword") String keyword)
	{
		List<Film> filmList = new ArrayList<Film>();
		ModelAndView mv = new ModelAndView();
		
		if ((keyword != null) && (!keyword.isEmpty()) || (!keyword.isBlank()))
		{
		
			try
			{
				filmList = dao.findFilmsByKeyword(keyword);
				mv.addObject("films", filmList);
				mv.setViewName("films");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		}
		else
		{
			mv.addObject("films", filmList);
			mv.setViewName("films");
		}
		
		return mv;
	}  // end search for film by keyword
	
	
	@GetMapping(path="createFilm.do", params={"Title","Description"})
	public ModelAndView createFilm(@RequestParam("Title") String Title, 
									@RequestParam("Description") String Description)
	{
		Film film = null;
		ModelAndView mv = new ModelAndView();

		try
		{
			film = dao.createFilm(Title, Description);
			mv.addObject("film", film);
			mv.setViewName("film");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end search for film by keyword

	
	@GetMapping(path="deleteFilm.do", params="id")
	public ModelAndView deleteFilm(@RequestParam("id") int filmId)
	{
		Boolean successfulDelete = false;
		ModelAndView mv = new ModelAndView();
		System.out.println("film id = " + filmId);
		
		try
		{
			successfulDelete = dao.deleteFilm(filmId);
			if (successfulDelete)
			{
				mv.addObject("isSuccess", successfulDelete);
				mv.setViewName("confirmDelete");
			}
			else
			{
				mv.addObject("isSuccess", false);
				mv.setViewName("confirmDelete");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end search for film by keyword
	
	
	@GetMapping(path="updateFilm.do", params={"id","title","description"})
	public ModelAndView updateFilm( @RequestParam("id") int filmId,
									@RequestParam("title") String title,
									@RequestParam("description") String description)
	{
		Film film = null;
		ModelAndView mv = new ModelAndView();
		Boolean successfulUpdate = false;
		
		System.out.println("id = " + filmId + " title = " + title + " description = " + description);
		
		try
		{
			film = dao.findFilmById(filmId);
			if ((title != null) && (!title.isEmpty()) || (!title.isBlank()))
			{
				film.setTitle(title);
			}
			
			if ((description != null) && (!description.isEmpty()) || (!description.isBlank()))
			{
				film.setDescription(description);
			}
			successfulUpdate = dao.updateFilm(film);
			
			if (successfulUpdate)
			{
				mv.addObject("isSuccess", successfulUpdate);
				mv.addObject("film", film);
				mv.setViewName("confirmUpdate");
			}
			else
			{
				mv.addObject("isSuccess", false);
				mv.setViewName("confirmUpdate");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end edit film id


	@GetMapping(path="updateGetInfo.do", params="id")
	public ModelAndView updateGetFilmInfo(@RequestParam("id") int filmId)
	{
		Film film = null;
		ModelAndView mv = new ModelAndView();
		
		if (filmId != 0)
		{
			try
			{
				film = dao.findFilmById(filmId);
				mv.addObject("film", film);
				mv.setViewName("update");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		}
		else
		{
			mv.addObject("film", film);
			mv.setViewName("update");
		}
		return mv;
	}  // end get info for edit film by id


	
	
	@RequestMapping(path="findActorById.do", method=RequestMethod.GET, params="id")
	public ModelAndView findActorById(@RequestParam("id") int actorId)
	{
		Actor actor = null;
		ModelAndView mv = new ModelAndView();
		
		try
		{
			actor = dao.findActorByActorId(actorId);
			mv.addObject("actor", actor);
			mv.setViewName("film");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return mv;
	}  // end find actor by actor id
	

}

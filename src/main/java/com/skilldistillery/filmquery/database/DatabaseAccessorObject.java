package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor 
{
	public static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	
	private Film film = null;
	private Actor actor = null;
	private String user = "student";
	private String pass = "student";
	private Connection conn = null;

	
	public DatabaseAccessorObject() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Film createFilm(String title, String description) 
	{
		// each method manages its own connection
		Connection conn = null;
		Film film = null;
		int newFilmId = 0;

		if ((title != null) && (!title.isEmpty()) || (!title.isBlank()))
		{
			try 
			{
				conn = DriverManager.getConnection(URL, user, pass);
				// start a transaction
				conn.setAutoCommit(false);
	
				// We'll be filling in the film's title, language_id - defaulting to 1, and description
				String sql = "INSERT INTO film (title, language_id, description) VALUES (? ,? ,?)";
				System.out.println("sql is " + sql);
				
				// compile / optimize the sql into the db, and request the generated keys be
				// Accessible
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	
				// bind (assign) the name fields into our sql statements bind vars
				stmt.setString(1, title);
				stmt.setInt(2, 1); // not prompting user for language, using default of 1
				stmt.setString(3, description);
	
				// run the query in the database
				int updateCount = stmt.executeUpdate();
	
				// check if the INSERT was successful in creating 1 new Film
				if (updateCount == 1) 
				{
					// good news: we can grab this new Film's id
					ResultSet keys = stmt.getGeneratedKeys();
	
					// we're expecting just 1 generated key since inserting one new film
					if (keys.next()) 
					{
						// grab the generated key (id)
						newFilmId = keys.getInt(1);
	
						// change the initial id in our Java entity to film's 'real' id
						
					}
					
					// an explicit commit of the transaction is required to prevent a rollback
					conn.commit();
					stmt.close();
					keys.close();
					conn.close();
				}
				else 
				{
					// something went wrong with the INSERT
					System.out.println("Something went wrong on the insert for your film");
				}
	
				film=findFilmById(newFilmId);
				
	
			} // end try
			catch (SQLException sqle) 
			{
				sqle.printStackTrace();
				if (conn != null) 
				{
					try 
					{
						conn.rollback();
					} 
					catch (SQLException sqle2) 
					{
						System.err.println("Error trying to rollback");
					}
				} // end if
				
			} // end catch for sql exception
			// ignoring connections exceptions for now
		} // end if to make sure title isn't blank
		
		return film;
	}	

	
	@Override
	public boolean deleteFilm(int filmId) 
	{
		
		Connection conn = null;

		if (filmId != 0)
		{
			try 
			{
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false);
	
				
				// setup delete sql stmt
				String sql = "DELETE FROM film WHERE id = ?";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, filmId);
				
				
				stmt.executeUpdate();
	
				conn.commit();
				stmt.close();
				conn.close();
			} 
			catch (SQLException sqle) 
			{
				sqle.printStackTrace();
				if (conn != null) 
				{
					try 
					{
						conn.rollback();
					} 
					catch (SQLException sqle2) 
					{
						System.err.println("Error trying to rollback");
					}
				}
				return false;
			}
			return true;
		}
		return false;	
	}	// end method deleteFilm

	
	@Override
	public boolean updateFilm(Film film) 
	{
		Connection conn = null;

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			// start the transaction
			conn.setAutoCommit(false);

			String sql = "UPDATE film SET title=?, description=?  WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getId());

			stmt.executeUpdate();
			
			// all data associated with the film has been updated, so
			// let's commit now
			conn.commit();
			stmt.close();
			conn.close();
			
		} 
		catch (SQLException sqle) 
		{
			// something went wrong, so the above commit() was never called
			// let's undo what we did
			sqle.printStackTrace();
			if (conn != null) 
			{
				try 
				{
					conn.rollback();
					conn.close();
				} 
				catch (SQLException sqle2) 
				{
					System.err.println("Error trying to rollback");
				}
			}
			// not successful in the update
			return false;
		}
		// we rocked the update!
		return true;
	}

	
	
	@Override
	public List<Film> getListOfAllFilms()
	{
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		
		try
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();
		
		
			while(results.next()) 
			{			
			     int id = results.getInt("id");
			     String title = results.getString("title");
			     String description = results.getString("description");
			     Integer year = results.getInt("release_year");
			     int languageId = results.getInt("language_id");        
			     int rentalDuration = results.getInt("rental_duration");     
			     double rentalRate = results.getDouble("rental_rate"); 
			     int length = results.getInt("length");
			     double replacementCost = results.getDouble("replacement_cost");  
			     String rating = results.getString("rating");
			     String specialFeatures = results.getString("special_features");
			     List<Actor> actorList = findActorsByFilmId(id);
			     String language = getLanguageByFilmId(id);
			     String category = getFilmCategoryByFilmId(id);
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures, actorList, language, category );
			     
			     filmList.add(film);
			}  // end while loop for creating filmlist
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		//System.out.println("just before return film list for list of films to delete\n\n");
		return filmList;
		
	} // end getListOfAllFilms
		

	@Override
	public Film findFilmAndActorsByFilmId(int filmId)
	{
		Film tmpFilm = null;
		List<Actor> listOfActors = new ArrayList<>();
		tmpFilm = findFilmById(filmId);
		
		if (tmpFilm == null)
		{
			film = null;
		}
		else
		{
			listOfActors = findActorsByFilmId(filmId);
			String language = getLanguageByFilmId(filmId);
			String category = getFilmCategoryByFilmId(filmId);
		    
			//System.out.println("inside findFilmAndActorsByFilmId just before add new film to list"
			//		+ "\n\n");
		    film = new Film(tmpFilm.getId(), tmpFilm.getTitle(), tmpFilm.getDescription(), 
		    				tmpFilm.getReleaseYear(), tmpFilm.getLanguageId(), tmpFilm.getRentalDuration(),
		    				tmpFilm.getRentalRate(), tmpFilm.getLength(), tmpFilm.getReplacementCost(), 
		    				tmpFilm.getRating(), tmpFilm.getSpecialFeatures(),listOfActors, language, category);
		}
		
		return film;
		
	     
	}


	@Override
	public Film findFilmById(int filmId) 
	{
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
			
			//System.out.println("just before adding film to find findFilmById");
			if(results.next()) 
			{
				
			     int id = results.getInt("id");
			     //System.out.println("adding film with film id to the filmlist " + id );
			     String title = results.getString("title");
			     String description = results.getString("description");
			     Integer year = results.getInt("release_year");
			     int languageId = results.getInt("language_id");        
			     int rentalDuration = results.getInt("rental_duration");     
			     double rentalRate = results.getDouble("rental_rate"); 
			     int length = results.getInt("length");
			     double replacementCost = results.getDouble("replacement_cost");  
			     String rating = results.getString("rating");
			     String specialFeatures = results.getString("special_features");
			     List<Actor> actorList = findActorsByFilmId(id);
			     String language = getLanguageByFilmId(filmId);
			     String category = getFilmCategoryByFilmId(filmId);
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures, actorList, language, category );
			     
			}
			else
			{
				//System.out.println("adding a null film to the film list");
				film = null;
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return film;
		
	}  // end method findFilmById

	

	@Override
	public List<Film> findFilmsByKeyword(String keyword) 
	{

		List<Film> filmList = new ArrayList<>();
		List<Actor> actorList = new ArrayList<>();
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film where film.title like ? OR film.description like ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setString(1, "%" +  keyword + "%");
			stmt.setString(2, "%" +  keyword + "%");

			ResultSet results = stmt.executeQuery();
			
			
			while (results.next()) 
			{
			     int id = results.getInt("id");
			     String title = results.getString("title");
			     String description = results.getString("description");
			     Integer year = results.getInt("release_year");
			     int languageId = results.getInt("language_id");        
			     int rentalDuration = results.getInt("rental_duration");     
			     double rentalRate = results.getDouble("rental_rate"); 
			     int length = results.getInt("length");
			     double replacementCost = results.getDouble("replacement_cost");  
			     String rating = results.getString("rating");
			     String specialFeatures = results.getString("special_features");
			     String language = getLanguageByFilmId(id);
			     actorList = findActorsByFilmId(id);
			     String category = getFilmCategoryByFilmId(id);
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures, actorList, language, category);
			     
			     filmList.add(film);
			     
			}  // end while loop creating filmList
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return filmList;
		
	}  // end method findFilmById


	@Override
	public String getFilmCategoryByFilmId(int filmId) 
	{
		String category = null;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select category.name "
					+ "from category "
					+ "join film_category on category.id = film_category.category_id "
					+ "join film on film.id = film_category.film_id "
					+ "where film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
			
		
			while (results.next()) 
			{
			     category = results.getString("category.name"); 
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return category;
	}  // end method getFilmCategoryByFilmId


	@Override
	public List<Actor> getListOfAllActors()
	{
		List<Actor> actorList = new ArrayList<>();
		Actor actor = null;
		
		try
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from actor";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();
		
		
			while(results.next()) 
			{			
			     int id = results.getInt("id");
			     String firstName = results.getString("first_name");
			     String lastName = results.getString("last_name");
			         
			     actor = new Actor(id, firstName, lastName );
			     
			     actorList.add(actor);
			}  // end while loop for creating actorList
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return actorList;
		
	} // end getListOfAllActors
	

	@Override
	public Actor createActor(String firstName, String lastName) 
	{
		// each method manages its own connection
		Connection conn = null;
		Actor actor = null;
		int newActorId = 0;

		if ((firstName != null) && (!firstName.isEmpty()) || (!firstName.isBlank()) &&
		(lastName != null) && (!lastName.isEmpty()) || (lastName.isBlank()))
		{
		
			try 
			{
				conn = DriverManager.getConnection(URL, user, pass);
				// start a transaction
				conn.setAutoCommit(false);
	
				
				String sql = "INSERT INTO actor (first_name, last_name) VALUES (? , ?)";
				System.out.println("sql is " + sql);
				
				// compile / optimize the sql into the db, and request the generated keys be
				// Accessible
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	
				// bind (assign) the name fields into our sql statements bind vars
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
	
				// run the query in the database
				int updateCount = stmt.executeUpdate();
	
				// check if the INSERT was successful in creating 1 new Film
				if (updateCount == 1) 
				{
					// good news: we can grab this new Film's id
					ResultSet keys = stmt.getGeneratedKeys();
	
					// we're expecting just 1 generated key since inserting one new film
					if (keys.next()) 
					{
						// grab the generated key (id)
						newActorId = keys.getInt(1);
	
						// change the initial id in our Java entity to film's 'real' id
						
					}
					
					// an explicit commit of the transaction is required to prevent a rollback
					conn.commit();
					stmt.close();
					keys.close();
					conn.close();
				}
				else 
				{
					// something went wrong with the INSERT
					System.out.println("Something went wrong on the insert for your Actor");
				}
	
				actor = findActorByActorId(newActorId);
				
	
			} // end try
			catch (SQLException sqle) 
			{
				sqle.printStackTrace();
				if (conn != null) 
				{
					try 
					{
						conn.rollback();
					} 
					catch (SQLException sqle2) 
					{
						System.err.println("Error trying to rollback");
					}
				} // end if
				
			} // end catch for sql exception
		} // end if checking first name and last name values
		
		return actor;
	}  // end method create actor
	
	
	public boolean deleteActor(Actor actor) 
	{
		Connection conn = null;

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			// film_actor is a child of (depends upon) both actor and film tables
			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			stmt.executeUpdate();

			// child rows for this actor are gone, can remove the Actor (parent) now
			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			stmt.executeUpdate();

			conn.commit();
			conn.close();
		} 
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
			if (conn != null) 
			{
				try 
				{
					conn.rollback();
				} 
				catch (SQLException sqle2) 
				{
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	
	
	public boolean saveActor(Actor actor) {
		  Connection conn = null;

		  try {
		    conn = DriverManager.getConnection(URL, user, pass);
		    // start the transaction
		    conn.setAutoCommit(false); 

		    String sql = "UPDATE actor SET first_name=?, last_name=?  WHERE id=?";
		    PreparedStatement stmt = conn.prepareStatement(sql);

		    stmt.setString(1, actor.getFirstName());
		    stmt.setString(2, actor.getLastName());
		    stmt.setInt(3, actor.getId());

		    int updateCount = stmt.executeUpdate();

		    if (updateCount == 1) {
		      // We don't know which (if any) of the actor's films have changed, so
		      // we will replace all the film ids currently associated with this actor

		      // remove the old film ids
		      sql = "DELETE FROM film_actor WHERE actor_id = ?";
		      stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, actor.getId());
		      stmt.executeUpdate();

		      // insert the current film ids
		      sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
		      stmt = conn.prepareStatement(sql);

		      // iterate through all the actor's current film ids, to
		      // (re)associate them with this actor
		      for (Film film : actor.getFilms()) {
		        stmt.setInt(1, film.getId());
		        stmt.setInt(2, actor.getId());
		        updateCount = stmt.executeUpdate();
		      }

		      // all data associated with the actor has been updated, so
		      // let's commit now
		      conn.commit();  
		      conn.close();         
		    }
		  } catch (SQLException sqle) {
		    // something went wrong, so the above commit() was never called
		    // let's undo what we did
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); } 
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    // not successful in the update
		    return false;
		  }
		  // we rocked the update!
		  return true;
	}
	
	
	@Override
	public Actor findActorByActorId(int actorId) 
	{
		System.out.println("actorId" + actorId);
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from actor where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, actorId);
			
			ResultSet results = stmt.executeQuery();
			
			if(results.next()) 
			{
			     int id = results.getInt("id");
			     String firstName = results.getString("first_name");
			     String lastName = results.getString("last_name");
			     
			     actor = new Actor(id, firstName, lastName);
			     
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actor;
	}  // end method Actor
		
	
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) 
	{
		List<Actor> actorList = new ArrayList<Actor>();
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);

			String sqltext = "select actor.id, actor.first_name, actor.last_name "
					+ "from actor "
					+ "join film_actor on film_actor.actor_id = actor.id "
					+ "join film on film.id = film_actor.film_id "
					+ "where film.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
				
			while (results.next()) 
			{
			     int id = results.getInt("actor.id");
			     String firstName = results.getString("actor.first_name");
			     String lastName = results.getString("actor.last_name");
			     
			     actor = new Actor(id, firstName, lastName);
			     actorList.add(actor);
			}
			
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		     
				
		return actorList;

	} // end method find actors by film id
	

	@Override
	public boolean updateActor(Actor actor) 
	{
		Connection conn = null;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			// start the transaction
			conn.setAutoCommit(false);

			String sql = "UPDATE actor SET first_name=?, last_name=?  WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());

			stmt.executeUpdate();
			
			// all data associated with the actor has been updated, so
			// let's commit now
			conn.commit();
			stmt.close();
			conn.close();
			
		} 
		catch (SQLException sqle) 
		{
			// something went wrong, so the above commit() was never called
			// let's undo what we did
			sqle.printStackTrace();
			if (conn != null) 
			{
				try 
				{
					conn.rollback();
					conn.close();
				} 
				catch (SQLException sqle2) 
				{
					System.err.println("Error trying to rollback");
				}
			}
			// not successful in the update
			return false;
		}
		// we rocked the update!
		return true;
	}  // end method update actor


	@Override
	public String getLanguageByFilmId(int filmId)
	{
		String language = null;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select language.name "
					+ "from language "
					+ "join film on film.language_id = language.id "
					+ "where film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
			
		
			while (results.next()) 
			{
			     language = results.getString("language.name");  
			}
			
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return language;		
	}

	
	
	@Override
	public boolean deleteActor(int actorId) 
	{
		
		Connection conn = null;

		if (actorId != 0)
		{
			try 
			{
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false);
	
				
				// setup delete sql stmt
				String sql = "DELETE FROM actor WHERE id = ?";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actorId);
				
				
				stmt.executeUpdate();
	
				conn.commit();
				stmt.close();
				conn.close();
			} 
			catch (SQLException sqle) 
			{
				sqle.printStackTrace();
				if (conn != null) 
				{
					try 
					{
						conn.rollback();
					} 
					catch (SQLException sqle2) 
					{
						System.err.println("Error trying to rollback");
					}
				}
				return false;
			}
			return true;
		}
		return false;	
	}	// end method deleteActor



}  // end class DatabaseAccessorObject

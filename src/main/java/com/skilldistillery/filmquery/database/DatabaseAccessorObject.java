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
	public Film createFilm(Film film) 
	{
		// each method manages its own connection
		Connection conn = null;

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			// start a transaction
			conn.setAutoCommit(false);

			// We'll be filling in the film's title, language_id - defaulting to 1, and description
			String sql = "INSERT INTO film (title, language_id, description) VALUES (?,?,?)";

			
			// compile / optimize the sql into the db, and request the generated keys be
			// Accessible
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// bind (assign) the name fields into our sql statements bind vars
			stmt.setString(1, film.getTitle());
			stmt.setInt(2, 1); // not prompting user for language, using default of 1
			stmt.setString(3, film.getDescription());

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
					int newFilmId = keys.getInt(1);

					// change the initial id in our Java entity to film's 'real' id
					film.setId(newFilmId);
				}

				// an explicit commit of the transaction is required to prevent a rollback
				conn.commit();

			}
			else 
			{
				// something went wrong with the INSERT
				System.out.println("Something went wrong on the insert for your film");
				film = null;
			}

			conn.close();

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

		return film;
	}	

	
	@Override
	public boolean deleteFilm(Film film) 
	{
		
		Connection conn = null;

		//System.out.println("just inside delete film");
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			
			// setup delete sql stmt
			String sql = "DELETE FROM film WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			
			
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
		List<Film> filmList = new ArrayList<Film>();
		int numberOfFilms = countNumberOfAllFilms();
		Film film = new Film();
		String title = null;
		String description = null;
		int languageId = 1;
		
		//System.out.println("\n\njust inside getListOfAllFilms");
		//System.out.println("number of films is numberOfFilms " + numberOfFilms);
		

		// going to do something funky here
		// originally had 1000 films in db so for this exercise
		// only going to allow deletion of newly added films
		// so any number less than the original 1000 can not be considered for deletion
		// can change to get full list by changing id to 1
		// only returning list of newly added films
		if (numberOfFilms <= 1000)
		{
			System.out.println("\n\nThere are no films that you are allowed to delete at the moment");
			System.out.println("Please add a film to the library or choose another option");
			return null;
		}
		// can only add films > 1000 to film list
		// some may be null because of odd deletions
		int id = 1001;
		int maxId = getMaxIdFromFilmTable();
		//System.out.println("id = " + id);
		//System.out.println("maxId = " + maxId);

		for (; id <= maxId; id++) 
		{
			 
			 film = findFilmAndActorsByFilmId(id);
		     
			 if (film != null )
			 {
				 title = film.getTitle();
				 description = film.getDescription();
				 
			     film = new Film(id, title, languageId, description );
			     //System.out.println("\n\nadd film id " + film.getId() + " to filmList");
			     //System.out.println("add film language = " + film.getLanguageId() + " to filmList");
			     //System.out.println("add film description = " + film.getDescription() + " to filmList");
			     //System.out.println("\nFilm = " +  film.toString());
			     filmList.add(film);
			 } // end check to make sure id isn't null
			 else
			 {
				 //System.out.println("adding null film to film list");
				 filmList.add(film);
			 }
			     
		}  // end while loop creating filmList
	
		//System.out.println("just before return film list for list of films to delete\n\n");
		return filmList;
		
	} // end getListOfAllFilms
	
	@Override
	public int getMaxIdFromFilmTable() 
	{
		int maxFilmId = 0;

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select MAX(id) from film";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();

			int maxValue = 0;
			if (results.next())
			{
                // Get the maximum value
                maxValue = results.getInt(1);
                //System.out.println("Maximum value: " + maxValue);
            }
			maxFilmId = maxValue;
			//System.out.println("\nThe maxFilmId is " + maxFilmId);

			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maxFilmId;

	}

	@Override
	public Film findFilmAndActorsByFilmId(int filmId)
	{
		Film tmpFilm = null;
		List<Actor> listOfActors = new ArrayList<Actor>();
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

		List<Film> filmList = new ArrayList<Film>();
		List<Actor> actorList = new ArrayList<Actor>();
		
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
			// TODO Auto-generated catch block
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

	
	// needs to be finished at later time kaa 11/2/24
	public Actor createActor(Actor actor) 
	{
		  // each method manages its own connection
		  Connection conn = null;

		  try {
		    conn = DriverManager.getConnection(URL, user, pass);
		    // start a transaction
		    conn.setAutoCommit(false); 

		    // We'll be filling in the actor's first and last names
		    String sql = "INSERT INTO actor (first_name, last_name) VALUES (?,?)";

		    // compile / optimize the sql into the db, and request the generated keys be accessable
		    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		    // bind (assign) the name fields into our sql statements bind vars
		    stmt.setString(1, actor.getFirstName());
		    stmt.setString(2, actor.getLastName());

		    // run the query in the database
		    int updateCount = stmt.executeUpdate();

		    // check if the INSERT was successful in creating 1 new Actor
		    if (updateCount == 1) {
		      // good news: we can grab this new Actor's id 
		      ResultSet keys = stmt.getGeneratedKeys();

		      // we're expecting just 1 generated key
		      if (keys.next()) {
		        // grab the generated key (id)
		        int newActorId = keys.getInt(1);

		        // change the initial id in our Java entity to actor's 'real' id 
		        actor.setId(newActorId);

		        // see if this new actor has been in previous films
		        if (actor.getFilms() != null && actor.getFilms().length > 0) 
		        {
			          sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
			          stmt = conn.prepareStatement(sql);
	
			          // associate each film they were in with their new Actor id 
			          for (Film film : actor.getFilms()) 
			          {
				            stmt.setInt(1, film.getId());
				            stmt.setInt(2, newActorId);
				            updateCount = stmt.executeUpdate();
			          }
		        }

		      }

		      // an explicit commit of the transaction is required to prevent a rollback
		      conn.commit(); 

		    } 
		    else 
		    {
		      // something went wrong with the INSERT
		      actor = null;
		    }
		    
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
		    throw new RuntimeException("Error inserting actor " + actor);
		  }

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
	public int countNumberOfAllActors()
	{

		int numberOfActors = 0;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from actor";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();
			
			
			
			while(results.next()) 
			{
			     numberOfActors++;
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
		
		actor.setNumberOfActors(numberOfActors);
		return numberOfActors;
		
	} // end method countNumberOfActors

	@Override
	public int countNumberOfAllFilms()
	{

		int numberOfFilms = 0;

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();

			
			while(results.next()) 
			{
			     numberOfFilms++;
			}
	
			// System.out.println("\nThe number of films are " + numberOfFilms);

			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numberOfFilms;
	} // end method countNumberOfFilms



}  // end class DatabaseAccessorObject

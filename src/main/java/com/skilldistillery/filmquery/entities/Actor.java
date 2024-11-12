package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Actor 
{
	private int id;
	private String firstName;
	private String lastName;
	private List<Actor> listOfAllActors = null;
	
	private int numberOfActors = 200;
	
	// constructors
	public Actor() 
	{
	} // end no arg constructor


	public Actor(int sagMemeberNumber) 
	{
		this.id = sagMemeberNumber;
	} // end constructor


	public Actor(String fn, String ln) 
	{
		this.firstName = fn;
		this.lastName = ln;
	}  // end constructor


	public Actor(int sagMemeberNumber, String fn, String ln) 
	{
		this.id = sagMemeberNumber;
		this.firstName = fn;
		this.lastName = ln;
	}  // end constructor


	// getters and setters
	public int getId() 
	{
		return id;
	} // end getId


	public void setId(int id) 
	{
		this.id = id;
	}  // end setId


	public String getFirstName() 
	{
		return firstName;
	}


	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}


	public String getLastName() 
	{
		return lastName;
	}


	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public int getNumberOfActors()
	{
		return this.numberOfActors;
	}

	public void setNumberOfActors(int numberOfActors)
	{
		this.numberOfActors = numberOfActors;
	}
	
	public List getListOfAllActors()
	{
		return this.listOfAllActors;
	}
	
	public void setListOfAllActors(List listOfAllActors)
	{
		this.listOfAllActors = listOfAllActors;
	}
	

	@Override
	public int hashCode() 
	{
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return id == other.id;
	}


	@Override
	public String toString() 
	{
		return "Actor id = " + id + ", firstName = " + firstName + ", lastName = " + lastName; 
	}


	public Film[] getFilms() 
	{
		// TODO Auto-generated method stub
		return null;
	}  // end method getFilms
	

}  // end class actor

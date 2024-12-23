package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film 
{

	// field defaults  rental duration=3
	// rental rate 4.99
	// replacement costs 19.99
	// rating g
	
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private int rentalDuration = 3;
	private double rentalRate = 4.99;
	private int length; // film length in minutes
	private double replacementCost = 19.99;
	private String rating= "G";
	private String specialFeatures;
	private List<Actor> actors;
	private String language;
	private String category;
	
	private int numberOfFilms = 1000;
	
	
	
	
	public Film() 
	{
	}



	public Film(int id) 
	{
		this.id = id;
	}

	// film must have id, title, language id
	public Film(int id, String title, int languageId) 
	{
		this.id = id;
		this.title = title;
		this.languageId = languageId;
	}


	// film must have id, title, language id
	public Film(int id, String title, int languageId, String description) 
	{
		this.id = id;
		this.title = title;
		this.languageId = languageId;
		this.description = description;
	}	
	

	
	

	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures) 
	{
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}  // end constructor Film


	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures,
			List<Actor> actorList, String language, String category) 
	{
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.actors = actorList;
		this.language = language;
		this.category = category;
	}  // end constructor Film




	public int getId() 
	{
		return id;
	}  // end method getId



	public void setId(int id) 
	{
		this.id = id;
	}



	public String getTitle() 
	{
		return title;
	}



	public void setTitle(String title) 
	{
		this.title = title;
	}



	public String getDescription() 
	{
		return description;
	}



	public void setDescription(String description) 
	{
		this.description = description;
	}



	public Integer getReleaseYear() 
	{
		return releaseYear;
	}



	public void setReleaseYear(Integer releaseYear) 
	{
		this.releaseYear = releaseYear;
	}



	public int getLanguageId() 
	{
		return languageId;
	}
	
	
	public void setLanguageId(int languageId) 
	{
		this.languageId = languageId;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public void setLanguage(String language)
	{
		this.language = language;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	


	// rental duration in minutes
	public int getRentalDuration() 
	{
		return rentalDuration;
	}



	public void setRentalDuration(int rentalDuration) 
	{
		this.rentalDuration = rentalDuration;
	}



	public double getRentalRate() 
	{
		return rentalRate;
	}



	public void setRentalRate(double rentalRate) 
	{
		this.rentalRate = rentalRate;
	}



	public int getLength() 
	{
		return length;
	}



	public void setLength(int length) 
	{
		this.length = length;
	}



	public double getReplacementCost() 
	{
		return replacementCost;
	}



	public void setReplacementCost(double replacementCost) 
	{
		this.replacementCost = replacementCost;
	}



	public String getRating() 
	{
		return rating;
	}



	public void setRating(String rating) 
	{
		this.rating = rating;
	}



	public String getSpecialFeatures() 
	{
		return specialFeatures;
	}



	public void setSpecialFeatures(String specialFeatures) 
	{
		this.specialFeatures = specialFeatures;
	}



	public List<Actor> getActors() 
	{
		return this.actors;
	}



	public void setActors(List<Actor> actors) 
	{
		this.actors = actors;
	}


	public int getNumberOfFilms()
	{
		return this.numberOfFilms;
	}

	public void setNumberOfFilms(int numberOfFilms)
	{
		this.numberOfFilms = numberOfFilms;
	}
	
	
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(description, id, languageId, length, rating, releaseYear, rentalDuration, rentalRate,
				replacementCost, specialFeatures, title);
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
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && languageId == other.languageId
				&& length == other.length && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}



	@Override
	public String toString() 
	{
		return "\ttitle = " + title + ", the film id = " + id
				+ "\n\tdescription = " + description 
				+ "\n\treleaseYear = " + releaseYear
				+ ", languageId = " + languageId + ", rentalDuration (in days) = " + rentalDuration + ", rentalRate = $" + rentalRate
				+ "\n\tlength of film in minutes = " + length 
				+ ", replacementCost = $" + replacementCost + ", rating = " + rating
				+ "\n\tspecialFeatures = " + specialFeatures + "\n";
	}



} // end class Film

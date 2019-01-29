package com.moviecentral.mc.models;

public class SearchQuery {

	String[] keys;
	Integer year;
	String genre;
	String actors;
	String directors;
	String rating;
	Integer stars;
	Integer page;
	String availability;
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public SearchQuery() {
		super();
		this.keys = null;
		this.year = null;
		this.genre = null;
		this.actors = null;
		this.directors = null;
		this.rating = null;
		this.stars = null;
		this.page = null;
	}
	
	public SearchQuery(String[] keys) {
		super();
		this.keys = keys;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public void setGenre(String genre){
		this.genre = genre;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
}

package com.moviecentral.mc.models;

public class SearchMovie {
	public SearchMovie(Integer movieid, String title, Integer year, String studio, String synopsis, String image,
			String movie, String actors, String director, String country, String rating, String availability,
			Integer price) {
		super();
		this.movieid = movieid;
		this.title = title;
		this.year = year;
		this.studio = studio;
		this.synopsis = synopsis;
		this.image = image;
		this.movie = movie;
		this.actors = actors;
		this.director = director;
		this.country = country;
		this.rating = rating;
		this.availability = availability;
		this.price = price;
	}
	public SearchMovie(){
		super();
		this.movieid = null;
		this.title = null;
		this.year = null;
		this.studio = null;
		this.synopsis = null;
		this.image = null;
		this.movie = null;
		this.actors = null;
		this.director = null;
		this.country = null;
		this.rating = null;
		this.availability = null;
		this.price = null;
	}
	private Integer movieid;
	private String title;
	private Integer year;
	private String studio;
	private String synopsis;
	private String image;
	private String movie;
	private String actors;
	private String director;
	private String country;
	private String rating;
	private String availability;
	private Integer price;
	private Long count;
	private Long plays;
	private Double avg;
	private String genre;
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public SearchMovie(Integer movieid, String title, Integer year, String studio, String synopsis, String image,
			String movie, String actors, String director, String country, String rating, String availability,
			Integer price, Long count, Long plays, Double avg) {
		super();
		this.movieid = movieid;
		this.title = title;
		this.year = year;
		this.studio = studio;
		this.synopsis = synopsis;
		this.image = image;
		this.movie = movie;
		this.actors = actors;
		this.director = director;
		this.country = country;
		this.rating = rating;
		this.availability = availability;
		this.price = price;
		this.count = count;
		this.plays = plays;
		this.avg = avg;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPlays() {
		return plays;
	}
	public void setPlays(Long plays) {
		this.plays = plays;
	}
	public Double getAvg() {
		return avg;
	}
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}

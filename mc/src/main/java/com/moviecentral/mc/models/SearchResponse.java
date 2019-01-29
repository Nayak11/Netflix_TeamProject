package com.moviecentral.mc.models;

import java.util.List;



public class SearchResponse {

	private String status;
	private String message;
	private List<SearchMovie> movies;
	
	public SearchResponse(String status, List<SearchMovie> movies) {
		super();
		this.status = status;
		this.movies = movies;
		this.message = null;
	}
	
	public SearchResponse(){
		this.status = null;
		this.movies = null;
		this.message = null;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SearchMovie> getMovies() {
		return movies;
	}
	public void setMovies(List<SearchMovie> movies) {
		this.movies = movies;
	}
}

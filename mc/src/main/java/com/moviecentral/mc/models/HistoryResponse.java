package com.moviecentral.mc.models;

import java.util.List;

public class HistoryResponse {

	private String status;
	private String message;
	private List<SearchMovie> movies;
	private List<UserInfo> users;
	public HistoryResponse(String status, String message, List<SearchMovie> movies, List<UserInfo> users) {
		super();
		this.status = status;
		this.message = message;
		this.movies = movies;
		this.users = users;
	}
	public HistoryResponse() {
		super();
		this.status = null;
		this.message = null;
		this.movies = null;
		this.users = null;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SearchMovie> getMovies() {
		return movies;
	}
	public void setMovies(List<SearchMovie> movies) {
		this.movies = movies;
	}
	public List<UserInfo> getUsers() {
		return users;
	}
	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	
	
}

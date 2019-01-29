package com.moviecentral.mc.models;

public class SignupRequest {
	
	public String email;
	public String username;
	public String password;
	
	public SignupRequest(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public SignupRequest(){
		this.email = "";
		this.username = "";
		this.password = "";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

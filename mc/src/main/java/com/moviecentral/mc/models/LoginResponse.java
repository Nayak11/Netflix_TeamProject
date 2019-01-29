package com.moviecentral.mc.models;

public class LoginResponse {
	private final String status;
	private final String type;
	private final String message;
	
	public LoginResponse(String status, String type, String message){
		super();
		this.status = status;
		this.type = type;
		this.message = message;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getMessage(){
		return this.message;
	}
}

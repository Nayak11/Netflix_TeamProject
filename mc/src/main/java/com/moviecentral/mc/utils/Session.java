package com.moviecentral.mc.utils;

public class Session {
	private Integer userid;
	private String type;
	private String username;
	private String email;
	
	public Session(){
		this.userid = 0;
		this.type = "";
		this.username = "";
		this.email = "";
	}
	
	public Session(Integer userid, String type, String username, String email) {
		this.userid = userid;
		this.type = type;
		this.username = username;
		this.email = email;
	}
	
	public void reset(){
		this.userid = 0;
		this.type = "";
		this.username = "";
		this.email = "";
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

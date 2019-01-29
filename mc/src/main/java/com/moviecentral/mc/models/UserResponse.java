package com.moviecentral.mc.models;

import java.sql.Date;

public class UserResponse {

	
	public Integer userid;
	public String username;
	public String email;
	public String type;
	public String password;
	Integer subscription;
	Integer activated;
	String code;
	java.sql.Date startdate;
	java.sql.Date enddate;
	
	public UserResponse(Integer userid, String username, String email, String type, String password,
			Integer subscription, Integer activated, String code, Date startdate, Date enddate) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.type = type;
		this.password = password;
		this.subscription = subscription;
		this.activated = activated;
		this.code = code;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSubscription() {
		return subscription;
	}
	public void setSubscription(Integer subscription) {
		this.subscription = subscription;
	}
	public Integer getActivated() {
		return activated;
	}
	public void setActivated(Integer activated) {
		this.activated = activated;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public java.sql.Date getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}
	public java.sql.Date getEnddate() {
		return enddate;
	}
	public void setEnddate(java.sql.Date enddate) {
		this.enddate = enddate;
	}
	
	
}

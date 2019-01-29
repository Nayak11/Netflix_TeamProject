package com.moviecentral.mc.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
	private String username;
	private String email;
	private String type;
	private String password;
	Integer subscription;
	Integer activated;
	String code;
	java.sql.Timestamp startdate;
	java.sql.Timestamp enddate;
	java.sql.Timestamp signupdate;
	public java.sql.Timestamp getSignupdate() {
		return signupdate;
	}
	public void setSignupdate(java.sql.Timestamp signupdate) {
		this.signupdate = signupdate;
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
	
	public java.sql.Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Timestamp startdate) {
		this.startdate = startdate;
	}
	public void setEnddate(java.sql.Timestamp enddate) {
		this.enddate = enddate;
	}
	public java.sql.Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Date date) {
		this.enddate = (Timestamp) date;
	}
	
	
	
	
	
}

package com.moviecentral.mc.models;

import java.sql.Timestamp;

public class UserInfo {
	public UserInfo(Integer userid, String username, String email, String type, Integer subscription, Integer activated,
			Timestamp startdate, Timestamp enddate) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.type = type;
		this.subscription = subscription;
		this.activated = activated;
		this.startdate = startdate;
		this.enddate = enddate;
		
	}
	public UserInfo() {
		super();
		this.userid = null;
		this.username = null;
		this.email = null;
		this.type = null;
		this.subscription = null;
		this.activated = null;
		this.startdate = null;
		this.enddate = null;
	}
	private Integer userid;
	private String username;
	private String email;
	private String type;
	private Integer subscription;
	private Integer activated;
	private java.sql.Timestamp startdate;
	private java.sql.Timestamp enddate;
	private Long count;
	public UserInfo(Integer userid, String username, String email, String type, Integer subscription, Integer activated,
			Timestamp startdate, Timestamp enddate, Long count) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.type = type;
		this.subscription = subscription;
		this.activated = activated;
		this.startdate = startdate;
		this.enddate = enddate;
		this.count = count;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public java.sql.Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Timestamp startdate) {
		this.startdate = startdate;
	}
	public java.sql.Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(java.sql.Timestamp enddate) {
		this.enddate = enddate;
	}
	
	
}

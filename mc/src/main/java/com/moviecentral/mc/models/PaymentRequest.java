package com.moviecentral.mc.models;

import java.sql.Date;

public class PaymentRequest {

	
	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer userid;
	private Integer movieid;
	String type;
	java.sql.Date date;
	Integer amount;
	String username;
	java.sql.Date expirydate;
	Integer selectedMonth;
	
	public Integer getSelectedMonth() {
		return selectedMonth;
	}
	public void setSelectedMonth(Integer selectedMonth) {
		this.selectedMonth = selectedMonth;
	}
	java.sql.Date startdate;
	public java.sql.Date getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}
	public java.sql.Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(java.sql.Date expirydate) {
		this.expirydate = expirydate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public PaymentRequest(Integer userid, Integer movieid, String type, Date date, Integer amount) {
		super();
		this.userid = userid;
		this.movieid = movieid;
		this.type = type;
		this.date = date;
		this.amount = amount;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}

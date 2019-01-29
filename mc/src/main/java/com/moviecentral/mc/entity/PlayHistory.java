package com.moviecentral.mc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "playhistory")
public class PlayHistory {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playid;
    private Integer userid;
    private Integer movieid;
    java.sql.Timestamp date;
	private String type;
	public Integer getPlayid() {
		return playid;
	}
	public void setPayid(Integer payid) {
		this.playid = payid;
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
	public java.sql.Timestamp getDate() {
		return date;
	}
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

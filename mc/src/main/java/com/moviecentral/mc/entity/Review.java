package com.moviecentral.mc.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewid;
	private String review;
	private Integer movieid;
	private Integer rating;
	private Integer userid;
	private java.sql.Timestamp date;
	public Integer getReviewid() {
		return reviewid;
	}
	public void setReviewid(Integer reviewid) {
		this.reviewid = reviewid;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public java.sql.Timestamp getDate() {
		return date;
	}
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	public Review(Integer reviewid, String review, Integer movieid, Integer rating, Integer userid, Timestamp date) {
		super();
		this.reviewid = reviewid;
		this.review = review;
		this.movieid = movieid;
		this.rating = rating;
		this.userid = userid;
		this.date = date;
	}
	public Review() {
		super();
	}
	
}

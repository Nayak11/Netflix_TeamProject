package com.moviecentral.mc.models;

import com.moviecentral.mc.entity.Review;

public class ReviewScore {
	Review review;
	double avg;
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public ReviewScore(Review review, double avg) {
		super();
		this.review = review;
		this.avg = avg;
	}
	public ReviewScore() {
		super();
	}
	
}

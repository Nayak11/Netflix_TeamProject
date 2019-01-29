package com.moviecentral.mc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_attributes")
public class MovieAttributes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movie_attributesid;
	private Integer movieid;
	private Integer attributesid;
	
	public Integer getMovie_attributesid() {
		return movie_attributesid;
	}
	public void setMovie_attributesid(Integer movie_attributesid) {
		this.movie_attributesid = movie_attributesid;
	}
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	public Integer getAttributesid() {
		return attributesid;
	}
	public void setAttributesid(Integer attributesid) {
		this.attributesid = attributesid;
	}
	
	
}

package com.moviecentral.mc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "attributes")
public class Attributes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attributesid;
	private String value;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "attributes")
    private Set<Movie> movies = new HashSet<>();
	
	public Integer getAttributesid() {
		return attributesid;
	}
	public void setAttributesid(Integer attributesid) {
		this.attributesid = attributesid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
	
}

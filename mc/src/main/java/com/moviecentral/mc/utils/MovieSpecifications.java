package com.moviecentral.mc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.moviecentral.mc.entity.Movie;

public class MovieSpecifications {
	public static Specification<Movie> withKeys(String[] values) {
        if (values == null) {
            return null;
        } else {
            return new Specification<Movie>(){
            	static final long serialVersionUID = 1;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Expression<String> exp = root.join("attributes").get("value");
					List<String> list = new ArrayList<String>(Arrays.asList(values));
					Predicate p = exp.in(list);

					query.groupBy(root);
					query.having(cb.gt(cb.count(root), values.length-1));
					
					return p;
				}
            };
        }
    }

	public static Specification<Movie> withGenre(String genre) {
		if (genre == null) {
            return null;
        } else {
            return new Specification<Movie>(){
            	static final long serialVersionUID = 1;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get("genre"), genre);
				}
            };
        }
	}

	public static Specification<Movie> withYear(Integer year) {
		if (year == null) {
            return null;
        } else {
            return new Specification<Movie>(){
            	static final long serialVersionUID = 1;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get("year"), year);
				}
            };
        }
	}
	
	public static Specification<Movie> withActors(String actors) {
		if(actors == null){
			return null;
		} else {
			return new Specification<Movie>(){
            	static final long serialVersionUID = 2;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					String[] actorsplit = actors.split(" ");
					Predicate p[] = new Predicate[actorsplit.length];
					for(int i=0; i<p.length; i++){
						p[i] = cb.like(root.get("actors"),"%" + actorsplit[i] + "%");
					}
					return cb.or(p);
				}
            };
		}
	}

	public static Specification<Movie> withDirectors(String directors) {
		if(directors == null){
			return null;
		} else {
			return new Specification<Movie>(){
            	static final long serialVersionUID = 2;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					String[] directorsplit = directors.split(" ");
					Predicate p[] = new Predicate[directorsplit.length];
					for(int i=0; i<p.length; i++){
						p[i] = cb.like(root.get("director"),"%" + directorsplit[i] + "%");
					}
					return cb.or(p);
				}
            };
		}
	}

	public static Specification<Movie> withRating(String rating) {
		if(rating == null){
			return null;
		} else {
			return new Specification<Movie>(){
            	static final long serialVersionUID = 2;
            	@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get("rating"), rating);
				}
            };
		}
	}

	public static Specification<Movie> withStars(Integer stars) {
		if (stars == null) {
            return null;
        } else {
            return new Specification<Movie>(){
            	static final long serialVersionUID = 1;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate p = cb.greaterThanOrEqualTo(root.get("stars"), stars);
					return p;
				}
            };
        }
	}

	public static Specification<Movie> withAvailability(String availability) {
		if (availability == null) {
            return null;
        } else {
            return new Specification<Movie>(){
            	static final long serialVersionUID = 1;
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get("availability"), availability);
				}
            };
        }
	}
	
}

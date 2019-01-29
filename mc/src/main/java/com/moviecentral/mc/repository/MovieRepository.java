package com.moviecentral.mc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moviecentral.mc.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>,JpaSpecificationExecutor<Movie> {

	List<Movie> findByMovieidIn(List<Integer> movieids);

	Movie findByMovieid(Integer movieid);

}

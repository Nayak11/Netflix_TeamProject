package com.moviecentral.mc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.moviecentral.mc.entity.MovieAttributes;

public interface MovieAttributesRepository extends JpaRepository<MovieAttributes, Integer> {
	@Transactional
	void deleteByMovieid(Integer movieid);
}

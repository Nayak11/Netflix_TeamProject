package com.moviecentral.mc.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moviecentral.mc.entity.Attributes;
import com.moviecentral.mc.entity.Review;
import com.moviecentral.mc.models.ReviewScore;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	@Query( "select new com.moviecentral.mc.models.ReviewScore(r, avg(r.rating)) from Review r where r.date >= :date group by r.movieid order by 2 desc")
	List<ReviewScore> findReviewScore(@Param("date")Timestamp date, @Param("pageable")Pageable pageable);

	@Query("select AVG(r.rating) from Review r where r.movieid = :movieid")
	Double averageRating(@Param("movieid")Integer movieid);

	List<Review> findByMovieid(Integer movieid);
}

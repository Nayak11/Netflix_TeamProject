package com.moviecentral.mc.repository;

import java.sql.Date;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moviecentral.mc.entity.Payment;
import com.moviecentral.mc.entity.User;


public interface PaymentRepository extends  JpaRepository<Payment, Integer>{
//Payment findbyId(Integer id);
	@Query( "SELECT DISTINCT e.userid FROM Payment e WHERE e.date >=?1 AND e.date <=?2") 
	ArrayList<Payment> findDistinctByUseridDateBetween(Timestamp s,Timestamp s1);
	ArrayList<Payment> findAmountByDateBetween(Timestamp s,Timestamp s1);
	
	@Query("select p from Payment p where p.date >= :date and p.userid = :userid and p.movieid = :movieid")
	Payment findIfPaid(@Param("date")Timestamp date, @Param("userid")Integer userid, @Param("movieid")Integer movieid);

}

package com.moviecentral.mc.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moviecentral.mc.entity.Payment;
import com.moviecentral.mc.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByUsernameOrEmail(String username, String email);
	User findByUsername(String username);
	User findByEmail(String email);
	ArrayList<User> findByStartdateBetween(Timestamp startdate,Timestamp enddate);
	
	@Query( "SELECT DISTINCT e.userid FROM User e WHERE e.signupdate >=?1 AND e.signupdate <=?2") 
	ArrayList<User> findDistinctByUseridSignupdateBetween(Timestamp s,Timestamp s1);

	List<User> findByUseridIn(List<Integer> userids);
}

package com.moviecentral.mc.controllers;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.moviecentral.mc.entity.User;
import com.moviecentral.mc.models.LoginResponse;
import com.moviecentral.mc.models.SignupRequest;
import com.moviecentral.mc.models.UserResponse;
import com.moviecentral.mc.repository.UserRepository;
import com.moviecentral.mc.utils.SessionMap;


@RestController

public class AdminUserController {
	/*
	public Integer userid;
	public String username;
	public String email;
	public String type;
	public String password;
	Integer subscription;
	Integer activated;
	String code;
	java.sql.Date startdate;
	java.sql.Date enddate;*/
	
	@Autowired
	
	private UserRepository userRepository;
	@Autowired
	private SessionMap sessionMap;
	//to fetch all the user details
	//System.out.println(crossOriginPath);
	
	
	@GetMapping(value="/user")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public ArrayList getAllUserDetails(){
		ArrayList<HashMap<String,String>> ar=new ArrayList<HashMap<String,String>>();
		//HashMap<String,String> inner = new HashMap<String,String>();
		for (User user : userRepository.findAll()) {
			//log.info(customer.toString());
			String email=user.getEmail();
			String[] end = email.split("@");
			if (end[1].equals("sjsu.edu")) {
					
			}
			else {
				
				HashMap<String,String> inner = new HashMap<String,String>();
				System.out.println("user id is "+String.valueOf(user.getUserid()));
				inner.put("userid",String.valueOf(user.getUserid()));
				inner.put("username",String.valueOf(user.getUsername()));
				inner.put("email",String.valueOf(user.getEmail()));
				inner.put("type",String.valueOf(user.getType()));
				inner.put("subscription",String.valueOf(user.getSubscription()));
				inner.put("activated",String.valueOf(user.getActivated()));
				inner.put("startdate",String.valueOf(user.getStartdate()));
			    inner.put("enddate",String.valueOf(user.getEnddate()));
				ar.add(inner);
			}	
			
		}
		
		return ar;
	}
	//to fetch individual user details
	@GetMapping(value="/user/{username}")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public ArrayList getOneUserDetails(@PathVariable String username,@RequestHeader("Authorization") Optional<String> sessionID){
		ArrayList<HashMap<String,String>> ar=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> inner = new HashMap<String,String>();
		
		if(!sessionID.isPresent() || sessionMap.getSessionMap().containsKey(sessionID.get()) == false){
			inner.put("status","invalid session");
			ar.add(inner);
			
		}else {
			User user=userRepository.findByUsername(username);
			//System.out.println("details based on username "+list1.getEmail());		
			System.out.println("user in admin user controller: "+user);
			if (user==null) {
				return null;
			}
				System.out.println("user id is of one user "+String.valueOf(user.getUserid()));
				inner.put("userid",String.valueOf(user.getUserid()));
				inner.put("username",String.valueOf(user.getUsername()));
				inner.put("type",String.valueOf(user.getType()));
				inner.put("email",String.valueOf(user.getEmail()));
				inner.put("subscription",String.valueOf(user.getSubscription()));
				inner.put("activated",String.valueOf(user.getActivated()));
				inner.put("startdate",String.valueOf(user.getStartdate()));
				inner.put("enddate",String.valueOf(user.getEnddate()));
				inner.put("status","success");
			
			ar.add(inner);
			
		}
		
		return ar;
	}
	
	
}

package com.moviecentral.mc.controllers;

import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.moviecentral.mc.entity.User;
import com.moviecentral.mc.models.LoginRequest;
import com.moviecentral.mc.models.LoginResponse;
import com.moviecentral.mc.repository.UserRepository;
import com.moviecentral.mc.utils.Session;
import com.moviecentral.mc.utils.SessionMap;

@RestController
public class LoginController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionMap sessionMap;
	
	
	@PostMapping("/login")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public LoginResponse login(@RequestBody LoginRequest req){
		String username = req.getUsername();
		String password = req.getPassword();

		User user = userRepository.findByUsername(username);
		if(user == null){
			return new LoginResponse("FAILURE", "", "invalid username");
		}
		
		if(passwordEncoder.matches(password, user.getPassword())){
			String sessionID = String.valueOf(new Random(System.nanoTime()).nextInt(100000000));
			sessionMap.getSessionMap().put(sessionID, new Session(user.getUserid(), user.getType(), 
									user.getUsername(), user.getEmail()));
			return new LoginResponse("SUCCESS", user.getType(), sessionID);
		} else {
			return new LoginResponse("FAILURE", user.getType(), "invalid password");
		}
	}
	
	@GetMapping(value = "/userlogout")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public LoginResponse logout(@RequestHeader("Authorization") Optional<String> sessionID){
		
		if(!sessionID.isPresent() || sessionMap.getSessionMap().containsKey(sessionID.get()) == false){
			return new LoginResponse("FAILURE", "", "invalid session");
		} else {
			sessionMap.getSessionMap().remove(sessionID.get());
			return new LoginResponse("SUCCESS", "", "logout successful");
		}
	}
	
	@GetMapping(value = "/checksession")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public LoginResponse checksession(@RequestHeader("Authorization") Optional<String> sessionID){
		Session s;
		String res = "", type="";
		if(!sessionID.isPresent() || sessionMap.getSessionMap().containsKey(sessionID.get()) == false){
			res = "invalid session";
		} else {
			s = sessionMap.getSessionMap().get(sessionID.get());
			System.out.println("This is session :" + s.getEmail());
			type = s.getType();
			res = "valid session";
		}
		return new LoginResponse("SUCCESS", type, res);
	}
	
	@GetMapping(value = "/getuser")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public LoginResponse checkuser(@RequestHeader("Authorization") Optional<String> sessionID){
		Session s = null;
		String res = "", type="";
		if(!sessionID.isPresent() || sessionMap.getSessionMap().containsKey(sessionID.get()) == false){
			res = "invalid session";
		} else {
			s = sessionMap.getSessionMap().get(sessionID.get());
			System.out.println("This is session :" + s.getEmail());
			type = s.getType();
			res = "valid session";
		}
		return new LoginResponse("SUCCESS", Integer.toString(s.getUserid()), s.getUsername());
	}
}

package com.moviecentral.mc.controllers;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.moviecentral.mc.entity.User;
import com.moviecentral.mc.models.LoginResponse;
import com.moviecentral.mc.models.SignupRequest;
import com.moviecentral.mc.repository.UserRepository;
import com.moviecentral.mc.utils.EmailService;
import com.moviecentral.mc.utils.Session;

@RestController
public class SignupController {
	
	@Autowired
	private EmailService emailSer;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(value="/signup")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public LoginResponse signup(@RequestBody SignupRequest req){
		String username = req.getUsername();
		String password = req.getPassword();
		String email = req.getEmail();
		String emailSplit[] = email.split("@");
		String type;
		if(emailSplit[1].equals("sjsu.edu")){
			type = "ADMIN";
		} else {
			type = "USER";
		}
		
		//username, password or email cannot be empty
		if(username.equals("")){
			return new LoginResponse("FAILURE", type, "EMPTY_USERNAME");
		}
		if(password.equals("")){
			return new LoginResponse("FAILURE", type, "EMPTY_PASSWORD");
		}
		if(email.equals("")){
			return new LoginResponse("FAILURE", type, "EMPTY_EMAIL");
		}
		
		//email or username already exits
		List<User> users = userRepository.findByUsernameOrEmail(username, email);
		if(users.size() > 0){
			return new LoginResponse("FAILURE", type, "username or email already exists");
		}
		
		Date newdate=new Date();
	    java.sql.Timestamp presentdate = new Timestamp(newdate.getTime());
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setType(type);
		user.setActivated(0);
		user.setSubscription(0);
		user.setSignupdate(presentdate);
		String code = String.valueOf(new Random(System.nanoTime()).nextInt(100000));
		user.setCode(code);
		user = userRepository.save(user);
		
        emailSer.sendEmail(code, email);
		
		return new LoginResponse("SUCCESS", type, "user created");
	}
	
	@GetMapping(value="/verify")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public LoginResponse verify(@RequestParam("email") String email, @RequestParam("code") String code){
		System.out.println("code "+code);
		System.out.println("email "+email);
		User user = userRepository.findByEmail(email);
		
		if(user == null){
			System.out.println("inside verify failure 1");
			return new LoginResponse("FAILURE", "", "invalid email");
		}
		
		if(user.getCode().equals(code)){
			user.setActivated(1);
			userRepository.save(user);
			System.out.println("inside verify success");
			return new LoginResponse("SUCCESS", user.getType(), "code successful");
		} else {
			System.out.println("inside verify failure");
			return new LoginResponse("FAILURE", user.getType(), "code invalid");
		}
	}
	
}

package com.moviecentral.mc.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionMap {
	public SessionMap() {
		super();
	}

	Map<String, Session> sessionMap = new HashMap<String, Session>();

	public Map<String, Session> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Session> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Bean
	public SessionMap session(){
		return new SessionMap();
	}
}

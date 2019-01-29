package com.moviecentral.mc.models;

import org.springframework.beans.factory.annotation.Autowired;

import com.moviecentral.mc.repository.PaymentRepository;

public class PaymentResponse {
	private final String status;
	


	private final String message;



	public PaymentResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}



	public String getStatus() {
		return status;
	}



	public String getMessage() {
		return message;
	}
	
	
	
	
	
	
	
	
	
}

package com.moviecentral.mc.models;

public class FinancialRequest {

	public FinancialRequest() {
		super();
	}

	private Integer month;
	private Integer year;
	
	
	public FinancialRequest(Integer month, Integer year) {
		super();
		this.month = month;
		this.year = year;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
}

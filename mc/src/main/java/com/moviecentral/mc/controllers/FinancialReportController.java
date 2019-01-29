package com.moviecentral.mc.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviecentral.mc.entity.Payment;
import com.moviecentral.mc.entity.User;
import com.moviecentral.mc.models.FinancialRequest;
import com.moviecentral.mc.models.PaymentRequest;
import com.moviecentral.mc.entity.PlayHistory;
import com.moviecentral.mc.repository.PaymentRepository;
import com.moviecentral.mc.repository.PlayHistoryRepository;
import com.moviecentral.mc.repository.UserRepository;
@RestController
public class FinancialReportController {

	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	public PaymentRepository paymentRepository;
	@Autowired
	public PlayHistoryRepository playhistoryRepository;
	
	//public FinancialRequest financialRequest;
	//to fetch all the user details
	//System.out.println(crossOriginPath);
	
	
	@PostMapping(value="/financial")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public ArrayList getFinancialMetrics(@RequestBody FinancialRequest req){
		ArrayList<HashMap> ar=new ArrayList<HashMap>();
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		System.out.println("in financial");
		int year=req.getYear();
		int month=req.getMonth();
		Date newdate=new Date();
	    java.sql.Timestamp presentdate = new Timestamp(newdate.getTime());
		
	    Calendar mycal = new GregorianCalendar(year,month, 1);
	    long startDate = mycal.getTimeInMillis();
	    java.sql.Timestamp s=new Timestamp(startDate);

	    // Get the number of days in that month which actually gives the last date.
	    int lastDate = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    mycal = new GregorianCalendar(year, month, lastDate);
	    long endDate = mycal.getTimeInMillis();
	    java.sql.Timestamp s1=new Timestamp(endDate);
	    
	    System.out.println("start and end dates" + s+" "+s1);
	    
	    int uniqueSubscriptionUsers=0;
	   ArrayList ans=userRepository.findByStartdateBetween(s, s1);
	  ArrayList<User> t= userRepository.findDistinctByUseridSignupdateBetween(s, s1);
	  
	  //System.out.println("t "+t.size());
	  hm.put("totalNumberOfUsers", t.size());
//	    System.out.println("val  "+ totalNumberOfUsers);
//	    System.out.println("ans is " +((User) ans.get(0)).getUsername());
	    for (int i=0;i<ans.size();i++) {
	    	
	    	if(((User) ans.get(i)).getSubscription()==1) {
	    		uniqueSubscriptionUsers++;
	    		
	    	}
	    }
	    System.out.println("unique Subscription Users " +uniqueSubscriptionUsers);
	   
	    hm.put("uniqueSubscriptionUsers", uniqueSubscriptionUsers);
	    
	    
	    
	    //find distinct payperview users per month
	    ArrayList<Payment> distPayPerViewUsers = new ArrayList<Payment>();
	   distPayPerViewUsers=paymentRepository.findDistinctByUseridDateBetween(s, s1);
	    
	    System.out.println("distPayPerView " +distPayPerViewUsers);
	    hm.put("distPayPerViewUsers",distPayPerViewUsers.size());
	    
	    
	    ArrayList am=paymentRepository.findAmountByDateBetween(s, s1);
	   int revenue=0;
	   int subscriptionRevenue=0;
	   int payperviewRevenue=0;
			   
	    for (int i=0;i<am.size();i++) {
	    	revenue+=((Payment) am.get(i)).getAmount();
	    	if (((Payment) am.get(i)).getType().equals("subscription")) {
	    		subscriptionRevenue+=((Payment) am.get(i)).getAmount();
	    		
	    	}
	    	else {
	    		payperviewRevenue+=((Payment) am.get(i)).getAmount();
	    		
	    	}
	    	
	    	//System.out.println("am "+((Payment) am.get(i)).getAmount());
	    }
       hm.put("revenue",revenue);
       hm.put("subscriptionRevenue",subscriptionRevenue);
	   hm.put("payperviewRevenue",payperviewRevenue);
	   System.out.println("revenue"+revenue);
	   System.out.println("subscriptionRevenue"+subscriptionRevenue);
	   System.out.println("payperviewRevenue"+payperviewRevenue);
	   
	   
	   //Unique active users
	   
	   ArrayList<PlayHistory> active=(ArrayList<PlayHistory>) playhistoryRepository.findUseridDateBetween(s, s1);
	   //System.out.println("active "+active.get(0).getType());

	   
	   
	   int uniqueActiveUsers=0;
	   int uniquePayPerViewUsers=0;
	   for (int i=0;i<active.size();i++) {
		   uniqueActiveUsers++;
		   if(( active.get(i).getType() != null &&  active.get(i).getType().equals("PayPerView"))) {
			   uniquePayPerViewUsers++;
		   }
		   //System.out.println("userid "+ ((PlayHistory) active.get(i)).getUserid());
	   }
	   
	   //System.out.println("uniqueActiveUsers "+uniqueActiveUsers); 
	   //System.out.println("uniquePayPerViewUsers "+uniquePayPerViewUsers); 
	       
	 hm.put("uniqueActiveUsers",uniqueActiveUsers);
	 hm.put("uniquePayPerViewUsers",uniquePayPerViewUsers);
	       
	ar.add(hm);		
		
	if(hm.size()==0) {
		 hm.put("uniqueActiveUsers",0);
		 hm.put("uniquePayPerViewUsers",0);
		 hm.put("revenue",0);
	     hm.put("subscriptionRevenue",0);
		 hm.put("payperviewRevenue",0);
		 hm.put("distPayPerViewUsers",0);
		 hm.put("uniqueSubscriptionUsers", 0);
		 return ar;
	} else {
		return ar;
	}
	}
	
}

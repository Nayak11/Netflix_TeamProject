package com.moviecentral.mc.controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviecentral.mc.entity.Payment;
import com.moviecentral.mc.entity.User;
import com.moviecentral.mc.models.LoginResponse;
import com.moviecentral.mc.models.PaymentRequest;
import com.moviecentral.mc.models.PaymentResponse;
import com.moviecentral.mc.repository.PaymentRepository;
import com.moviecentral.mc.repository.UserRepository;


@RestController

public class PaymentController {

	
@Autowired
public PaymentRepository paymentRepository;
@Autowired

public UserRepository userRepository;
Payment payment=new Payment();
@PostMapping(value="/payment")
@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
@ResponseBody
public PaymentResponse makePayment(@RequestBody PaymentRequest req,@RequestParam("type") String type) throws ParseException{
	System.out.println("req id in may payment is "+req.getUserid());
	System.out.println("req in may payment is "+req);
	System.out.println("type in may payment is "+type);
	if(type.equals("subscription")) {	
	
	 String username=req.getUsername();
	 Integer userid=req.getUserid();
	 Integer movieid=req.getMovieid();
	//String type=req.getType();
	//java.sql.Date date=req.getDate();
	Integer amount=req.getAmount();
	//java.sql.Date startdate=req.getStartdate();
	
	Integer selectedMonth=req.getSelectedMonth();
	
	String expirydate;
	System.out.println("type "+type);
	if(userid.equals(null)){
		return new PaymentResponse("FAILURE", "EMPTY_USERID");
	}
	User user=userRepository.findByUsername(username);
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date curdate = new Date();
	String todayDate=dateFormat.format(curdate);
	if (user.getSubscription()==0) {
	
	System.out.println("current date "+dateFormat.format(curdate));
	
	String[] s = todayDate.split(" ");
	String[] s1=s[0].split("-");
	int day=Integer.parseInt(s1[2]);
	int month=Integer.parseInt(s1[1]);
	int year=Integer.parseInt(s1[0]);
	
    String expirymonth="";
	String expiryday;
	String expiryyear;
	
	
	if(selectedMonth+month>12) {
		int m=selectedMonth+month-12;
		expiryyear=Integer.toString(year+1);
		if (m<10){
            expirymonth='0'+Integer.toString(m);
        }
		else {
			expirymonth=Integer.toString(m);
		}
	}
	
	else {
		int m=selectedMonth+month;
		expiryyear=Integer.toString(year);
		if (m<10){
            expirymonth='0'+Integer.toString(m);
        }
		else {
			expirymonth=Integer.toString(m);
		}
	}
	String date = expirymonth+"/1/"+expiryyear;
	LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/d/yyyy"));
	convertedDate = convertedDate.withDayOfMonth(
	                                convertedDate.getMonth().length(convertedDate.isLeapYear()));

	String t=convertedDate.toString();
	String[] t1=t.split("-");
	String lastday=t1[2];
	System.out.println("convertedDate "+lastday);
	if(day>Integer.parseInt(lastday)) {
		
		expiryday=lastday;
		if (Integer.parseInt(expiryday)<10){
			expiryday='0'+expiryday;
        }
	}
	else {
		
		expiryday=Integer.toString(day);
		if (Integer.parseInt(expiryday)<10){
			expiryday='0'+expiryday;
        }
	}
	
	expirydate=expiryyear + '-' + expirymonth +'-' + expiryday +" 00:00:00";
	
	
	System.out.println("expiry date "+expirydate);
	}
	
	else {
		String endate=user.getEnddate().toString();
		System.out.println("user.getEndDate() "+user.getEnddate());
		System.out.println("enddate "+endate);
		String[] n=endate.split(" ");
		String[] s=n[0].split("-");
		int day=Integer.parseInt(s[2]);
		int month=Integer.parseInt(s[1]);
		int year=Integer.parseInt(s[0]);
		//System.out.println("enddate "+month);
		String expirymonth="";
		String expiryday;
		String expiryyear;
		System.out.println("selectedMonth "+selectedMonth);
		if(selectedMonth+month>12) {
			int m=selectedMonth+month-12;
			expiryyear=Integer.toString(year+1);
			if (m<10){
	            expirymonth='0'+Integer.toString(m);
	        }
			else {
				expirymonth=Integer.toString(m);
			}
		}
		
		else {
			int m=selectedMonth+month;
			expiryyear=Integer.toString(year);
			if (m<10){
	            expirymonth='0'+Integer.toString(m);
	        }
			else {
				expirymonth=Integer.toString(m);
			}
		}
		System.out.println("expirymonth "+expirymonth);
		String date = expirymonth+"/1/"+expiryyear;
		LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/d/yyyy"));
		convertedDate = convertedDate.withDayOfMonth(
		                                convertedDate.getMonth().length(convertedDate.isLeapYear()));

		String t=convertedDate.toString();
		System.out.println("t "+t);
		String[] t1=t.split("-");
		String lastday=t1[2];
		System.out.println("convertedDate "+lastday);
		
		if(day>Integer.parseInt(lastday)) {
			
			expiryday=lastday;
			if (Integer.parseInt(expiryday)<10){
				expiryday='0'+expiryday;
	        }
		}
		else {
			
			expiryday=Integer.toString(day);
			if (Integer.parseInt(expiryday)<10){
				expiryday='0'+expiryday;
	        }
		}
		
		expirydate=expiryyear + '-' + expirymonth +'-' + expiryday +" "+"00:00:00";
		
		
		System.out.println("expiry date below "+expirydate);
		
		
		 
	}
	DateFormat formatter;
    formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    Date date2 = (Date) formatter.parse(todayDate);
    Date newdate=new Date();
    java.sql.Timestamp presentdate = new Timestamp(newdate.getTime());
    System.out.println("presentdate "+presentdate);
	payment.setUserid(userid);
	
	payment.setMovieid(movieid);
	
	payment.setType(type);
	
	payment.setDate(presentdate);
	
	payment.setAmount(amount);
	
	
	
	paymentRepository.save(payment);
	
	
	 System.out.println("payment variable of save ");
	 
	// User user=userRepository.findByUsername(username);
	
	System.out.println("user in payment is "+user.getUserid());
	Integer sub=user.getSubscription();
	
	//Date date1 = (Date) formatter.parse(expirydate);
   // java.sql.Timestamp timeStampDate1 = new Timestamp(date1.getTime());
	System.out.println("expiry date before ts set "+expirydate);
    Timestamp ts = Timestamp.valueOf(expirydate);
    System.out.println("ts"+ts);
   // System.out.println("date1.getTime()"+date1.getTime());
   // System.out.println("timeStampDate1"+timeStampDate1);
	if (type.equals("subscription")) {
		
		user.setSubscription(1);
	}
	else {
		user.setSubscription(0);
	}
	System.out.println("ts converted "+ts);
	user.setEnddate(ts);
	if (sub!=1) {
		
		user.setStartdate(presentdate);
	}
	
	userRepository.save(user);
	
	
	}else {
		Date newdate=new Date();
	    java.sql.Timestamp presentdate = new Timestamp(newdate.getTime());
	    System.out.println("presentdate "+presentdate);
	    
	    payment.setAmount(req.getAmount());
	    payment.setDate(presentdate);
	    payment.setMovieid(req.getMovieid());
	    payment.setUserid(req.getUserid());
	    payment.setType("paid");
	    
	    paymentRepository.save(payment);
		
	}
	return new PaymentResponse("SUCCESS", "payment done");
	
}
}

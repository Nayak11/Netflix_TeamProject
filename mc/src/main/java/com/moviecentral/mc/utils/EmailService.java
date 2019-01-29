package com.moviecentral.mc.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sendgrid.*;
import java.io.IOException;

@Configuration
public class EmailService {
	
	@Bean
	public EmailService emailSer(){
		return new EmailService();
	}
	
	public boolean sendEmail(String code, String email){
		Email from = new Email("verification@moviecentral.com");
	    String subject = "Verification Email from Movie Central";
	    Email to = new Email(email);
	    
	    //update it with front end email
	    Content content = new Content("text/plain", "Please click on this link : " + "http://54.193.119.24:3000/verify?code="+code+"&email="+email);
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    
	    return true;
	}
}

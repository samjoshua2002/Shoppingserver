package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailServiceContact {

	@Autowired
	private JavaMailSender mailsender;
	
	@Value("${spring.mail.username}")
	private String username;
	
	
	public void sendMail(String ToFrom,String ToId,String body,String subject) {
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setFrom(ToFrom);
		message.setTo(ToId);
		message.setText(body);
		message.setSubject(subject);
		message.setReplyTo(ToFrom);
		
		
		mailsender.send(message);
		
	}
	public void sendReply(String ToId, String name) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(username);
		message.setTo(ToId);
		message.setText("Dear " + name + ",\n" + 
						"\n" + 
						"Thank you for reaching out to Trendix. We have received your query/feedback and our team is currently reviewing it. We are working diligently to provide you with the best possible response and will update you soon with more information.\n" + 
						"\n" + 
						"We truly appreciate your input and patience as we strive to enhance your experience with us.\n" + 
						"\n" + 
						"Best regards,\n" + 
						"Sam Joshua\n" + 
						"Customer Support Team\n" + 
						"Trendix");
		message.setSubject("Acknowledgment of Your Query/Feedback");
		
		mailsender.send(message);
	}
	
	
	
}

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
	private String ToId;
	
	public void sendMail(String ToFrom,String body,String subject) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(ToFrom);
		message.setTo(ToId);
		message.setText(body);
		message.setSubject(subject);
		
		mailsender.send(message);
	}
	
}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

	@Autowired
	private JavaMailSender mailsender;
	
	@Value("${spring.mail.username}")
	private String fromId;
	
	public void sendMail(String toId,String body,String subject) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(fromId);
		message.setTo(toId);
		message.setText(body);
		message.setSubject(subject);
		
		mailsender.send(message);
	}
	
}

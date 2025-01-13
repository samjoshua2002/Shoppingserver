package com.example.demo.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class OtpService {
	

	@Autowired
	private JavaMailSender mailsender;
	
	@Value("${spring.mail.username}")
	private String fromId;
	
	public String otpGenerator() {
		Random random = new Random();
		int otp=random.nextInt(9999);
		return String.format("%04d", otp);
	}
	
	public void otpSender(String mail,String otp) {
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setFrom(fromId);
        message.setTo(mail);
        message.setSubject("Your OTP for registraion.");
        message.setText("Your OTP code for All-In-All  is: " + otp);
        
        mailsender.send(message);
	}
	
//	public String registerUser(Aentity user) {
//	    String otp = otpGenerator(); 
//	    System.out.println("Generated OTP: " + otp);
//
//	    try {
//	        String phoneRegex = "^\\d{10}$";
//	        String nameRegex = "^[A-Za-z\\s]+$";
//	        String phoneString = String.valueOf(user.getUsernumber());
//	        Optional<Aentity> existingUser = arepo.findByUseremailOrUsernumber(user.getUseremail(), user.getUsernumber());
//
//	        if (existingUser.isPresent()) {
//	            System.out.println("Account already exists.");
//	            return "Account already exists";
//	        }
//	        if (!phoneString.matches(phoneRegex)) {
//	            System.out.println("Invalid phone number.");
//	            return "Invalid phone number. It must be 10 digits.";
//	        }
//	        if (!user.getUsername().matches(nameRegex)) {
//	            System.out.println("Invalid name.");
//	            return "Invalid name. It must contain only letters and spaces.";
//	        }
//
//	        user.setUserotp(otp);
//	        arepo.save(user);
//	        System.out.println("User saved to database: " + user);
//
//	        otpSender(user.getUseremail(), otp);
//	        System.out.println("OTP sent to email: " + user.getUseremail());
//
//	        return "Registered successfully. OTP has been sent to your email.";
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return "An error occurred during registration.";
//	    }
//	}


	
//	public boolean otpVerify(String mail,String otp) {
//		Optional<Aentity>user=arepo.findByUseremail(mail);
//		if(user.isPresent()) {
//			System.out.println("User is present " + user);
//			if(user.get().getUserotp().equals(otp)) {
//				System.out.println("User otp" + user.get().getUserotp());
//				return true;
//			}
//		}else {
//			System.out.println("User is not present " + user);
//		}
//		return false;
//	}
//	
//	public void setPassword(String mail,String password) {
//		Optional<Aentity>user=arepo.findByUseremail(mail);
//		Aentity USER=user.get() ;
//		if(user.isPresent()) {
//			USER.setUserpassword(password);
//			arepo.save(USER);
//		}
//	}
	
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ContactMessage;
import com.example.demo.repository.ContactMessageRepository;
import com.example.demo.service.EmailServiceContact;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.234:3000","https://trendix-seven.vercel.app/"})
public class ContactController {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Autowired
    private EmailServiceContact emailService;

    @PostMapping
    public ResponseEntity<String> submitContactMessage(@RequestBody ContactMessage contactMessage) {
        // Save the contact message to the database
        contactMessageRepository.save(contactMessage);

        // Send email notification
        String subject = "New Contact Form Submission";
        String body = "Name: " + contactMessage.getName() +
                      "\nEmail: " + contactMessage.getEmail() + // Sender's email
                      "\nMessage: " + contactMessage.getMessage();
            String to = "trendixauth@gmail.com";
        // Send mail to the username configured in application.properties
        emailService.sendMail( contactMessage.getEmail(),to, body, subject);
        emailService.sendReply(contactMessage.getEmail(),contactMessage.getName()); // To: username

        return ResponseEntity.ok("Message submitted successfully!");
    }

   

}

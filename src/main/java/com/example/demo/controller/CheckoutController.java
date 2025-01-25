package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CheckoutMessage;
import com.example.demo.repository.CheckoutMessageRepository;
import com.example.demo.service.EmailServiceCheckout;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.234:3000","https://trendix-seven.vercel.app/"})
public class CheckoutController {

    @Autowired
    private CheckoutMessageRepository checkoutMessageRepository;

    @Autowired
    private EmailServiceCheckout emailServiceCheckout;

    @PostMapping
    public ResponseEntity<String> processCheckout(@RequestBody CheckoutMessage checkoutMessage) {
        // Save the checkout message to the database
        checkoutMessageRepository.save(checkoutMessage);

        // Prepare email details for Trendix
        String subjectToTrendix = "Checkout Notification";
        String bodyToTrendix = "Name: " + checkoutMessage.getName() +
                               "\nEmail: " + checkoutMessage.getEmail() +
                               "\nMessage: " + checkoutMessage.getMessage();
        String trendixEmail = "trendixauth@gmail.com";

        // Send email to Trendix
        emailServiceCheckout.sendCheckout(checkoutMessage.getEmail(), trendixEmail, bodyToTrendix, subjectToTrendix);

        // Send acknowledgment email to the user
        // Send acknowledgment email to the user with their original message
        emailServiceCheckout.sendReply(checkoutMessage.getEmail(), checkoutMessage.getName(), checkoutMessage.getMessage());


        return ResponseEntity.ok("Checkout processed successfully!");
    }
}

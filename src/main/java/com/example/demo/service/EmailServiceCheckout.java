package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceCheckout {

    @Autowired
    private JavaMailSender mailsender;

    @Value("${spring.mail.username}")
    private String username;

    // Method to send email to Trendix
    public void sendCheckout(String fromEmail, String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        message.setReplyTo(fromEmail);

        mailsender.send(message);
    }

    // Method to send acknowledgment email to the user
    // Method to send acknowledgment email to the user, including their original message
public void sendReply(String toEmail, String name, String originalMessage) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(username);
    message.setTo(toEmail);
    message.setText("Dear " + name + ",\n\n" +
                    "Thank you for your checkout submission to Trendix. We have received your message and will process it shortly.\n\n" +
                    "Here is a copy of your message for reference:\n" +
                    originalMessage + "\n\n" +
                    "If you have any further queries, feel free to reach out.\n\n" +
                    "Best regards,\n" +
                    "Trendix Team");
    message.setSubject("Acknowledgment of Your Checkout Submission");

    mailsender.send(message);
}

}

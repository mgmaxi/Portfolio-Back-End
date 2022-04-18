package com.example.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;


@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String name,
                                String email,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("maxi.mghirardi@gmail.com");
        message.setTo("maxi.mghirardi@gmail.com");
        message.setSubject("Portfolio - Contact: " + name);
        message.setText(body + " || Email from: " + email);

        mailSender.send(message);
    }

}

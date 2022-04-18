package com.example.portfolio.controller;

import com.example.portfolio.dto.Email;
import com.example.portfolio.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailService;

    @PostMapping("/email")
    public void sendEmail(@RequestBody Email email) {

        emailService.sendSimpleEmail(email.getName(), email.getEmail(), email.getBody(), email.getSubject());

    }
}

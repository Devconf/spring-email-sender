package com.example.email.controller;

import com.example.email.application.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping(value = "/send")
    public ResponseEntity send(){
        emailService.sendEmail();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

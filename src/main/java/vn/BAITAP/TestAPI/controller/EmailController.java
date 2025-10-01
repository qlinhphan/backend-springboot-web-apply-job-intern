package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.service.EmailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EmailController {
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email/text")
    public String getMethodName() {
        this.emailService.sendEmail("qlinhphan@gmail.com", "send to you", "It is true not false");
        return "send";
    }

}

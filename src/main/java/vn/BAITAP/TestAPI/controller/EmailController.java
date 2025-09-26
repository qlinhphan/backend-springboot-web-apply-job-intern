package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import vn.BAITAP.TestAPI.service.EmailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EmailController {
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email/text")
    public String postMethodName() {
        this.emailService.sendTextEmail("qlinhphan@gmail.com", "OK", "send to you");

        return "sent";
    }

    @GetMapping("/email/temp")
    public String getMethodName() {
        Context ct = new Context();
        ct.setVariable("myName", "Linh");
        try {
            this.emailService.sendTempEmail("qlinhphan@gmail.com", "OK", "test", ct);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "sent";
    }

}

package com.compny.sms.email;

import com.compny.exception.EmailSendException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void send(String toEmail, String title, String content) {
        try {
            SimpleMailMessage simple = new SimpleMailMessage();
            simple.setTo(toEmail);
            simple.setSubject(title);
            simple.setText(content);
            javaMailSender.send(simple);
        }catch (RuntimeException e){
            throw new EmailSendException("Does not send sms code to email ");
        }

    }
}

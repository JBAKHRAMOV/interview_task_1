package com.compny.sms.service;



import com.compny.sms.email.EmailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service("sms-service")
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    @Value("${sms.sender.nik}")
    private String SENDER_NIK;

    private final EmailService emailService;



    public Boolean sendMsgToPhone(String phoneNum, String text) {

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post("https://sms.sysdc.ru/index.php")
                    .header("Authorization", "Bearer XPBtdijkWimj6DJ3sCpjNTnv46EJJRPbeUvqZqajUupnn5mvI7cKKGvtGpUjMbMO")
                    .field("sender", SENDER_NIK)
                    .field("mobile_phone", phoneNum)
                    .field("message", text)
                    .asString();

            if (response.getStatus() == 200) return true;

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Boolean sendSmsToEmail(String email, String text) {

        String title = "Shifo 24";

        emailService.send(email, title, text);

        return true;
    }


}

package com.compny.sms.service;


public interface SmsService {

    Boolean sendMsgToPhone(String phoneNum, String message);
    Boolean sendSmsToEmail(String email, String message);

}

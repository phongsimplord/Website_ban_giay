package com.example.demo.email.service;

import org.springframework.stereotype.Service;


public interface EmailService {
    void sendOtp(String emailNhan);
    Boolean isValidOtp(String email,String otp);
}

package com.mblog.mblog.service;


import com.mblog.mblog.entites.User;
import com.mblog.mblog.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private userService userservice;
    static final Map<String,String> emailotpMapping=new HashMap<>();
    public Map<String,String> verifyOTP(String email,String otp){
        String storedotp = emailotpMapping.get(email);
        Map<String,String> response = new HashMap<>();
        if(storedotp!=null && storedotp.equals(otp)) {
            User user = userservice.findByEmail(email).get();
            if (user != null) {
                userservice.verifyEmail(user);
                emailotpMapping.remove(email);
                response.put("Status: ", "Success");
                response.put("message: ", "Email Verified Succesfully");
            }
            else {
                response.put("status", "error");
                response.put("message", "User not found");
            }
        }
        else{
            response.put("status", "error");
            response.put("message", "Invalid OTP");
        }
        return response;
    }
}

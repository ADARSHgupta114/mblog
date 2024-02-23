package com.mblog.mblog.service;

import com.mblog.mblog.entites.User;
import com.mblog.mblog.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class forgotPasswordService{

    @Autowired
    private userService userservice;
    static final Map<String,String> forgotpassotpMapping=new HashMap<>();
   public Map<String,String> verfiychangePassword(String email, String otp, String password){
       User user = userservice.findByEmail(email).get();
       Map<String,String> response = new HashMap<>();
       String storedotp = forgotpassotpMapping.get(email);
       if(storedotp!=null && storedotp.equals(otp)){
           if(user!=null){
               userservice.forgotpasswordchange(user,password);
               response.put("Status: ", "Success");
               response.put("message: ", "Password Changed Succesfully");
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

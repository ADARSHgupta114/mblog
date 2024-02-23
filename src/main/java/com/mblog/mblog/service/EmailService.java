package com.mblog.mblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.mblog.mblog.repository.userRepository;
import static  com.mblog.mblog.service.EmailVerificationService.emailotpMapping;
import static  com.mblog.mblog.service.forgotPasswordService.forgotpassotpMapping;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javamailsender;

    private userRepository userRepository;

    @Autowired
    public EmailService(JavaMailSender javamailsender) {
        this.javamailsender = javamailsender;
    }

    public String generateOTP(){
        return String.format("%06d",new java.util.Random().nextInt(1000000));
    }


    public void sendotpEmailForget(String email){
        String otp = generateOTP();
        forgotpassotpMapping.put(email,otp);
        sendEmail(email,"Otp for Email Verification","Your OTP "+otp);
    }
    public void sendotpEmail(String email){
        String otp = generateOTP();
        emailotpMapping.put(email,otp);
        sendEmail(email,"Otp for Email Verification","Your OTP "+otp);

    }
    private void sendEmail(String to,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("p7151215@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javamailsender.send(message);
    }

    //OTP LINKING FOR EMAIL VERIFICATION
    public void sendotplinkEmail(String email,String URL) {
        String otp = generateOTP();
        emailotpMapping.put(email,otp);
        sendEmail(email,"Link for Email Verification","http://localhost:8080/api/auth/verify-email?email="+email+"&otp="+otp);
    }
}

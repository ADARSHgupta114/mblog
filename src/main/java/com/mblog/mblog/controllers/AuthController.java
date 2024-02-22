package com.mblog.mblog.controllers;

import com.mblog.mblog.entites.User;
import com.mblog.mblog.payload.LoginDTO;
import com.mblog.mblog.payload.Role;
import com.mblog.mblog.payload.SingupDTO;
import com.mblog.mblog.repository.roleRepository;
import com.mblog.mblog.repository.userRepository;
import com.mblog.mblog.service.EmailService;
import com.mblog.mblog.service.EmailVerificationService;
import com.mblog.mblog.service.impl.userServiceImpl;
import com.mblog.mblog.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationmanager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private roleRepository rolerepository;

    private userService userservice;

    public AuthController(userService userservice) {
        this.userservice = userservice;
    }

    @Autowired
    private EmailService emailserive;

    @Autowired
    private EmailVerificationService emailVerificationService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SingupDTO signDTO){
        if(userservice.existsByUsername(signDTO.getUsername()))
            return new ResponseEntity<>("UserName is Already Taken", HttpStatus.BAD_REQUEST);
        if(userservice.existsByEmail(signDTO.getEmail()))
            return  new ResponseEntity<>("Email id is Already Registered",HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setEmail(signDTO.getEmail());
        user.setName(signDTO.getName());
        user.setUsername(signDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signDTO.getPassword()));
        Role roles = rolerepository.findByName("ROLE_ADMIN").get();
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(roles);
        user.setRoles(roleSet);
        emailserive.sendotpEmail(signDTO.getEmail());
        userservice.saveUser(user);
        Map<String,String> response = new HashMap<>();
        response.put("Status: ","Success");
        response.put("Message:","User Registered Successfully check your Email");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> emailVerify(@RequestParam String email,@RequestParam String otp){
       Map<String,String> verified = emailVerificationService.verifyOTP(email,otp);
        return new ResponseEntity<>(verified,HttpStatus.OK);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO logindto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(logindto.getUsernameOrEmail(), logindto.getPassword());
        Authentication authenticate = authenticationmanager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>("User Signed in SuccessFully",HttpStatus.OK);
    }
}

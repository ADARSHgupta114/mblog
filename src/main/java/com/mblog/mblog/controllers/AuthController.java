package com.mblog.mblog.controllers;

import com.mblog.mblog.entites.User;
import com.mblog.mblog.payload.LoginDTO;
import com.mblog.mblog.payload.Role;
import com.mblog.mblog.payload.SingupDTO;
import com.mblog.mblog.repository.roleRepository;
import com.mblog.mblog.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
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
    @Autowired
    private userRepository userrepository;



    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SingupDTO signDTO){
        if(userrepository.existsByUsername(signDTO.getUsername()))
            return new ResponseEntity<>("UserName is Already Taken", HttpStatus.BAD_REQUEST);
        if(userrepository.existsByEmail(signDTO.getEmail()))
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
        userrepository.save(user);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO logindto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(logindto.getUsernameOrEmail(), logindto.getPassword());
        Authentication authenticate = authenticationmanager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>("User Signed in SuccessFully",HttpStatus.OK);
    }
}

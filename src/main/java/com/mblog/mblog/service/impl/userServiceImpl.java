package com.mblog.mblog.service.impl;

import com.mblog.mblog.entites.User;
import com.mblog.mblog.repository.userRepository;
import com.mblog.mblog.service.userService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class userServiceImpl implements userService {

    private userRepository userrepository;

    public userServiceImpl(userRepository userrepository) {
        this.userrepository = userrepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userrepository.findByEmail(email);
    }



    @Override
    public User getUserByEmail(String email) {
        return userrepository.findByUsername(email).get();
    }

    @Override
    public void verifyEmail(User user) {
        user.setVerifiedEmail(true);
        userrepository.save(user);
    }

    @Override
    public boolean isEmailVerified(String email) {
        return false;
    }

    @Override
    public void saveUser(User user) {
        userrepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userrepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userrepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userrepository.findByUsernameOrEmail(username,email);
    }
}

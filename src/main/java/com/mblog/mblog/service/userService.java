package com.mblog.mblog.service;

import com.mblog.mblog.entites.User;

import java.util.Optional;

public interface userService
{
    Optional<User> findByEmail(String email);

    public User getUserByEmail(String email);

    public void verifyEmail(User user);
    public boolean isEmailVerified(String email);

    void saveUser(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String username1);

    void forgotpasswordchange(User user,String password);
}

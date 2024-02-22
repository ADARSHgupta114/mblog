package com.mblog.mblog.security;

import com.mblog.mblog.entites.User;
import com.mblog.mblog.payload.Role;
import com.mblog.mblog.repository.userRepository;
import com.mblog.mblog.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomerUserDetailsService implements UserDetailsService {


    private userService userservice;



    public CustomerUserDetailsService(userService userservice) {
        this.userservice = userservice;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userservice.findByUsernameOrEmail(username, username).orElseThrow(()->
                new UsernameNotFoundException("User Not Found with username or Email"+username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

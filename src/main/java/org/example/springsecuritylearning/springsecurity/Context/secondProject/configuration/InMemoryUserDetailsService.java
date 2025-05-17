package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {
    private final List<UserDetails> userDetails;
    public InMemoryUserDetailsService(List<UserDetails> userDetails){
        this.userDetails=userDetails ;


    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userDetails.stream()
                .filter((u)->u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException(username));


    }

}

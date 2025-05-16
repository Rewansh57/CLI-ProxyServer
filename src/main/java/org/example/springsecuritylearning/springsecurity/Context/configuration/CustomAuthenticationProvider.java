package org.example.springsecuritylearning.springsecurity.Context.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
      if ( ("John".equals(username) && ("123").equals(password))){
          return new UsernamePasswordAuthenticationToken(username,password, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        }
        else {
            throw new BadCredentialsException("Bad Credentials");

        }


    }
    @Override
    public boolean supports(Class<?>  authenticationType){
        return UsernamePasswordAuthenticationToken
                .class
                .isAssignableFrom(authenticationType);


    }
}
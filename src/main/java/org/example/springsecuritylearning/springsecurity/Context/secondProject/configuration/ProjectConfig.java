package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import org.example.springsecuritylearning.springsecurity.Context.secondProject.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService getUser() {
        UserDetails u = new User("john", "12345", "read");
       List<UserDetails> users=List.of(u);
       return new InMemoryUserDetailsService(users);


    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }
}

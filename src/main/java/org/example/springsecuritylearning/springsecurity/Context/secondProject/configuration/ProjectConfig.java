package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import org.example.springsecuritylearning.springsecurity.Context.secondProject.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

@Configuration
public class ProjectConfig {

   @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http
               .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
               .authorizeHttpRequests((c)->c.anyRequest().permitAll());
       return http.build();



        }
}

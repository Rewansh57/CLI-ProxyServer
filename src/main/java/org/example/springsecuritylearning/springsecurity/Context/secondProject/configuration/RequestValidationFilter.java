package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class RequestValidationFilter extends OncePerRequestFilter {

    @Value("${Authentication.Key}")
    private String authKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestKey = request.getHeader("Authorization");

        if (requestKey != null && requestKey.equals(authKey)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid API key");
        }
    }
}

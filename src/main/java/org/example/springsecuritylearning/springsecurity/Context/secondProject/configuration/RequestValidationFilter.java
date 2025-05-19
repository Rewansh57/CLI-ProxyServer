package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class RequestValidationFilter implements Filter {
    @Value("{Authentication.Key}")
    private String authKey;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestKey = httpRequest.getHeader("Authorization");

        if (requestKey != null && requestKey.equals(authKey)) {
            filterchain.doFilter(request, response);

        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }


    }
}

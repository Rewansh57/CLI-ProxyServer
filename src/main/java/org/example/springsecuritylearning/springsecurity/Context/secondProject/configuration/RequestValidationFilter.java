package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestValidationFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException{
        var httpRequest=(HttpServletRequest) request;
        var httpResponse=(HttpServletResponse) response;

        String requestId=httpRequest.getHeader("Request-Id");
        if (requestId==null || requestId.isBlank()){
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;

        }
        filterchain.doFilter(request,response);



    }

}

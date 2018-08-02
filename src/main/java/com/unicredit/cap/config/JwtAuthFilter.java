package com.unicredit.cap.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String authorization = servletRequest.getHeader("Authorization");
        String origin = servletRequest.getHeader("Origin");

        if (authorization != null)
        {
            JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
            SecurityContextHolder.getContext().setAuthentication(token);
            
        }
        
        
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }
    
   
}

package com.lz2zg.qsl.servlet;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserService userService = UserServiceFactory.getUserService();
        Principal user = req.getUserPrincipal();
        if (user != null) {
            if (user.getName().equals("rgerganov@gmail.com") || user.getName().equals("vlado@lz2zg.com")) {
                chain.doFilter(request, response);
            } else {
                resp.setContentType("text/html");
                resp.getWriter().println("<h1>Not authorized</h1>");
            }
        } else {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
    }

    @Override
    public void destroy() {
    }
}

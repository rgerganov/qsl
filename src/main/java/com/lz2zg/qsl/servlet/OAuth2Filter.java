package com.lz2zg.qsl.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OAuth2Filter implements Filter {

    static final Logger log = Logger.getLogger(OAuth2ClientImpl.class.getName());
    final static String GS_SCOPE = "https://www.googleapis.com/auth/devstorage.read_write";
    OAuth2Client oauth2client;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        oauth2client = (OAuth2Client) context.getAttribute("oauth2client");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getRequestURL().toString().equals(OAuth2Client.REDIRECT_URI)) {
            log.info("callback has been requested, pass forward");
            chain.doFilter(request, response);
            return;
        }
        String accessToken = oauth2client.getAccessToken();
        if (accessToken != null) {
            log.info("accessToken has been found");
            req.setAttribute("accessToken", accessToken);
            chain.doFilter(request, response);
        } else {
            String refreshToken = oauth2client.getRefreshToken();
            if (refreshToken != null) {
                log.info("getting accessToken using refreshToken");
                accessToken = oauth2client.getAccessToken(refreshToken);
                if (accessToken != null) {
                    req.setAttribute("accessToken", accessToken);
                    chain.doFilter(request, response);
                } else {
                    resp.setStatus(401);
                    resp.setContentType("text/html");
                    resp.getWriter().println("<html><body>Not Authorized</body></html>");
                }
            } else {
                log.info("refreshToken not found, redirect to get authCode");
                resp.sendRedirect(String.format("https://accounts.google.com/o/oauth2/auth?client_id=%s&redirect_uri=%s&scope=%s&response_type=code",
                        OAuth2Client.CLIENT_ID,
                        URLEncoder.encode(OAuth2Client.REDIRECT_URI, "UTF-8"),
                        URLEncoder.encode(GS_SCOPE, "UTF-8")));
            }
        }
    }

    @Override
    public void destroy() {
    }
}

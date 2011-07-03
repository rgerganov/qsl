package com.lz2zg.qsl.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuth2CallbackServlet extends HttpServlet {

    static final Logger log = Logger.getLogger(OAuth2CallbackServlet.class.getName());
    private static final long serialVersionUID = 1L;
    OAuth2Client oauth2client;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        oauth2client = (OAuth2Client) context.getAttribute("oauth2client");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authCode = req.getParameter("code");
        log.info("authCode: " + authCode);
        oauth2client.fetchAccessAndRefreshTokens(authCode);
        resp.setContentType("text/html");
        resp.getWriter().println("<html><body>Authorization in Google Storage successful</body></html>");
    }
}

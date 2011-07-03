package com.lz2zg.qsl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accessToken = (String) req.getAttribute("accessToken");
        resp.setContentType("text/html");
        resp.getWriter().println("Access token: " + accessToken);
    }

}

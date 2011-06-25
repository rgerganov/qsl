package com.lz2zg.qsl.servlet;

import com.lz2zg.qsl.model.QslCardDao;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    QslCardDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        dao = (QslCardDao) context.getAttribute("dao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        long id = Long.parseLong(idParam);
        dao.delete(id);
        resp.sendRedirect("/admin");
    }
}

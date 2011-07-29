package com.lz2zg.qsl.servlet;

import com.lz2zg.qsl.model.Page;
import com.lz2zg.qsl.model.QslCardDao;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 6;
    QslCardDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        dao = (QslCardDao) context.getAttribute("dao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = ServletUtils.parsePageNumber(req);
        String query = req.getParameter("q");
        Page page = dao.getPage(query, PAGE_SIZE, pageNumber);
        req.setAttribute("page", page);
        req.setAttribute("currentPage", pageNumber);
        req.getRequestDispatcher("/WEB-INF/showCards.jsp").forward(req, resp);
    }
}

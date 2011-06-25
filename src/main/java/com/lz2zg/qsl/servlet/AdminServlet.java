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

public class AdminServlet extends HttpServlet {

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
        String pageNumberParam = req.getParameter("p");
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(pageNumberParam);
            if (pageNumber <= 0) {
                pageNumber = 1;
            }
        } catch (NumberFormatException e) {
        }
        String query = req.getParameter("q");
        Page page = dao.getPage(query, pageNumber);
        req.setAttribute("page", page);
        req.setAttribute("currentPage", pageNumber);
        req.getRequestDispatcher("/WEB-INF/listCards.jsp").forward(req, resp);
    }

}

package com.lz2zg.qsl.servlet;

import com.lz2zg.qsl.model.QslCard;
import com.lz2zg.qsl.model.QslCardDao;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCardServlet extends HttpServlet {

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
        QslCard qslCard = dao.get(id);
        if (qslCard != null) {
            req.setAttribute("card", qslCard);
            req.getRequestDispatcher("/WEB-INF/editCard.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            QslCard qslCard = ServletUtils.parseCard(req);
            String idParam = req.getParameter("id");
            long id = Long.parseLong(idParam);
            qslCard.setId(id);
            dao.update(qslCard);
            resp.sendRedirect("/admin");
        } catch (NumberFormatException e) {
            // ignore
            resp.sendRedirect("/admin");
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/editCard.jsp").forward(req, resp);
        }
    }
}

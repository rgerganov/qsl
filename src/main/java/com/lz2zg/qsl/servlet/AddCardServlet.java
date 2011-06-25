package com.lz2zg.qsl.servlet;

import com.lz2zg.qsl.model.QslCard;
import com.lz2zg.qsl.model.QslCardDao;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCardServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/addCard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String callsign = req.getParameter("callsign");
        String frontImageUrl = req.getParameter("frontImageUrl");
        String backImageUrl = req.getParameter("backImageUrl");
        Date date = new Date(); // FIXME
        QslCard qslCard = new QslCard();
        qslCard.setCallsign(callsign);
        qslCard.setFrontImageUrl(frontImageUrl);
        qslCard.setBackImageUrl(backImageUrl);
        qslCard.setDate(date);
        dao.add(qslCard);
        resp.sendRedirect("/admin");
    }
}

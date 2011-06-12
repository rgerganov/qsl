package com.lz2zg.qsl.servlet;

import com.lz2zg.qsl.model.QslCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<QslCard> cards = new ArrayList<QslCard>();
        QslCard c1 = new QslCard();
        c1.setFrontImageUrl("imgs/3DA0WW_1.JPG");
        c1.setCallsign("3DA0WW");
        cards.add(c1);
        QslCard c2 = new QslCard();
        c2.setFrontImageUrl("imgs/5W0NU_1.JPG");
        c2.setCallsign("5W0NU");
        cards.add(c2);
        req.setAttribute("cards", cards);
        req.getRequestDispatcher("cards.jsp").forward(req, resp);
    }
}

package com.lz2zg.qsl.servlet;

import com.lz2zg.qsl.model.QslCard;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static QslCard parseCard(HttpServletRequest req) throws IllegalArgumentException {
        String callsign = req.getParameter("callsign");
        if (isEmpty(callsign)) {
            throw new IllegalArgumentException("Callsign is required");
        }
        String frontImageUrl = req.getParameter("frontImageUrl");
        if (isEmpty(frontImageUrl)) {
            throw new IllegalArgumentException("Front image URL is required");
        }
        if (!isUrlLink(frontImageUrl)) {
            throw new IllegalArgumentException("Front image is not an URL");
        }
        String backImageUrl = req.getParameter("backImageUrl");
        if (isEmpty(backImageUrl)) {
            throw new IllegalArgumentException("Back image URL is required");
        }
        if (!isUrlLink(backImageUrl)) {
            throw new IllegalArgumentException("Back image is not an URL");
        }
        QslCard qslCard = new QslCard();
        qslCard.setCallsign(callsign);
        qslCard.setFrontImageUrl(frontImageUrl);
        qslCard.setBackImageUrl(backImageUrl);
        return qslCard;
    }

    static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    static boolean isUrlLink(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }

}

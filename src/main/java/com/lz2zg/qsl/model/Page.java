package com.lz2zg.qsl.model;

import java.util.List;

public class Page {

    int startPage;
    int endPage;
    List<QslCard> cards;

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List<QslCard> getCards() {
        return cards;
    }

    public void setCards(List<QslCard> cards) {
        this.cards = cards;
    }
}

package com.lz2zg.qsl.model;

public interface QslCardDao {

    void add(QslCard qslCard);

    void delete(long id);

    QslCard get(long id);

    void update(QslCard qslCard);

    Page getPage(String query, int pageNumber);

}

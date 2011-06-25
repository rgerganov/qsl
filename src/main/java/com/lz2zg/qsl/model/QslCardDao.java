package com.lz2zg.qsl.model;

public interface QslCardDao {

    void add(QslCard qslCard);

    void delete(long id);

    Page getPage(String query, int pageNumber);

}

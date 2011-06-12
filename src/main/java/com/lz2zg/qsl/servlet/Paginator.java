package com.lz2zg.qsl.servlet;

public interface Paginator {

    Pagination getPagination(int totalItems, int page);
}

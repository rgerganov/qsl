package com.lz2zg.qsl.servlet;

public class PaginatorImpl implements Paginator {

    final int itemsPerPage;
    final int maxPages;

    public PaginatorImpl(int itemsPerPage, int maxPages) {
        if (itemsPerPage <= 0 || maxPages <= 0) {
            throw new IllegalArgumentException();
        }
        this.itemsPerPage = itemsPerPage;
        this.maxPages = maxPages;
    }

    @Override
    public Pagination getPagination(int totalItems, int page) {
        if (totalItems <= 0) {
            throw new IllegalArgumentException("totalItems must be > 0");
        }
        if (page <= 0) {
            throw new IllegalArgumentException("page must be > 0");
        }
        int totalPages = totalItems / itemsPerPage;
        if (totalItems % itemsPerPage != 0) {
            totalPages++;
        }
        int currentPage = page;
        if (page > totalPages) {
            currentPage = totalPages;
        }
        int pages = 1;
        int startPage = currentPage;
        int endPage = currentPage;
        while (pages < Math.min(maxPages, totalPages)) {
            if (startPage > 1) {
                startPage--;
                pages++;
            }
            if (endPage < totalPages) {
                endPage++;
                pages++;
            }
        }
        Pagination pagination = new Pagination();
        pagination.setTotalPages(totalPages);
        pagination.setCurrentPage(currentPage);
        pagination.setStartPage(startPage);
        pagination.setEndPage(endPage);
        return pagination;
    }
}

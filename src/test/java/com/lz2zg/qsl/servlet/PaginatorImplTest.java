package com.lz2zg.qsl.servlet;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PaginatorImplTest {

    @Test
    public void testGetPagination() {
        int itemsPerPage = 10;
        int maxPages = 5;

        PaginatorImpl paginator = new PaginatorImpl(itemsPerPage, maxPages);
        int totalItems = 94;
        int page = 3;
        Pagination pagination = paginator.getPagination(totalItems, page);
        Assert.assertEquals(1, pagination.getStartPage());
        Assert.assertEquals(5, pagination.getEndPage());
        Assert.assertEquals(3, pagination.getCurrentPage());
        Assert.assertEquals(10, pagination.getTotalPages());

        totalItems = 99;
        page = 8;
        pagination = paginator.getPagination(totalItems, page);
        Assert.assertEquals(6, pagination.getStartPage());
        Assert.assertEquals(10, pagination.getEndPage());
        Assert.assertEquals(8, pagination.getCurrentPage());
        Assert.assertEquals(10, pagination.getTotalPages());

        totalItems = 130;
        page = 9;
        pagination = paginator.getPagination(totalItems, page);
        Assert.assertEquals(7, pagination.getStartPage());
        Assert.assertEquals(11, pagination.getEndPage());
        Assert.assertEquals(9, pagination.getCurrentPage());
        Assert.assertEquals(13, pagination.getTotalPages());

        totalItems = 35;
        page = 2;
        pagination = paginator.getPagination(totalItems, page);
        Assert.assertEquals(1, pagination.getStartPage());
        Assert.assertEquals(4, pagination.getEndPage());
        Assert.assertEquals(2, pagination.getCurrentPage());
        Assert.assertEquals(4, pagination.getTotalPages());
    }

}

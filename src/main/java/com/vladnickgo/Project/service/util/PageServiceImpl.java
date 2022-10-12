package com.vladnickgo.Project.service.util;

public class PageServiceImpl implements PageService{
    @Override
    public Integer initNumberOfPage(String numberOfPage, Integer defaultNumberOfPage) {
        return numberOfPage==null?defaultNumberOfPage:Integer.valueOf(numberOfPage);
    }

    @Override
    public Integer initRecordsOnPage(String recordsOnPage, Integer defaultRecordsOnPage) {
        return recordsOnPage==null?defaultRecordsOnPage:Integer.valueOf(recordsOnPage);
    }

    @Override
    public Integer getTotalPages(Integer recordsOnPage, Integer totalRecords) {

        return totalRecords / recordsOnPage + (totalRecords % recordsOnPage > 0 ? 1 : 0);
    }

    @Override
    public Integer getFirstRecordOnPage(Integer totalPages, Integer numberOfPage, Integer recordsOnPage) {

        return recordsOnPage * ((Math.min(numberOfPage, totalPages)) - 1);
    }
}

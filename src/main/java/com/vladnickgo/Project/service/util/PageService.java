package com.vladnickgo.Project.service.util;

public interface PageService {
    Integer initNumberOfPage(String numberOfPage, Integer defaultNumberOfPage);

    Integer initRecordsOnPage(String recordsOnPage, Integer defaultRecordsOnPage);

    Integer getTotalPages(Integer recordsOnPage, Integer totalRecords);

    Integer getFirstRecordOnPage(Integer totalPages, Integer numberOfPage, Integer recordsOnPage);
}

package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.RouteRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteRequestDtoUtilTest {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;
    private static final Integer TEST_PAGE_NUMBER = 5;
    private static final Integer TEST_ITEMS_ON_PAGE = 10;

    @Mock
    RouteRequestDto routeRequestDto;
    @InjectMocks
    RouteRequestDtoUtil routeRequestDtoUtil;

    @Test
    void getNumberOfPageIfNull() {
        when(routeRequestDto.getNumberOfPage()).thenReturn(null);
        Integer actualNumberOfPage = routeRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actualNumberOfPage);
    }

    @Test
    void getNumberOfPageIfNotValidData() {
        when(routeRequestDto.getNumberOfPage()).thenReturn("asd");
        Integer actualNumberOfPage = routeRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actualNumberOfPage);
    }

    @Test
    void getNumberOfPageIfValidData() {
        when(routeRequestDto.getNumberOfPage()).thenReturn("5");
        Integer actualNumberOfPage = routeRequestDtoUtil.getNumberOfPage();
        assertEquals(TEST_PAGE_NUMBER, actualNumberOfPage);
    }

    @Test
    void getItemsOnPageIfNull() {
        when(routeRequestDto.getRecordsOnPage()).thenReturn(null);
        Integer actualItemsOnPage = routeRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actualItemsOnPage);
    }

    @Test
    void getItemsOnPageIfNotValidData() {
        when(routeRequestDto.getRecordsOnPage()).thenReturn("");
        Integer actualItemsOnPage = routeRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actualItemsOnPage);
    }

    @Test
    void getItemsOnPageIfValidData() {
        when(routeRequestDto.getRecordsOnPage()).thenReturn(TEST_ITEMS_ON_PAGE.toString());
        Integer actualItemsOnPage = routeRequestDtoUtil.getItemsOnPage();
        assertEquals(TEST_ITEMS_ON_PAGE, actualItemsOnPage);
    }

    @Test
    void getFirstRecordOnPage() {
        when(routeRequestDto.getNumberOfPage()).thenReturn("2");
        when(routeRequestDto.getRecordsOnPage()).thenReturn("15");
        Integer actual = routeRequestDtoUtil.getFirstRecordOnPage();
        Integer expected = 15;
        assertEquals(expected, actual);
    }
}
package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.PortRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortRequestDtoUtilTest {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 10;
    private static final String DEFAULT_SORTING_PARAMETER = "port_name_ua";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";

    @Mock
    PortRequestDto portRequestDto;
    @InjectMocks
    PortRequestDtoUtil portRequestDtoUtil;

    @Test
    void getSortingTestIfNull() {
        when(portRequestDto.getSorting()).thenReturn(null);
        String actual = portRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_PARAMETER, actual);
    }

    @Test
    void getSortingTestIfEmpty() {
        when(portRequestDto.getSorting()).thenReturn("");
        String actual = portRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_PARAMETER, actual);
    }

    @Test
    void getSortingTestIfValid() {
        when(portRequestDto.getSorting()).thenReturn("port_name_en");
        String actual = portRequestDtoUtil.getSorting();
        assertEquals("port_name_en", actual);
    }

    @Test
    void getSortingTestIfNotValid() {
        when(portRequestDto.getSorting()).thenReturn("qwerty");
        String actual = portRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfNull() {
        when(portRequestDto.getOrdering()).thenReturn(null);
        String actual = portRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfEmpty() {
        when(portRequestDto.getOrdering()).thenReturn("");
        String actual = portRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfNotValid() {
        when(portRequestDto.getOrdering()).thenReturn("UP");
        String actual = portRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfValid() {
        when(portRequestDto.getOrdering()).thenReturn("ASC");
        String actual = portRequestDtoUtil.getOrdering();
        assertEquals("ASC", actual);
    }

    @Test
    void getNumberOfPageIfNull() {
        when(portRequestDto.getNumberOfPage()).thenReturn(null);
        Integer actual = portRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actual);
    }

    @Test
    void getNumberOfPageIfNotValid() {
        when(portRequestDto.getNumberOfPage()).thenReturn("qwerty");
        Integer actual = portRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actual);
    }

    @Test
    void getNumberOfPageIfValid() {
        when(portRequestDto.getNumberOfPage()).thenReturn("5");
        Integer actual = portRequestDtoUtil.getNumberOfPage();
        Integer expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void getItemsOnPageIfNull() {
        when(portRequestDto.getRecordsOnPage()).thenReturn(null);
        Integer actual = portRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actual);
    }

    @Test
    void getItemsOnPageIfEmpty() {
        when(portRequestDto.getRecordsOnPage()).thenReturn("");
        Integer actual = portRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actual);
    }

    @Test
    void getItemsOnPageIfNotValid() {
        when(portRequestDto.getRecordsOnPage()).thenReturn("qwerty");
        Integer actual = portRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actual);
    }

    @Test
    void getItemsOnPageIfValid() {
        when(portRequestDto.getRecordsOnPage()).thenReturn("15");
        Integer actual = portRequestDtoUtil.getItemsOnPage();
        Integer expected = 15;
        assertEquals(expected, actual);
    }

    @Test
    void getFirstRecordOnPage() {
        when(portRequestDto.getNumberOfPage()).thenReturn("3");
        when(portRequestDto.getRecordsOnPage()).thenReturn("15");
        Integer actual = portRequestDtoUtil.getFirstRecordOnPage();
        Integer expected = 30;
        assertEquals(expected, actual);
    }

    @Test
    void extractedComparator() {
    }
}
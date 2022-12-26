package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.ShipRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipRequestDtoUtilTest {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;
    private static final String DEFAULT_SHIP_SORTING = "ship_name";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";

    @Mock
    ShipRequestDto shipRequestDto;
    @InjectMocks
    ShipRequestDtoUtil shipRequestDtoUtil;

    @Test
    void getSortingIfNull() {
        when(shipRequestDto.getSorting()).thenReturn(null);
        String actual = shipRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SHIP_SORTING, actual);
    }

    @Test
    void getSortingIfNotValid() {
        when(shipRequestDto.getSorting()).thenReturn("not_valid");
        String actual = shipRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SHIP_SORTING, actual);
    }

    @Test
    void getSortingIfExist() {
        when(shipRequestDto.getSorting()).thenReturn("passengers");
        String actual = shipRequestDtoUtil.getSorting();
        assertEquals("passengers", actual);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckGetOrderingMethod")
    void getOrdering(String ordering, String expectedOrdering, String message) {
        when(shipRequestDto.getOrdering()).thenReturn(ordering);
        String actual = shipRequestDtoUtil.getOrdering();
        assertEquals(expectedOrdering, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckGetNumberOfPageMethod")
    void getNumberOfPage(String numberOfPage, Integer expectedNumberOfPage, String message) {
        when(shipRequestDto.getNumberOfPage()).thenReturn(numberOfPage);
        Integer actual = shipRequestDtoUtil.getNumberOfPage();
        assertEquals(expectedNumberOfPage, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckGetItemsOnPageMethod")
    void getItemsOnPageCheckWithParameters(String itemsOnPage, Integer expectedItemsOnPage, String message) {
        when(shipRequestDto.getRecordsOnPage()).thenReturn(itemsOnPage);
        Integer actual = shipRequestDtoUtil.getItemsOnPage();
        assertEquals(expectedItemsOnPage, actual, message);
    }

    @Test
    void getFirstRecordOnPageTest() {
        when(shipRequestDto.getNumberOfPage()).thenReturn("3");
        when(shipRequestDto.getRecordsOnPage()).thenReturn("10");
        Integer actual = shipRequestDtoUtil.getFirstRecordOnPage();
        Integer expected = 20;
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideDataForCheckGetOrderingMethod() {
        return Stream.of(
                Arguments.of(null, DEFAULT_ORDERING_PARAMETER, "Check getOrdering method with null value"),
                Arguments.of("UP", DEFAULT_ORDERING_PARAMETER, "Check getOrdering method with not valid value"),
                Arguments.of("ASC", "ASC", "Check getOrdering method with ASC value"),
                Arguments.of("DESC", "DESC", "Check getOrdering method with DESC value")
        );
    }

    private static Stream<Arguments> provideDataForCheckGetNumberOfPageMethod() {
        return Stream.of(
                Arguments.of(null, DEFAULT_PAGE_NUMBER, "Check getNumberOfPage method with null value"),
                Arguments.of("", DEFAULT_PAGE_NUMBER, "Check getNumberOfPage method with empty value"),
                Arguments.of("asd", DEFAULT_PAGE_NUMBER, "Check getNumberOfPage method with not valid value"),
                Arguments.of("5", 5, "Check getNumberOfPage method with valid value")
        );
    }

    private static Stream<Arguments> provideDataForCheckGetItemsOnPageMethod() {
        return Stream.of(
                Arguments.of(null, DEFAULT_ITEMS_ON_PAGE, "Check getItemsOnPage method with null value"),
                Arguments.of("", DEFAULT_ITEMS_ON_PAGE, "Check getItemsOnPage method with empty value"),
                Arguments.of("asd", DEFAULT_ITEMS_ON_PAGE, "Check getItemsOnPage method with not valid value"),
                Arguments.of("10", 10, "Check getItemsOnPage method with valid value"));
    }

}
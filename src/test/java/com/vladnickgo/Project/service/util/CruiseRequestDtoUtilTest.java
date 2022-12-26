package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.CruiseDatesDto;
import com.vladnickgo.Project.controller.dto.CruiseDurationDto;
import com.vladnickgo.Project.controller.dto.CruiseRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CruiseRequestDtoUtilTest {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;
    private static final String DEFAULT_SORTING_PARAMETER = "cruise_name";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";
    private static final Integer TEST_MIN_DURATION_VALUE = 5;
    private static final Integer TEST_MAX_DURATION_VALUE = 25;
    private static final String TEST_BOTTOM_DURATION_VALUE = "10";
    private static final String TEST_TOP_DURATION_VALUE = "15";
    private static final CruiseDurationDto TEST_CRUISE_DURATION_DTO =
            CruiseDurationDto.newBuilder()
                    .minDurationValue(TEST_MIN_DURATION_VALUE)
                    .maxDurationValue(TEST_MAX_DURATION_VALUE)
                    .bottomDuration(TEST_BOTTOM_DURATION_VALUE)
                    .topDuration(TEST_TOP_DURATION_VALUE)
                    .build();
    private static final CruiseDurationDto TEST_CRUISE_DURATION_DTO_WITH_NULL_DURATION =
            CruiseDurationDto.newBuilder()
                    .minDurationValue(TEST_MIN_DURATION_VALUE)
                    .maxDurationValue(TEST_MAX_DURATION_VALUE)
                    .build();
    public static final LocalDate MIN_DATE_START = LocalDate.parse("2022-10-01");
    public static final LocalDate MAX_DATE_END = LocalDate.parse("2022-12-31");
    public static final String TEST_DATE_START = "2022-12-01";
    public static final String TEST_DATE_END = "2022-12-30";
    private static final CruiseDatesDto TEST_CRUISE_DATES_DTO =
            CruiseDatesDto.newBuilder()
                    .minDateStart(MIN_DATE_START)
                    .maxDateEnd(MAX_DATE_END)
                    .dateStart(TEST_DATE_START)
                    .dateEnd(TEST_DATE_END)
                    .build();
    private static final CruiseDatesDto TEST_CRUISE_DATES_DTO_WITH_NULL_DATE_START =
            CruiseDatesDto.newBuilder()
                    .minDateStart(MIN_DATE_START)
                    .maxDateEnd(MAX_DATE_END)
                    .dateEnd(TEST_DATE_END)
                    .build();
    private static final CruiseDatesDto TEST_CRUISE_DATES_DTO_WITH_NULL_DATE_END =
            CruiseDatesDto.newBuilder()
                    .minDateStart(MIN_DATE_START)
                    .maxDateEnd(MAX_DATE_END)
                    .dateStart(TEST_DATE_START)
                    .build();
    private static final CruiseRequestDto TEST_CRUISE_REQUEST_DTO =
            CruiseRequestDto.newBuilder()
                    .statusAvailable("available")
                    .statusInProgress("in progress")
                    .statusFinished("finished")
                    .statusNotAvailable("not available")
                    .build();
    @Mock
    CruiseRequestDto cruiseRequestDto;
    @InjectMocks
    CruiseRequestDtoUtil cruiseRequestDtoUtil;

    @Test
    void getSortingIfNull() {
        when(cruiseRequestDto.getSorting()).thenReturn(null);
        String actual = cruiseRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_PARAMETER, actual);
    }

    @Test
    void getSortingIfEmpty() {
        when(cruiseRequestDto.getSorting()).thenReturn("");
        String actual = cruiseRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_PARAMETER, actual);
    }

    @Test
    void getSortingIfNotValid() {
        when(cruiseRequestDto.getSorting()).thenReturn("abc");
        String actual = cruiseRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_PARAMETER, actual);
    }

    @Test
    void getSortingIfValid() {
        when(cruiseRequestDto.getSorting()).thenReturn("route_name");
        String actual = cruiseRequestDtoUtil.getSorting();
        String expected = "route_name";
        assertEquals(expected, actual);
    }

    @Test
    void getOrderingIfNull() {
        when(cruiseRequestDto.getOrdering()).thenReturn(null);
        String actual = cruiseRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfEmpty() {
        when(cruiseRequestDto.getOrdering()).thenReturn("");
        String actual = cruiseRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfNotValid() {
        when(cruiseRequestDto.getOrdering()).thenReturn("ABC");
        String actual = cruiseRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_PARAMETER, actual);
    }

    @Test
    void getOrderingIfValid() {
        when(cruiseRequestDto.getOrdering()).thenReturn("DESC");
        String actual = cruiseRequestDtoUtil.getOrdering();
        assertEquals("DESC", actual);
    }

    @Test
    void getNumberOfPageIfNull() {
        when(cruiseRequestDto.getNumberOfPage()).thenReturn(null);
        Integer actual = cruiseRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actual);
    }

    @Test
    void getNumberOfPageIfEmpty() {
        when(cruiseRequestDto.getNumberOfPage()).thenReturn("");
        Integer actual = cruiseRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actual);
    }

    @Test
    void getNumberOfPageIfNotValid() {
        when(cruiseRequestDto.getNumberOfPage()).thenReturn("abc");
        Integer actual = cruiseRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actual);
    }

    @Test
    void getNumberOfPageIfValid() {
        when(cruiseRequestDto.getNumberOfPage()).thenReturn("5");
        Integer actual = cruiseRequestDtoUtil.getNumberOfPage();
        Integer expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void getItemsOnPageIfNull() {
        when(cruiseRequestDto.getRecordsOnPage()).thenReturn(null);
        Integer actual = cruiseRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actual);
    }

    @Test
    void getItemsOnPageIfNotValid() {
        when(cruiseRequestDto.getRecordsOnPage()).thenReturn("abc");
        Integer actual = cruiseRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actual);
    }

    @Test
    void getItemsOnPageIfValid() {
        when(cruiseRequestDto.getRecordsOnPage()).thenReturn("10");
        Integer actual = cruiseRequestDtoUtil.getItemsOnPage();
        Integer expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    void getFirstRecordOnPageIfValid() {
        when(cruiseRequestDto.getRecordsOnPage()).thenReturn("10");
        when(cruiseRequestDto.getNumberOfPage()).thenReturn("5");
        Integer actual = cruiseRequestDtoUtil.getFirstRecordOnPage();
        Integer expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    void getStatusAvailableIfNull() {
        when(cruiseRequestDto.getSorting()).thenReturn(null);
        when(cruiseRequestDto.getStatusAvailable()).thenReturn(null);
        when(cruiseRequestDto.getStatusInProgress()).thenReturn(null);
        when(cruiseRequestDto.getStatusFinished()).thenReturn(null);
        when(cruiseRequestDto.getStatusNotAvailable()).thenReturn(null);
        String actual = cruiseRequestDtoUtil.getStatusAvailable();
        String expected = "available";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusAvailableIfSortingNotNull() {
        when(cruiseRequestDto.getSorting()).thenReturn("cruise_name");
        when(cruiseRequestDto.getStatusAvailable()).thenReturn(null);
        String actual = cruiseRequestDtoUtil.getStatusAvailable();
        assertNull(actual);
    }

    @Test
    void getStatusAvailableIfSortingAndStatusAvailableNotNull() {
        when(cruiseRequestDto.getSorting()).thenReturn("cruise_name");
        when(cruiseRequestDto.getStatusAvailable()).thenReturn("available");
        String actual = cruiseRequestDtoUtil.getStatusAvailable();
        String expected = "available";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusInProgressIfNull() {
        when(cruiseRequestDto.getStatusInProgress()).thenReturn(null);
        String actual = cruiseRequestDtoUtil.getStatusInProgress();
        assertNull(actual);
    }

    @Test
    void getStatusInProgressIfValid() {
        when(cruiseRequestDto.getStatusInProgress()).thenReturn("in progress");
        String actual = cruiseRequestDtoUtil.getStatusInProgress();
        String expected = "in progress";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusFinishedIfValid() {
        when(cruiseRequestDto.getStatusFinished()).thenReturn("finished");
        String actual = cruiseRequestDtoUtil.getStatusFinished();
        String expected = "finished";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusNotAvailableIfValid() {
        when(cruiseRequestDto.getStatusNotAvailable()).thenReturn("not available");
        String actual = cruiseRequestDtoUtil.getStatusNotAvailable();
        String expected = "not available";
        assertEquals(expected, actual);
    }

    @Test
    void getBottomCruiseDurationIfValid() {
        when(cruiseRequestDto.getCruiseDurationDto()).thenReturn(TEST_CRUISE_DURATION_DTO);
        Integer actual = cruiseRequestDtoUtil.getBottomCruiseDuration();
        Integer expected = Integer.valueOf(TEST_BOTTOM_DURATION_VALUE);
        assertEquals(expected, actual);
    }

    @Test
    void getBottomCruiseDurationIfNull() {
        when(cruiseRequestDto.getCruiseDurationDto()).thenReturn(TEST_CRUISE_DURATION_DTO_WITH_NULL_DURATION);
        Integer actual = cruiseRequestDtoUtil.getBottomCruiseDuration();
        assertEquals(TEST_MIN_DURATION_VALUE, actual);
    }

    @Test
    void getTopCruiseDurationIfValid() {
        when(cruiseRequestDto.getCruiseDurationDto()).thenReturn(TEST_CRUISE_DURATION_DTO);
        Integer actual = cruiseRequestDtoUtil.getTopCruiseDuration();
        Integer expected = Integer.valueOf(TEST_TOP_DURATION_VALUE);
        assertEquals(expected, actual);
    }

    @Test
    void getTopCruiseDurationIfNull() {
        when(cruiseRequestDto.getCruiseDurationDto()).thenReturn(TEST_CRUISE_DURATION_DTO_WITH_NULL_DURATION);
        Integer actual = cruiseRequestDtoUtil.getTopCruiseDuration();
        assertEquals(TEST_MAX_DURATION_VALUE, actual);
    }

    @Test
    void getDateStartIfValid() {
        when(cruiseRequestDto.getCruiseDatesDto()).thenReturn(TEST_CRUISE_DATES_DTO);
        LocalDate actual = cruiseRequestDtoUtil.getDateStart();
        LocalDate expected = LocalDate.parse(TEST_DATE_START);
        assertEquals(expected, actual);
    }

    @Test
    void getDateStartIfNotValid() {
        when(cruiseRequestDto.getCruiseDatesDto()).thenReturn(TEST_CRUISE_DATES_DTO_WITH_NULL_DATE_START);
        LocalDate actual = cruiseRequestDtoUtil.getDateStart();
        assertEquals(MIN_DATE_START, actual);
    }

    @Test
    void getDateEndIfValid() {
        when(cruiseRequestDto.getCruiseDatesDto()).thenReturn(TEST_CRUISE_DATES_DTO);
        LocalDate actual = cruiseRequestDtoUtil.getDateEnd();
        LocalDate expected = LocalDate.parse(TEST_DATE_END);
        assertEquals(expected, actual);
    }

    @Test
    void getDateEndIfNull() {
        when(cruiseRequestDto.getCruiseDatesDto()).thenReturn(TEST_CRUISE_DATES_DTO_WITH_NULL_DATE_END);
        LocalDate actual = cruiseRequestDtoUtil.getDateEnd();
        assertEquals(MAX_DATE_END, actual);
    }

    @Test
    void getMinCruseDurationTest() {
        when(cruiseRequestDto.getCruiseDurationDto()).thenReturn(TEST_CRUISE_DURATION_DTO);
        Integer actual = cruiseRequestDtoUtil.getMinCruiseDuration();
        assertEquals(TEST_MIN_DURATION_VALUE, actual);
    }

    @Test
    void getMaxCruiseDurationTest() {
        when(cruiseRequestDto.getCruiseDurationDto()).thenReturn(TEST_CRUISE_DURATION_DTO);
        Integer actual = cruiseRequestDtoUtil.getMaxCruiseDuration();
        assertEquals(TEST_MAX_DURATION_VALUE, actual);
    }

    @Test
    void getMinDateStartTest() {
        when(cruiseRequestDto.getCruiseDatesDto()).thenReturn(TEST_CRUISE_DATES_DTO);
        LocalDate actual = cruiseRequestDtoUtil.getMinDateStart();
        assertEquals(MIN_DATE_START, actual);
    }

    @Test
    void getMaxDateEndTest() {
        when(cruiseRequestDto.getCruiseDatesDto()).thenReturn(TEST_CRUISE_DATES_DTO);
        LocalDate actual = cruiseRequestDtoUtil.getMaxDateEnd();
        assertEquals(MAX_DATE_END, actual);
    }

    @Test
    void getCruiseStatusIdsIfAvailable() {
        when(cruiseRequestDto.getStatusAvailable()).thenReturn("available");
        Integer[] actual = cruiseRequestDtoUtil.getCruiseStatusIds();
        Integer[] expected = {1, 0, 0, 0};
        for (int i=0;i<4;i++) {
            assertEquals(expected[i],actual[i]);
        }
    }

    @Test
    void getCruiseStatusIdsIfNull() {
        Integer[] actual = cruiseRequestDtoUtil.getCruiseStatusIds();
        Integer[] expected = {1, 0, 0, 0};
        for (int i=0;i<4;i++) {
            assertEquals(expected[i],actual[i]);
        }
    }
}
package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentRequestDtoUtilTest {
    private static final String DEFAULT_SORTING_VALUE = "amount";
    private static final String DEFAULT_ORDERING_VALUE = "ASC";
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 5;
    private static final Integer STATUS_IN_PROGRESS_ID = 1;
    private static final Integer STATUS_CONFIRMED_ID = 2;
    private static final Integer STATUS_CANCELED_ID = 3;
    private static final Integer STATUS_COMPLETED_ID = 4;
    @Mock
    PaymentRequestDto paymentRequestDto;
    @InjectMocks
    PaymentRequestDtoUtil paymentRequestDtoUtil;

    @Test
    void getSortingIfNull() {
        when(paymentRequestDto.getSorting()).thenReturn(null);
        String actual = paymentRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_VALUE, actual);
    }

    @Test
    void getSortingIfNotValid() {
        when(paymentRequestDto.getSorting()).thenReturn("some sorting value");
        String actual = paymentRequestDtoUtil.getSorting();
        assertEquals(DEFAULT_SORTING_VALUE, actual);
    }

    @Test
    void getSortingIfValid() {
        when(paymentRequestDto.getSorting()).thenReturn("order_date");
        String actual = paymentRequestDtoUtil.getSorting();
        String expected = "order_date";
        assertEquals(expected, actual);
    }

    @Test
    void getOrderingIfNull() {
        when(paymentRequestDto.getOrdering()).thenReturn(null);
        String actual = paymentRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_VALUE, actual);
    }

    @Test
    void getOrderingIfNotValid() {
        when(paymentRequestDto.getOrdering()).thenReturn("not valid ordering");
        String actual = paymentRequestDtoUtil.getOrdering();
        assertEquals(DEFAULT_ORDERING_VALUE, actual);
    }

    @Test
    void getNumberOfPageIfNull() {
        when(paymentRequestDto.getNumberOfPage()).thenReturn(null);
        Integer actual = paymentRequestDtoUtil.getNumberOfPage();
        assertEquals(DEFAULT_PAGE_NUMBER, actual);
    }

    @Test
    void getNumberOfPageIfExistValue() {
        when(paymentRequestDto.getNumberOfPage()).thenReturn("5");
        Integer actual = paymentRequestDtoUtil.getNumberOfPage();
        Integer expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void getItemsOnPageIfNull() {
        when(paymentRequestDto.getItemsOnPage()).thenReturn(null);
        Integer actual = paymentRequestDtoUtil.getItemsOnPage();
        assertEquals(DEFAULT_ITEMS_ON_PAGE, actual);
    }

    @Test
    void getItemsOnPageIfExist() {
        when(paymentRequestDto.getItemsOnPage()).thenReturn("10");
        Integer actual = paymentRequestDtoUtil.getItemsOnPage();
        Integer expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    void getUserIdIfNull() {
        when(paymentRequestDto.getUserId()).thenReturn(null);
        Integer userId = paymentRequestDtoUtil.getUserId();
        Assertions.assertNull(userId);
    }

    @Test
    void getUserIdIfValid() {
        when(paymentRequestDto.getUserId()).thenReturn(5);
        Integer actual = paymentRequestDtoUtil.getUserId();
        Integer expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void getStatusInProgressIfNull() {
        String actual = paymentRequestDtoUtil.getStatusInProgress();
        String expected = "inProgress";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusInProgressIfExistStatusVales() {
        when(paymentRequestDto.getStatusConfirmed()).thenReturn("confirmed");
        String actual = paymentRequestDtoUtil.getStatusInProgress();
        assertNull(actual);
    }

    @Test
    void getStatusInProgressIfExistStatusInProgress() {
        when(paymentRequestDto.getStatusInProgress()).thenReturn("inProgress");
        String actual = paymentRequestDtoUtil.getStatusInProgress();
        String expected = "inProgress";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusConfirmedIfNull() {
        when(paymentRequestDto.getStatusConfirmed()).thenReturn(null);
        String actual = paymentRequestDtoUtil.getStatusConfirmed();
        assertNull(actual);
    }

    @Test
    void getStatusConfirmedIfStatusConfirmed() {
        when(paymentRequestDto.getStatusConfirmed()).thenReturn("confirmed");
        String actual = paymentRequestDtoUtil.getStatusConfirmed();
        String expected = "confirmed";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusCompletedIfNull() {
        when(paymentRequestDto.getStatusCompleted()).thenReturn(null);
        String actual = paymentRequestDtoUtil.getStatusCompleted();
        assertNull(actual);
    }

    @Test
    void getStatusCompletedIfStatusCompleted() {
        when(paymentRequestDto.getStatusCompleted()).thenReturn("completed");
        String actual = paymentRequestDtoUtil.getStatusCompleted();
        String expected = "completed";
        assertEquals(expected, actual);
    }

    @Test
    void getStatusCanceledIfNull() {
        when(paymentRequestDto.getStatusCanceled()).thenReturn(null);
        String actual = paymentRequestDtoUtil.getStatusCanceled();
        assertNull(actual);
    }

    @Test
    void getStatusCanceledIfStatusCanceled() {
        when(paymentRequestDto.getStatusCanceled()).thenReturn("canceled");
        String actual = paymentRequestDtoUtil.getStatusCanceled();
        String expected = "canceled";
        assertEquals(expected, actual);
    }

    @Test
    void getPaymentStatusIds() {
        when(paymentRequestDto.getStatusInProgress()).thenReturn("inProgress");
        when(paymentRequestDto.getStatusConfirmed()).thenReturn("confirmed");
        when(paymentRequestDto.getStatusCompleted()).thenReturn("completed");
        when(paymentRequestDto.getStatusCanceled()).thenReturn("canceled");
        Integer[] actual = paymentRequestDtoUtil.getPaymentStatusIds();
        Integer[] expected = {STATUS_IN_PROGRESS_ID, STATUS_CONFIRMED_ID, STATUS_COMPLETED_ID, STATUS_CANCELED_ID};
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
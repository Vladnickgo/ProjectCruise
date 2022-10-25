package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaymentRequestDtoUtil {
    private final PaymentRequestDto paymentRequestDto;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 5;
    private static final String DEFAULT_SORTING_PARAMETER = "amount";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";
    private static final Integer STATUS_IN_PROGRESS_ID = 1;
    private static final Integer STATUS_CONFIRMED_ID = 2;
    private static final Integer STATUS_CANCELED_ID = 3;
    private static final Integer STATUS_COMPLETED_ID = 4;

    public PaymentRequestDtoUtil(PaymentRequestDto paymentRequestDto) {
        this.paymentRequestDto = paymentRequestDto;
    }

    public String getSorting() {
        String sorting = paymentRequestDto.getSorting();
        return initSorting().getOrDefault(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = paymentRequestDto.getOrdering();
        return initOrdering().getOrDefault(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(paymentRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(paymentRequestDto.getItemsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    public Integer getUserId() {
        return paymentRequestDto.getUserId();
    }

    public String getStatusInProgress() {
        return Objects.equals(paymentRequestDto.getSorting(), null) &&
                Objects.equals(paymentRequestDto.getStatusInProgress(), null) &&
                Objects.equals(paymentRequestDto.getStatusConfirmed(), null) &&
                Objects.equals(paymentRequestDto.getStatusCompleted(), null) &&
                Objects.equals(paymentRequestDto.getStatusCanceled(), null)
                ? "inProgress" : paymentRequestDto.getStatusInProgress();
    }

    public String getStatusConfirmed() {
        return paymentRequestDto.getStatusConfirmed();
    }

    public String getStatusCompleted() {
        return paymentRequestDto.getStatusCompleted();
    }

    public String getStatusCanceled() {
        return paymentRequestDto.getStatusCanceled();
    }

    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return Objects.equals(null, parameter) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public Comparator<PaymentResponseDto> extractedComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<PaymentResponseDto>> initReverseComparatorMap() {
        Map<String, Comparator<PaymentResponseDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("amount", Comparator.comparing(PaymentResponseDto::getAmount, Comparator.reverseOrder()));
        reverseComparatorMap.put("order_date", Comparator.comparing(PaymentResponseDto::getPaymentDate, Comparator.reverseOrder()));
        reverseComparatorMap.put("cruise_name", Comparator.comparing(PaymentResponseDto::getPaymentDate, Comparator.reverseOrder()));
        reverseComparatorMap.put("date_start", Comparator.comparing(PaymentResponseDto::getDateStart, Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<PaymentResponseDto>> initNaturalComparatorMap() {
        Map<String, Comparator<PaymentResponseDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("amount", Comparator.comparing(PaymentResponseDto::getAmount, Comparator.naturalOrder()));
        naturalComparatorMap.put("order_date", Comparator.comparing(PaymentResponseDto::getPaymentDate, Comparator.naturalOrder()));
        naturalComparatorMap.put("cruise_name", Comparator.comparing(PaymentResponseDto::getPaymentDate, Comparator.naturalOrder()));
        naturalComparatorMap.put("date_start", Comparator.comparing(PaymentResponseDto::getDateStart, Comparator.naturalOrder()));
        return naturalComparatorMap;
    }

    public Integer[] getPaymentStatusIds() {
        Integer[] paymentStatusArray = new Integer[4];
        paymentStatusArray[0] = Objects.equals(getStatusInProgress(), "inProgress") ? STATUS_IN_PROGRESS_ID : 0;
        paymentStatusArray[1] = Objects.equals(getStatusConfirmed(), "confirmed") ? STATUS_CONFIRMED_ID : 0;
        paymentStatusArray[2] = Objects.equals(getStatusCompleted(), "completed") ? STATUS_COMPLETED_ID : 0;
        paymentStatusArray[3] = Objects.equals(getStatusCanceled(), "canceled") ? STATUS_CANCELED_ID : 0;
        return paymentStatusArray;
    }

    private static Map<String, String> initSorting() {
        Map<String, String> sortingMap = new HashMap<>();
        sortingMap.put("price", "price");
        sortingMap.put("order_date", "order_date");
        sortingMap.put("date_start", "date_start");
        sortingMap.put("cruise_name", "cruise_name");
        sortingMap.put("cabin_type", "cabin_type");
        return sortingMap;
    }

    private static Map<String, String> initOrdering() {
        Map<String, String> orderingMap = new HashMap<>();
        orderingMap.put("ASC", "ASC");
        orderingMap.put("DESC", "DESC");
        return orderingMap;
    }


}

package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.controller.dto.CruiseRequestDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CruiseRequestDtoUtil {
    private final CruiseRequestDto cruiseRequestDto;
    private static final Integer STATUS_AVAILABLE_ID = 1;
    private static final Integer STATUS_IN_PROGRESS = 2;
    private static final Integer STATUS_FINISHED_ID = 3;
    private static final Integer STATUS_NOT_AVAILABLE_ID = 4;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;
    private static final String DEFAULT_SORTING_PARAMETER = "cruise_name";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";

    public CruiseRequestDtoUtil(CruiseRequestDto cruiseRequestDto) {
        this.cruiseRequestDto = cruiseRequestDto;
    }

    public String getSorting() {
        String sorting = cruiseRequestDto.getSorting();
        return initSorting().getOrDefault(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = cruiseRequestDto.getOrdering();
        return initOrdering().getOrDefault(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(cruiseRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(cruiseRequestDto.getRecordsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return Objects.equals(null, parameter) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public Integer getFirstRecordOnPage() {
        return (getNumberOfPage() - 1) * getItemsOnPage();
    }

    private static Map<String, String> initSorting() {
        Map<String, String> sortingMap = new HashMap<>();
        sortingMap.put("cruise_name", "cruise_name");
        sortingMap.put("route_name", "route_name");
        sortingMap.put("date_start", "date_start");
        sortingMap.put("date_end", "date_end");
        sortingMap.put("nights", "nights");
        return sortingMap;
    }

    private static Map<String, String> initOrdering() {
        Map<String, String> orderingMap = new HashMap<>();
        orderingMap.put("ASC", "ASC");
        orderingMap.put("DESC", "DESC");
        return orderingMap;
    }

    public String getStatusAvailable() {
        return Objects.equals(cruiseRequestDto.getSorting(), null) &&
                Objects.equals(cruiseRequestDto.getStatusAvailable(), null) &&
                Objects.equals(cruiseRequestDto.getStatusInProgress(), null) &&
                Objects.equals(cruiseRequestDto.getStatusFinished(), null) &&
                Objects.equals(cruiseRequestDto.getStatusNotAvailable(), null)
                ? "available" : cruiseRequestDto.getStatusAvailable();
    }

    public String getStatusInProgress() {
        return cruiseRequestDto.getStatusInProgress();
    }

    public String getStatusFinished() {
        return cruiseRequestDto.getStatusFinished();
    }

    public String getStatusNotAvailable() {
        return cruiseRequestDto.getStatusNotAvailable();
    }

    public Integer getBottomCruiseDuration(){
        return Objects.equals(cruiseRequestDto.getCruiseDurationDto().getBottomDuration(),null)?
                cruiseRequestDto.getCruiseDurationDto().getMinDurationValue():
                Integer.valueOf(cruiseRequestDto.getCruiseDurationDto().getBottomDuration());
    }
    public Integer getTopCruiseDuration(){
        return Objects.equals(cruiseRequestDto.getCruiseDurationDto().getTopDuration(),null)?
                cruiseRequestDto.getCruiseDurationDto().getMaxDurationValue():
                Integer.valueOf(cruiseRequestDto.getCruiseDurationDto().getTopDuration());
    }

    public LocalDate getDateStart(){
        return Objects.equals(cruiseRequestDto.getCruiseDatesDto().getDateStart(),null)?
                cruiseRequestDto.getCruiseDatesDto().getMinDateStart():
                LocalDate.parse(cruiseRequestDto.getCruiseDatesDto().getDateStart());
    }

    public LocalDate getDateEnd(){
        return Objects.equals(cruiseRequestDto.getCruiseDatesDto().getDateEnd(),null)?
                cruiseRequestDto.getCruiseDatesDto().getMaxDateEnd():
                LocalDate.parse(cruiseRequestDto.getCruiseDatesDto().getDateEnd());
    }

    public Integer getMinCruiseDuration(){
        return cruiseRequestDto.getCruiseDurationDto().getMinDurationValue();
    }

    public Integer getMaxCruiseDuration(){
        return cruiseRequestDto.getCruiseDurationDto().getMaxDurationValue();
    }

    public LocalDate getMinDateStart(){
        return cruiseRequestDto.getCruiseDatesDto().getMinDateStart();
    }

    public LocalDate getMaxDateEnd(){
        return cruiseRequestDto.getCruiseDatesDto().getMaxDateEnd();
    }

    public Comparator<CruiseResponseDto> extractedComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<CruiseResponseDto>> initReverseComparatorMap() {
        Map<String, Comparator<CruiseResponseDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("cruise_name", Comparator.comparing(CruiseResponseDto::getCruiseName, Comparator.reverseOrder()));
        reverseComparatorMap.put("date_start", Comparator.comparing(CruiseResponseDto::getDateStart, Comparator.reverseOrder()));
        reverseComparatorMap.put("date_end", Comparator.comparing(CruiseResponseDto::getDateEnd, Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<CruiseResponseDto>> initNaturalComparatorMap() {
        Map<String, Comparator<CruiseResponseDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("cruise_name", Comparator.comparing(CruiseResponseDto::getCruiseName, Comparator.naturalOrder()));
        naturalComparatorMap.put("date_start", Comparator.comparing(CruiseResponseDto::getDateStart, Comparator.naturalOrder()));
        naturalComparatorMap.put("date_end", Comparator.comparing(CruiseResponseDto::getDateEnd, Comparator.naturalOrder()));
        return naturalComparatorMap;
    }

    public Integer[] getCruiseStatusIds() {
        Integer[] paymentStatusArray = new Integer[4];
        paymentStatusArray[0] = Objects.equals(getStatusAvailable(), "available") ? STATUS_AVAILABLE_ID : 0;
        paymentStatusArray[1] = Objects.equals(getStatusInProgress(), "in progress") ? STATUS_IN_PROGRESS : 0;
        paymentStatusArray[2] = Objects.equals(getStatusFinished(), "finished") ? STATUS_FINISHED_ID : 0;
        paymentStatusArray[3] = Objects.equals(getStatusNotAvailable(), "not available") ? STATUS_NOT_AVAILABLE_ID : 0;
        return paymentStatusArray;
    }

}

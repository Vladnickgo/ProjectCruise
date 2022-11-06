package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.controller.dto.PortRequestDto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PortRequestDtoUtil {
    private final PortRequestDto portRequestDto;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 10;
    private static final String DEFAULT_SORTING_PARAMETER = "port_name_ua";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";

    public PortRequestDtoUtil(PortRequestDto portRequestDto) {
        this.portRequestDto = portRequestDto;
    }

    public String getSorting() {
        String sorting = portRequestDto.getSorting();
        return initSorting().getOrDefault(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = portRequestDto.getOrdering();
        return initOrdering().getOrDefault(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(portRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(portRequestDto.getRecordsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    private static Map<String, String> initSorting() {
        Map<String, String> sortingMap = new HashMap<>();
        sortingMap.put("port_name_ua", "port_name_ua");
        sortingMap.put("port_name_en", "port_name_en");
        sortingMap.put("country_ua", "country_ua");
        sortingMap.put("country_en", "country_en");
        return sortingMap;
    }

    private static Map<String, String> initOrdering() {
        Map<String, String> orderingMap = new HashMap<>();
        orderingMap.put("ASC", "ASC");
        orderingMap.put("DESC", "DESC");
        return orderingMap;
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

    public Comparator<PortDto> extractedComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<PortDto>> initReverseComparatorMap() {
        Map<String, Comparator<PortDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("port_name_ua", Comparator.comparing(PortDto::getPortNameUa).reversed());
        reverseComparatorMap.put("port_name_en", Comparator.comparing(PortDto::getPortNameEn).reversed());
        reverseComparatorMap.put("country_ua", Comparator.comparing(PortDto::getCountryUa).reversed());
        reverseComparatorMap.put("country_en", Comparator.comparing(PortDto::getCountryEn).reversed());
        return reverseComparatorMap;
    }

    private Map<String, Comparator<PortDto>> initNaturalComparatorMap() {
        Map<String, Comparator<PortDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("port_name_ua", Comparator.comparing(PortDto::getPortNameUa));
        naturalComparatorMap.put("port_name_en", Comparator.comparing(PortDto::getPortNameEn));
        naturalComparatorMap.put("country_ua", Comparator.comparing(PortDto::getCountryUa));
        naturalComparatorMap.put("country_en", Comparator.comparing(PortDto::getCountryEn));
        return naturalComparatorMap;
    }
}

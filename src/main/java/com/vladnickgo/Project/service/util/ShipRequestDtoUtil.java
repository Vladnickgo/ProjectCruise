package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.controller.dto.ShipRequestDto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShipRequestDtoUtil {
    private final ShipRequestDto shipRequestDto;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_ITEMS_ON_PAGE = 4;
    private static final String DEFAULT_SORTING_PARAMETER = "ship_name";
    private static final String DEFAULT_ORDERING_PARAMETER = "ASC";

    public ShipRequestDtoUtil(ShipRequestDto shipRequestDto) {
        this.shipRequestDto = shipRequestDto;
    }

    public String getSorting() {
        String sorting = shipRequestDto.getSorting();
        return initSorting().getOrDefault(sorting, DEFAULT_SORTING_PARAMETER);
    }

    public String getOrdering() {
        String ordering = shipRequestDto.getOrdering();
        return initOrdering().getOrDefault(ordering, DEFAULT_ORDERING_PARAMETER);
    }

    public Integer getNumberOfPage() {
        return initParameterValue(shipRequestDto.getNumberOfPage(), DEFAULT_PAGE_NUMBER);
    }

    public Integer getItemsOnPage() {
        return initParameterValue(shipRequestDto.getRecordsOnPage(), DEFAULT_ITEMS_ON_PAGE);
    }

    public Integer getShipId() {
        return shipRequestDto.getShipId();
    }

    private Integer initParameterValue(String parameter, Integer defaultValue) {
        try {
            return (Objects.equals(null, parameter)||Objects.equals("", parameter)) ? defaultValue : Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public Integer getFirstRecordOnPage() {
        return (getNumberOfPage()-1) * getItemsOnPage();
    }

    private static Map<String, String> initSorting() {
        Map<String, String> sortingMap = new HashMap<>();
        sortingMap.put("ship_name", "ship_name");
        sortingMap.put("passengers", "passengers");
        sortingMap.put("number_of_staff", "number_of_staff");
        return sortingMap;
    }

    private static Map<String, String> initOrdering() {
        Map<String, String> orderingMap = new HashMap<>();
        orderingMap.put("ASC", "ASC");
        orderingMap.put("DESC", "DESC");
        return orderingMap;
    }


    public Comparator<ShipDto> getComparator() {
        String sort = getSorting();
        return getOrdering().equals("ASC") ? initNaturalComparatorMap().get(sort) : initReverseComparatorMap().get(sort);
    }

    private Map<String, Comparator<ShipDto>> initReverseComparatorMap() {
        Map<String, Comparator<ShipDto>> reverseComparatorMap = new HashMap<>();
        reverseComparatorMap.put("ship_name", Comparator.comparing(ShipDto::getShipName, Comparator.reverseOrder()));
        reverseComparatorMap.put("passengers", Comparator.comparing(ShipDto::getPassengersCapacity, Comparator.reverseOrder()));
        reverseComparatorMap.put("number_of_staff", Comparator.comparing(ShipDto::getNumberOfStaff, Comparator.reverseOrder()));
        return reverseComparatorMap;
    }

    private Map<String, Comparator<ShipDto>> initNaturalComparatorMap() {
        Map<String, Comparator<ShipDto>> naturalComparatorMap = new HashMap<>();
        naturalComparatorMap.put("ship_name", Comparator.comparing(ShipDto::getShipName, Comparator.naturalOrder()));
        naturalComparatorMap.put("passengers", Comparator.comparing(ShipDto::getPassengersCapacity, Comparator.naturalOrder()));
        naturalComparatorMap.put("number_of_staff", Comparator.comparing(ShipDto::getNumberOfStaff, Comparator.naturalOrder()));
        return naturalComparatorMap;
    }
}

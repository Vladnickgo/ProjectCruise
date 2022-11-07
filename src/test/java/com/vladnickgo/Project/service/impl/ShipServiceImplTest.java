package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.ShipDao;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShipServiceImplTest {
    @Mock
    private ShipDao shipRepository;

    @Mock
    private Mapper<ShipDto, Ship> mapper;

    @Mock
    private Validator<ShipDto> shipDtoValidator;

    @Mock
    private ShipDto shipDto;

    @Mock
    private ShipRequestDtoUtil shipRequestDtoUtil;

    @InjectMocks
    private ShipServiceImpl shipService;

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    void getTotalPages(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        Mockito.when(shipService.countAll()).thenReturn(totalRecords);
        Integer actualPages = shipService.getTotalPages(recordsOnPage);
        assertEquals(expectedPages, actualPages, message);
    }

    private static Stream<Arguments> provideNumberOfRecordsOnPageAndTotalRecords() {
        return Stream.of(
                Arguments.of(
                        5, 7, 2,"Check for 7 records, 5 records on page"
                ),
                Arguments.of(
                        5, 1, 1,"Check for 1 records, 5 records on page"
                ),
                Arguments.of(
                        5, 10, 2,"Check for 11 records, 10 records on page"
                ),
                Arguments.of(
                        15, 15, 1,"Check for 7 records, 5 records on page"
                )
                );

    }
}
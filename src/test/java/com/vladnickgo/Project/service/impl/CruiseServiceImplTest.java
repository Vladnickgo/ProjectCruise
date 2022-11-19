package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.service.mapper.CruiseMapper;
import com.vladnickgo.Project.service.mapper.CruiseResponseMapper;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;
import com.vladnickgo.Project.validator.CruiseValidator;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CruiseServiceImplTest {
    private static final Cruise TEST_CRUISE = Cruise.newBuilder()
            .id(1)
            .dateStart(LocalDate.parse("2022-12-01"))
            .dateEnd(LocalDate.parse("2022-12-11"))
            .nights(10)
            .cruiseName("Winter Route")
            .cruiseStatus(CruiseStatus.newBuilder()
                    .id(1)
                    .cruiseStatusName("available")
                    .build())
            .route(Route.newBuilder()
                    .id(1)
                    .routeName("european")
                    .build())
            .ship(Ship.newBuilder()
                    .id(1)
                    .shipName("Princess Ruby")
                    .passengersCapacity(2000)
                    .numberOfStaff(1000)
                    .shipImageSource("princess_ruby.jpg")
                    .build())
            .build();
    private static final CruiseResponseDto TEST_CRUISE_RESPONSE_DTO = CruiseResponseDto.newBuilder()
            .id(1)
            .dateStart(LocalDate.parse("2022-12-01"))
            .dateEnd(LocalDate.parse("2022-12-11"))
            .nights(10)
            .cruiseName("Winter Route")
            .cruiseStatusName("available")
            .routeName("european")
            .shipName("Princess Ruby")
            .shipImageSource("princess_ruby.jpg")
            .build();
    private static final CruiseDto TEST_CRUISE_DTO = CruiseDto.newBuilder()
            .id(1)
            .cruiseName("Winter Route")
            .dateStart(LocalDate.parse("2022-12-01"))
            .dateEnd(LocalDate.parse("2022-12-11"))
            .nights(10)
            .cruiseStatusId(1)
            .shipId(1)
            .routeID(1)
            .build();
    private static final List<CruiseResponseDto> TEST_CRUISE_RESPONSE_DTO_LIST = List.of(TEST_CRUISE_RESPONSE_DTO);
    private static final List<Cruise> TEST_CRUISE_LIST = List.of(TEST_CRUISE);
    private static final Integer TEST_CRUISE_RESPONSE_ID = 1;

    @Mock
    CruiseDao cruiseRepository;
    @Mock
    CruiseRequestDtoUtil cruiseRequestDtoUtil;
    @Spy
    CruiseResponseMapper cruiseResponseMapper;
    @Mock
    CruiseMapper cruiseMapper;
    @Spy
    CruiseValidator cruiseValidator;
    @InjectMocks
    CruiseServiceImpl cruiseService;

    @Test
    public void findAllIsSuccessful() {
        when(cruiseRepository.findAllByParameters(cruiseRequestDtoUtil)).thenReturn(TEST_CRUISE_LIST);
        when(cruiseResponseMapper.mapEntityToDto(TEST_CRUISE)).thenReturn(TEST_CRUISE_RESPONSE_DTO);
        when(cruiseRequestDtoUtil.extractedComparator()).thenReturn(Comparator.comparing(CruiseResponseDto::getCruiseName));
        List<CruiseResponseDto> actual = cruiseService.findAll(cruiseRequestDtoUtil);
        assertEquals(TEST_CRUISE_RESPONSE_DTO_LIST, actual);
    }

    @Test
    public void findCruiseByIdIsSuccessful() {
        when(cruiseRepository.findById(TEST_CRUISE_RESPONSE_ID)).thenReturn(Optional.of(TEST_CRUISE));
        when(cruiseResponseMapper.mapEntityToDto(TEST_CRUISE)).thenReturn(TEST_CRUISE_RESPONSE_DTO);
        CruiseResponseDto actual = cruiseService.findCruiseById(TEST_CRUISE_RESPONSE_ID);
        assertEquals(TEST_CRUISE_RESPONSE_DTO, actual);
    }

    @Test
    public void findCruiseByIdIsEmpty() {
        when(cruiseRepository.findById(TEST_CRUISE_RESPONSE_ID)).thenReturn(Optional.empty());
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> cruiseService.findCruiseById(TEST_CRUISE_RESPONSE_ID));
        String expectedMessage = "Cruise not found";
        Assertions.assertEquals(expectedMessage, actual.getMessage());
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckCreateCruiseMethod")
    public void createCruiseWithNOtValidCruiseDto(CruiseDto cruiseDto, String expectedMessage, String dataMessage) {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> cruiseService.createCruise(cruiseDto));
        Assertions.assertEquals(expectedMessage, actual.getMessage(), dataMessage);
    }



    private static Stream<Arguments> provideDataForCheckCreateCruiseMethod() {
        return Stream.of(
                Arguments.of(null, "Cruise is null", "Check with null cruiseDto"),
                Arguments.of(CruiseDto.newBuilder()
                                .id(1)
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-12-11"))
                                .nights(10)
                                .cruiseStatusId(1)
                                .shipId(1)
                                .routeID(1)
                                .build(),
                        "Empty cruise name",
                        "Check with null cruiseName"),
                Arguments.of(CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Winter Route")
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-12-11"))
                                .nights(10)
                                .cruiseStatusId(1)
                                .shipId(1)
                                .build(),
                        "Route id is null",
                        "Check with null route id"),
                Arguments.of(CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Winter Route")
                                .dateEnd(LocalDate.parse("2022-12-11"))
                                .nights(10)
                                .cruiseStatusId(1)
                                .routeID(1)
                                .shipId(1)
                                .build(),
                        "Empty date start",
                        "Check with null date start"),
                Arguments.of(CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Winter Route")
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .dateEnd(LocalDate.parse("2022-12-11"))
                                .nights(10)
                                .cruiseStatusId(1)
                                .routeID(1)
                                .build(),
                        "Ship id is empty",
                        "Check with null ship id"),
                Arguments.of(CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Winter Route")
                                .dateStart(LocalDate.parse("2022-12-01"))
                                .nights(10)
                                .shipId(1)
                                .cruiseStatusId(1)
                                .routeID(1)
                                .build(),
                        "Empty date end",
                        "Check with null date end"),
                Arguments.of(CruiseDto.newBuilder()
                                .id(1)
                                .cruiseName("Winter Route")
                                .dateStart(LocalDate.parse("2022-12-11"))
                                .dateEnd(LocalDate.parse("2022-12-01"))
                                .nights(10)
                                .shipId(1)
                                .cruiseStatusId(1)
                                .routeID(1)
                                .build(),
                        "Date start more or equal than date end",
                        "Check date start more or equal than date end")
        );
    }
}
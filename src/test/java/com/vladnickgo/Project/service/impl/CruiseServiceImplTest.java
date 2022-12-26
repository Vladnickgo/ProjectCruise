package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinTypeResponseDto;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.OrderDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.CruiseStatus;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.mapper.CruiseResponseMapper;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;
import com.vladnickgo.Project.validator.CruiseValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CruiseServiceImplTest {
    private static final Integer CRUISE_STATUS_AVAILABLE = 1;
    private static final Integer CRUISE_STATUS_NOT_AVAILABLE = 4;
    private static final Integer TEST_CRUISE_ID = 1;
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

    private static final Map<String, Integer> TEST_CABIN_TYPE_MAP = Map.of("single", 5, "inside", 3, "balcony", 7, "ocean view", 5);
    private static final Map<String, Integer> TEST_FREE_CABIN_TYPE_MAP = Map.of("single", 1, "inside", 2, "balcony", 1, "ocean view", 1);

    private static final List<CabinTypeResponseDto> TEST_CABIN_TYPE_RESPONSE_DTO_LIST = List.of(CabinTypeResponseDto.newBuilder()
                    .cabinTypeName("inside")
                    .numberOfCabins(3)
                    .numberOfBusyCabins(2)
                    .build(),
            CabinTypeResponseDto.newBuilder()
                    .cabinTypeName("balcony")
                    .numberOfCabins(7)
                    .numberOfBusyCabins(1)
                    .build(),
            CabinTypeResponseDto.newBuilder()
                    .cabinTypeName("ocean view")
                    .numberOfCabins(5)
                    .numberOfBusyCabins(1)
                    .build(),
            CabinTypeResponseDto.newBuilder()
                    .cabinTypeName("single")
                    .numberOfCabins(5)
                    .numberOfBusyCabins(1)
                    .build());
    private static final List<CruiseResponseDto> TEST_CRUISE_RESPONSE_DTO_LIST = List.of(TEST_CRUISE_RESPONSE_DTO);
    private static final List<Cruise> TEST_CRUISE_LIST = List.of(TEST_CRUISE);
    private static final Integer TEST_CRUISE_RESPONSE_ID = 1;

    @Mock
    CruiseDao cruiseRepository;
    @Mock
    OrderDao orderRepository;
    @Mock
    CruiseRequestDtoUtil cruiseRequestDtoUtil;
    @Spy
    CruiseResponseMapper cruiseResponseMapper;
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

    @Test
    public void blockCruiseByIdIsSuccessful() {
        cruiseService.blockCruiseById(TEST_CRUISE_DTO.getId());
        verify(cruiseRepository).updateCruiseStatusByCruiseId(TEST_CRUISE_DTO.getId(), CRUISE_STATUS_NOT_AVAILABLE);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    public void getTotalPagesTest(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        Mockito.when(cruiseRepository.countAll()).thenReturn(totalRecords);
        Integer actualPages = cruiseService.getNumberOfPages(recordsOnPage);
        assertEquals(expectedPages, actualPages, message);
    }

    @Test
    public void getMinCruiseDuration() {
        cruiseService.getMinCruiseDuration();
        verify(cruiseRepository).getMinCruiseDuration();
    }

    @Test
    public void getMinDateStartForStatusAvailable() {
        cruiseService.getMinDateStartForStatusAvailable();
        verify(cruiseRepository).getMinDateStart();
    }

    @Test
    public void getMaxDateEndForStatusAvailable() {
        cruiseService.getMaxDateEndForStatusAvailable();
        verify(cruiseRepository).getMaxDateEnd();
    }

    @Test
    public void unblockCruiseById() {
        cruiseService.unblockCruiseById(TEST_CRUISE_ID);
        verify(cruiseRepository).updateCruiseStatusByCruiseId(TEST_CRUISE_ID, CRUISE_STATUS_AVAILABLE);
    }

    @Test
    public void getMaxCruiseDurationIfCruiseNotFound() {
        when(cruiseRepository.getMaxCruiseDuration()).thenReturn(0);
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> cruiseService.getMaxCruiseDuration());
        String expectedMessage = "Cruise not found";
        assertEquals(expectedMessage, actual.getMessage());
    }

    @Test
    public void getNumberOfAllAndBusyCabinsIsSuccessful() {
        when(cruiseRepository.getEachCabinTypeNumberMap(TEST_CRUISE_ID)).thenReturn(TEST_CABIN_TYPE_MAP);
        when(orderRepository.getEachBusyCabinTypeNumberMap(TEST_CRUISE_ID)).thenReturn(TEST_FREE_CABIN_TYPE_MAP);
        List<CabinTypeResponseDto> actual = cruiseService.getNumberOfAllAndBusyCabins(TEST_CRUISE_ID.toString());
        Assertions.assertTrue(TEST_CABIN_TYPE_RESPONSE_DTO_LIST.containsAll(actual));
    }

    @Test
    public void findAllByDatesAndDuration() {
        when(cruiseRepository.findAllByDatesAndDuration(cruiseRequestDtoUtil)).thenReturn(TEST_CRUISE_LIST);
        when(cruiseResponseMapper.mapEntityToDto(TEST_CRUISE)).thenReturn(TEST_CRUISE_RESPONSE_DTO);
        List<CruiseResponseDto> actual = cruiseService.findAllByDatesAndDuration(cruiseRequestDtoUtil);
       assertEquals(TEST_CRUISE_RESPONSE_DTO_LIST,actual);
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

    private static Stream<Arguments> provideNumberOfRecordsOnPageAndTotalRecords() {
        return Stream.of(
                Arguments.of(
                        5, 7, 2, "Check for 7 records, 5 records on page"
                ),
                Arguments.of(
                        5, 1, 1, "Check for 1 records, 5 records on page"
                ),
                Arguments.of(
                        5, 10, 2, "Check for 11 records, 10 records on page"
                ),
                Arguments.of(
                        15, 15, 1, "Check for 7 records, 5 records on page"
                )
        );
    }
}
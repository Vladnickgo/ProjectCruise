package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.dao.ShipDao;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;
import com.vladnickgo.Project.validator.ShipValidator;
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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipServiceImplTest {
    public static final ShipDto TEST_SHIP_DTO = ShipDto.newBuilder()
            .id(1)
            .shipName("Silver Wave")
            .numberOfStaff(20)
            .passengersCapacity(25)
            .shipImage("silver_wave.jpg")
            .build();

    public static final Ship TEST_SHIP_ENTITY = Ship.newBuilder()
            .id(1)
            .shipName("Silver Wave")
            .numberOfStaff(20)
            .passengersCapacity(25)
            .shipImageSource("silver_wave.jpg")
            .build();

    public static final List<CabinRequestDto> CABIN_REQUEST_DTO_LIST = List.of(
            CabinRequestDto.newBuilder()
                    .cabinTypeId(1)
                    .numberOfCabins(5)
                    .build(),
            CabinRequestDto.newBuilder()
                    .cabinTypeId(2)
                    .numberOfCabins(5)
                    .build(),
            CabinRequestDto.newBuilder()
                    .cabinTypeId(3)
                    .numberOfCabins(5)
                    .build());
    public static final LocalDate TEST_DATE_START = LocalDate.parse("2022-11-10");
    public static final LocalDate TEST_DATE_END = LocalDate.parse("2022-11-01");
    @Mock
    private ShipDao shipRepository;

    @Spy
    private Mapper<ShipDto, Ship> mapper;

    @Spy
    private ShipValidator shipValidator;

    @Mock
    private ShipRequestDtoUtil shipRequestDtoUtil;

    @InjectMocks
    private ShipServiceImpl shipService;

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    public void getTotalPages(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        Mockito.when(shipService.countAll()).thenReturn(totalRecords);
        Integer actualPages = shipService.getTotalPages(recordsOnPage);
        assertEquals(expectedPages, actualPages, message);
    }

    @Test
    public void addShipIfShipAlreadyExist() {
        doNothing().when(shipValidator).validate(TEST_SHIP_DTO);
        when(mapper.mapDtoToEntity(TEST_SHIP_DTO)).thenReturn(TEST_SHIP_ENTITY);
        when(shipRepository.findByName(TEST_SHIP_DTO.getShipName())).thenReturn(Optional.of(TEST_SHIP_ENTITY));
        EntityAlreadyExistException exception = Assertions.assertThrows(EntityAlreadyExistException.class,
                () -> shipService.addShip(TEST_SHIP_DTO, CABIN_REQUEST_DTO_LIST));
        String EXPECTED_EXCEPTION_MESSAGE = "A ship with name SILVER WAVE already exists";
        assertEquals(EXPECTED_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void saveShipIsNull() {
        String expectedExceptionMessage = "Ship is null";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> shipService.addShip(null, CABIN_REQUEST_DTO_LIST));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveShipIsEmpty() {
        String expectedExceptionMessage = "Ship is empty";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> shipService.addShip(ShipDto.newBuilder().build(), CABIN_REQUEST_DTO_LIST));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveShipIsNullName() {
        String expectedExceptionMessage = "Ship name is empty";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> shipService.addShip(ShipDto.newBuilder()
                        .id(1)
                        .shipImage("ship_img.jpg")
                        .passengersCapacity(1000)
                        .numberOfStaff(200)
                        .build(), CABIN_REQUEST_DTO_LIST));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveShipIfNullNumberOfStaff() {
        String expectedExceptionMessage = "Not valid number of persons";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> shipService.addShip(ShipDto.newBuilder()
                        .id(1)
                        .shipName("Silver Wave")
                        .shipImage("ship_img.jpg")
                        .passengersCapacity(1000)
                        .build(), CABIN_REQUEST_DTO_LIST));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveShipIfNullPassengerCapacity() {
        String expectedExceptionMessage = "Not valid number of cabins";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> shipService.addShip(ShipDto.newBuilder()
                        .id(1)
                        .shipName("Silver Wave")
                        .shipImage("ship_img.jpg")
                        .numberOfStaff(1000)
                        .build(), CABIN_REQUEST_DTO_LIST));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveShipIfNullShipImage() {
        String expectedExceptionMessage = "Ship image is empty";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> shipService.addShip(ShipDto.newBuilder()
                        .id(1)
                        .shipName("Silver Wave")
                        .numberOfStaff(1000)
                        .passengersCapacity(2000)
                        .build(), CABIN_REQUEST_DTO_LIST));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void findAllFreeShipsByDateStartAndDateEndWithDateStartMoreThanDateEnd() {
        String expectedExceptionMessage = "Date start more or equal than date end";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> shipService.
                        findAllFreeShipsByDateStartAndDateEnd(TEST_DATE_START, TEST_DATE_END));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideNullDatesForFindAllFreeShipsByDateStartAndDateEndMethod")
    public void findAllFreeShipsByDateStartAndDateEndIfNullDate(LocalDate dateStart, LocalDate dateEnd, String expectedExceptionMessage, String message) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> shipService.
                        findAllFreeShipsByDateStartAndDateEnd(dateStart, dateEnd));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage(), message);
    }

    @Test
    public void addShipSuccessful() throws SQLException {
        when(mapper.mapDtoToEntity(any(ShipDto.class))).thenReturn(TEST_SHIP_ENTITY);
        when(shipRepository.findByName(TEST_SHIP_ENTITY.getShipName())).thenReturn(Optional.empty());
        shipService.addShip(TEST_SHIP_DTO, CABIN_REQUEST_DTO_LIST);
        verify(shipRepository).insertShipAndCabinsAndCabinStatuses(TEST_SHIP_ENTITY, CABIN_REQUEST_DTO_LIST);
    }

    @Test
    public void updateShipSuccessful() {
        when(mapper.mapDtoToEntity(any(ShipDto.class))).thenReturn(TEST_SHIP_ENTITY);
        shipService.updateShip(TEST_SHIP_DTO);
        verify(shipRepository).update(TEST_SHIP_ENTITY);
    }

    @Test
    public void deleteShipSuccessful() {
        shipService.deleteShipById(TEST_SHIP_ENTITY.getId());
        verify(shipRepository).deleteShipById(TEST_SHIP_ENTITY.getId());
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

    private static Stream<Arguments> provideNullDatesForFindAllFreeShipsByDateStartAndDateEndMethod() {
        return Stream.of(
                Arguments.of(null,
                        TEST_DATE_END,
                        "Date start is null",
                        "Check findAllFreeShipsByDate with null dateStart"),
                Arguments.of(TEST_DATE_START,
                        null,
                        "Date end is null",
                        "Check findAllFreeShipsByDate with null dateStart"));
    }
}
package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.controller.dto.CabinStatusRequestDto;
import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.CabinStatusDao;
import com.vladnickgo.Project.dao.entity.*;
import com.vladnickgo.Project.service.mapper.CabinStatusMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CabinStatusServiceImplTest {
    private static final Integer TEST_CRUISE_STATUS_ID = 1;
    private static final Integer TEST_CRUISE_ID = 1;
    private static final Integer TEST_CABIN_TYPE_ID = 1;
    private static final Integer TEST_CABIN_STATUS_ID = 1;
    private static final CabinStatus TEST_CABIN_STATUS = CabinStatus.newBuilder()
            .id(1)
            .statusStatement(CabinStatusStatement.newBuilder()
                    .id(1)
                    .statusStatementName("available")
                    .build())
            .cabin(Cabin.newBuilder()
                    .id(1)
                    .cabinType(CabinType.newBuilder()
                            .id(1)
                            .cabinTypeName("single")
                            .numberOfBeds(1)
                            .price(1000)
                            .build())
                    .ship(Ship.newBuilder()
                            .id(1)
                            .shipName("Silver Wave")
                            .shipImageSource("silver_wave.jpg")
                            .numberOfStaff(1000)
                            .passengersCapacity(2500)
                            .build())
                    .build())
            .statusStart(LocalDate.parse("2022-10-01"))
            .statusEnd(LocalDate.parse("2023-10-01"))
            .build();
    private static final CabinStatusDto TEST_CABIN_STATUS_DTO = CabinStatusDto.newBuilder()
            .id(1)
            .cabinId(1)
            .statusStatementName("available")
            .statusStart(LocalDate.parse("2022-10-01"))
            .statusEnd(LocalDate.parse("2023-10-01"))
            .build();
    private static final Map<CabinStatus, Integer> TEST_CABIN_STATUS_MAP = Map.of(
            CabinStatus.newBuilder()
                    .id(1)
                    .statusStart(LocalDate.parse("2022-12-01"))
                    .statusEnd(LocalDate.parse("2022-12-30"))
                    .cabin(Cabin.newBuilder()
                            .id(1)
                            .ship(Ship.newBuilder()
                                    .id(1)
                                    .passengersCapacity(100)
                                    .numberOfStaff(50)
                                    .shipName("Ship")
                                    .shipImageSource("ship.jpg")
                                    .build())
                            .cabinType(CabinType.newBuilder()
                                    .id(1)
                                    .price(1000)
                                    .numberOfBeds(1)
                                    .cabinTypeName("single")
                                    .build())
                            .build())
                    .build(), 5,
            CabinStatus.newBuilder()
                    .id(1)
                    .statusStart(LocalDate.parse("2022-12-02"))
                    .statusEnd(LocalDate.parse("2022-12-29"))
                    .cabin(Cabin.newBuilder()
                            .id(1)
                            .ship(Ship.newBuilder()
                                    .id(2)
                                    .passengersCapacity(100)
                                    .numberOfStaff(50)
                                    .shipName("Ship2")
                                    .shipImageSource("ship2.jpg")
                                    .build())
                            .cabinType(CabinType.newBuilder()
                                    .id(1)
                                    .price(1000)
                                    .numberOfBeds(1)
                                    .cabinTypeName("single")
                                    .build())
                            .build())
                    .build(), 5,
            CabinStatus.newBuilder()
                    .id(1)
                    .statusStart(LocalDate.parse("2022-12-03"))
                    .statusEnd(LocalDate.parse("2022-12-25"))
                    .cabin(Cabin.newBuilder()
                            .id(1)
                            .ship(Ship.newBuilder()
                                    .id(3)
                                    .passengersCapacity(100)
                                    .numberOfStaff(50)
                                    .shipName("Ship3")
                                    .shipImageSource("ship3.jpg")
                                    .build())
                            .cabinType(CabinType.newBuilder()
                                    .id(1)
                                    .price(1000)
                                    .numberOfBeds(1)
                                    .cabinTypeName("single")
                                    .build())
                            .build())
                    .build(), 5);
    private static final List<CabinStatusRequestDto> TEST_CABIN_STATUS_REQUEST_DTO_LIST = List.of(
            CabinStatusRequestDto.newBuilder()
                    .cabinStatusId(1)
                    .cabinId(1)
                    .cabinType(CabinTypeDto.newBuilder()
                            .id(1)
                            .cabinTypeName("single")
                            .numberOfBeds(1)
                            .price(1000)
                            .build())
                    .numberOfRoom(5)
                    .build(),
            CabinStatusRequestDto.newBuilder()
                    .cabinStatusId(1)
                    .cabinId(1)
                    .cabinType(CabinTypeDto.newBuilder()
                            .id(1)
                            .cabinTypeName("single")
                            .numberOfBeds(1)
                            .price(1000)
                            .build())
                    .numberOfRoom(5)
                    .build(),
            CabinStatusRequestDto.newBuilder()
                    .cabinStatusId(1)
                    .cabinId(1)
                    .cabinType(CabinTypeDto.newBuilder()
                            .id(1)
                            .cabinTypeName("single")
                            .numberOfBeds(1)
                            .price(1000)
                            .build())
                    .numberOfRoom(5)
                    .build());


    @Mock
    CabinStatusDao cabinStatusRepository;
    @Spy
    CabinStatusMapper cabinStatusMapper;
    @InjectMocks
    CabinStatusServiceImpl cabinStatusService;

    @Test
    void findCabinStatusByParametersIfNotFound() {
        when(cabinStatusRepository.findCabinStatusByParameters(TEST_CRUISE_ID, TEST_CABIN_TYPE_ID)).thenReturn(null);
        String expectedMessage = "CabinStatus not found";
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> cabinStatusService.findCabinStatusByParameters(TEST_CRUISE_ID, TEST_CABIN_TYPE_ID));
        Assertions.assertEquals(expectedMessage, actual.getMessage());
    }

    @Test
    void findCabinStatusByParametersIfSuccessful() {
        when(cabinStatusRepository.findCabinStatusByParameters(TEST_CRUISE_ID, TEST_CABIN_TYPE_ID)).thenReturn(TEST_CABIN_STATUS);
        when(cabinStatusMapper.mapEntityToDto(TEST_CABIN_STATUS)).thenReturn(TEST_CABIN_STATUS_DTO);
        CabinStatusDto actual = cabinStatusService.findCabinStatusByParameters(TEST_CRUISE_ID, TEST_CABIN_TYPE_ID);
        assertEquals(TEST_CABIN_STATUS_DTO, actual);
    }

    @Test
    void getCabinStatusListByParameterIfSuccessful() {
        when(cabinStatusRepository.getCabinStatusListByParameter(TEST_CRUISE_STATUS_ID)).thenReturn(TEST_CABIN_STATUS_MAP);
        List<CabinStatusRequestDto> actual = cabinStatusService.getCabinStatusListByParameter(TEST_CRUISE_STATUS_ID);
        assertEquals(TEST_CABIN_STATUS_REQUEST_DTO_LIST, actual);
    }

    @Test
    void findCabinStatusIdByParametersIfNull() {
        when(cabinStatusRepository.findCabinStatusIdByParameters(TEST_CRUISE_STATUS_ID, TEST_CABIN_STATUS_ID)).thenReturn(null);
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> cabinStatusService.findCabinStatusIdByParameters(TEST_CRUISE_STATUS_ID, TEST_CABIN_STATUS_ID));
        String expectedMessage = "CabinStatus not found";
        Assertions.assertEquals(expectedMessage, actual.getMessage());
    }

}
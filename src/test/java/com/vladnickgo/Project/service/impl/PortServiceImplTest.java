package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.PortDao;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.PortMapper;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;
import com.vladnickgo.Project.validator.PortValidator;
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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortServiceImplTest {
    private static final Port TEST_PORT_ENTITY = Port.newBuilder()
            .id(1)
            .portNameUa("Барі")
            .portNameEn("Bari")
            .countryUa("Італія")
            .countryEn("Italy")
            .build();
    private static final PortDto TEST_PORT_DTO = PortDto.newBuilder()
            .id(1)
            .portNameUa("Барі")
            .portNameEn("Bari")
            .countryUa("Італія")
            .countryEn("Italy")
            .build();
    private static final List<Port> TEST_PORT_ENTITY_LIST = List.of(TEST_PORT_ENTITY);
    private static final List<PortDto> TEST_PORT_DTO_LIST = List.of(TEST_PORT_DTO);
    @Mock
    PortDao portRepository;

    @Mock
    PortRequestDtoUtil portRequestDtoUtil;

    @Spy
    PortValidator portValidator;

    @Spy
    PortMapper portMapper;

    @InjectMocks
    private PortServiceImpl portService;

    @Test
    public void findAllIsSuccess() {
        when(portRepository.findAll()).thenReturn(TEST_PORT_ENTITY_LIST);
        when(portMapper.mapEntityToDto(TEST_PORT_ENTITY)).thenReturn(TEST_PORT_DTO);
        List<PortDto> actualPortList = portService.findAll();
        assertEquals(TEST_PORT_DTO_LIST, actualPortList);
    }

    @Test
    public void findAllByRouteIdCheckMethod() {
        when(portRepository.findAllByPageAndSorting(portRequestDtoUtil)).thenReturn(TEST_PORT_ENTITY_LIST);
        when(portMapper.mapEntityToDto(TEST_PORT_ENTITY)).thenReturn(TEST_PORT_DTO);
        when(portRequestDtoUtil.extractedComparator()).thenReturn(Comparator.comparing(PortDto::getId));
        List<PortDto> actualPortList = portService.findAllByPageAndSorting(portRequestDtoUtil);
        assertEquals(TEST_PORT_DTO_LIST, actualPortList);
    }

    @Test
    public void addPortIfPortNameUaAlreadyExist() {
        doNothing().when(portValidator).validate(TEST_PORT_DTO);
        when(portMapper.mapDtoToEntity(TEST_PORT_DTO)).thenReturn(TEST_PORT_ENTITY);
        when(portRepository.findByNameUa(TEST_PORT_DTO.getPortNameUa())).thenReturn(Optional.of(TEST_PORT_ENTITY));
        EntityAlreadyExistException exception = Assertions.assertThrows(EntityAlreadyExistException.class, () -> portService.addPort(TEST_PORT_DTO));
        String EXPECTED_EXCEPTION_MESSAGE = "A port with name БАРІ already exist";
        assertEquals(EXPECTED_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void addPortIfPortNameEnAlreadyExist() {
        doNothing().when(portValidator).validate(TEST_PORT_DTO);
        when(portMapper.mapDtoToEntity(TEST_PORT_DTO)).thenReturn(TEST_PORT_ENTITY);
        when(portRepository.findByNameEn(TEST_PORT_DTO.getPortNameEn())).thenReturn(Optional.of(TEST_PORT_ENTITY));
        EntityAlreadyExistException exception = Assertions.assertThrows(EntityAlreadyExistException.class, () -> portService.addPort(TEST_PORT_DTO));
        String EXPECTED_EXCEPTION_MESSAGE = "A port with name BARI already exist";
        assertEquals(EXPECTED_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void addPortIfPortIsNull() {
        String EXPECTED_MESSAGE = "Port is null";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> portService.addPort(null));
        assertEquals(EXPECTED_MESSAGE, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("providePortDtoCheckMethod")
    public void addPortIfPortDtoHasNullData(PortDto portDto, String expectedMessage, String message) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> portService.addPort(portDto));
        assertEquals(expectedMessage, exception.getMessage(), message);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    public void getTotalPagesTest(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        when(portRepository.countAll()).thenReturn(totalRecords);
        when(portRequestDtoUtil.getItemsOnPage()).thenReturn(recordsOnPage);
        Integer actualPages = portService.getNumberOfPages(portRequestDtoUtil);
        assertEquals(expectedPages, actualPages, message);
    }

    @Test
    public void deletePortSuccessful() {
        portService.deletePortById(TEST_PORT_ENTITY.getId());
        verify(portRepository).deletePortById(TEST_PORT_ENTITY.getId());
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
                        1, 0, 0, "Check for 0 records, 0 records on page"
                ),
                Arguments.of(
                        1, 1, 1, "Check for 1 records, 1 records on page"
                ));
    }

    private static Stream<Arguments> providePortDtoCheckMethod() {
        return Stream.of(
                Arguments.of(
                        PortDto.newBuilder()
                                .id(1)
                                .portNameEn("Bari")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .build(),
                        "Port name for ua language is null",
                        "Check addPort method with null portNameUa"),
                Arguments.of(
                        PortDto.newBuilder()
                                .id(1)
                                .portNameUa("Барі")
                                .countryUa("Італія")
                                .countryEn("Italy")
                                .build(),
                        "Port name for en language is null",
                        "Check addPort method with null portNameEn"),
                Arguments.of(
                        PortDto.newBuilder()
                                .id(1)
                                .portNameUa("Барі")
                                .portNameEn("Bari")
                                .countryEn("Italy")
                                .build(),
                        "Country name for ua language is null",
                        "Check addPort method with null countryNameUa"),
                Arguments.of(
                        PortDto.newBuilder()
                                .id(1)
                                .portNameUa("Барі")
                                .portNameEn("Bari")
                                .countryUa("Італія")
                                .build(),
                        "Country name for en language is null",
                        "Check addPort method with null countryNameEn"),
                Arguments.of(
                        PortDto.newBuilder()
                                .build(),
                        "Port is empty",
                        "Check addPort method with empty port data")
                );
    }

}
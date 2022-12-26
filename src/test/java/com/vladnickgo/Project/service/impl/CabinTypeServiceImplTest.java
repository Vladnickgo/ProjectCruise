package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.CabinTypeDao;
import com.vladnickgo.Project.dao.entity.CabinType;
import com.vladnickgo.Project.service.mapper.CabinTypeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CabinTypeServiceImplTest {
    private static final CabinType TEST_CABIN_TYPE_SINGLE = CabinType.newBuilder()
            .id(1)
            .cabinTypeName("single")
            .price(1000)
            .numberOfBeds(1)
            .build();
    private static final CabinType TEST_CABIN_TYPE_INSIDE = CabinType.newBuilder()
            .id(2)
            .cabinTypeName("inside")
            .price(1200)
            .numberOfBeds(2)
            .build();
    private static final CabinType TEST_CABIN_TYPE_BALCONY = CabinType.newBuilder()
            .id(3)
            .cabinTypeName("balcony")
            .price(1500)
            .numberOfBeds(2)
            .build();
    private static final CabinType TEST_CABIN_TYPE_OCEAN_VIEW = CabinType.newBuilder()
            .id(4)
            .cabinTypeName("ocean view")
            .price(1800)
            .numberOfBeds(2)
            .build();
    private static final CabinType TEST_CABIN_TYPE_SUITE = CabinType.newBuilder()
            .id(5)
            .cabinTypeName("suite")
            .price(2500)
            .numberOfBeds(2)
            .build();
    private static final CabinTypeDto TEST_CABIN_TYPE_DTO_SINGLE = CabinTypeDto.newBuilder()
            .id(1)
            .cabinTypeName("single")
            .price(1000)
            .numberOfBeds(1)
            .build();
    private static final CabinTypeDto TEST_CABIN_TYPE_DTO_INSIDE = CabinTypeDto.newBuilder()
            .id(2)
            .cabinTypeName("inside")
            .price(1200)
            .numberOfBeds(2)
            .build();
    private static final CabinTypeDto TEST_CABIN_TYPE_DTO_BALCONY = CabinTypeDto.newBuilder()
            .id(3)
            .cabinTypeName("balcony")
            .price(1500)
            .numberOfBeds(2)
            .build();
    private static final CabinTypeDto TEST_CABIN_TYPE_DTO_OCEAN_VIEW = CabinTypeDto.newBuilder()
            .id(4)
            .cabinTypeName("ocean view")
            .price(1800)
            .numberOfBeds(2)
            .build();
    private static final CabinTypeDto TEST_CABIN_TYPE_DTO_SUITE = CabinTypeDto.newBuilder()
            .id(5)
            .cabinTypeName("suite")
            .price(2500)
            .numberOfBeds(2)
            .build();
    private static final List<CabinType> TEST_CABIN_TYPE_LIST = new ArrayList<>(
            List.of(
                    TEST_CABIN_TYPE_SINGLE,
                    TEST_CABIN_TYPE_INSIDE,
                    TEST_CABIN_TYPE_OCEAN_VIEW,
                    TEST_CABIN_TYPE_BALCONY,
                    TEST_CABIN_TYPE_SUITE));
    private static final List<CabinTypeDto> TEST_CABIN_TYPE_DTO_LIST = new ArrayList<>(
            List.of(
                    TEST_CABIN_TYPE_DTO_SINGLE,
                    TEST_CABIN_TYPE_DTO_INSIDE,
                    TEST_CABIN_TYPE_DTO_OCEAN_VIEW,
                    TEST_CABIN_TYPE_DTO_BALCONY,
                    TEST_CABIN_TYPE_DTO_SUITE));

    @Mock
    CabinTypeDao cabinTypeRepository;
    @Spy
    CabinTypeMapper cabinTypeMapper;
    @InjectMocks
    CabinTypeServiceImpl cabinTypeService;

    @Test
    void findAll() {
        when(cabinTypeRepository.findAll()).thenReturn(TEST_CABIN_TYPE_LIST);
        when(cabinTypeMapper.mapEntityToDto(TEST_CABIN_TYPE_SINGLE)).thenReturn(TEST_CABIN_TYPE_DTO_SINGLE);
        when(cabinTypeMapper.mapEntityToDto(TEST_CABIN_TYPE_INSIDE)).thenReturn(TEST_CABIN_TYPE_DTO_INSIDE);
        when(cabinTypeMapper.mapEntityToDto(TEST_CABIN_TYPE_OCEAN_VIEW)).thenReturn(TEST_CABIN_TYPE_DTO_OCEAN_VIEW);
        when(cabinTypeMapper.mapEntityToDto(TEST_CABIN_TYPE_BALCONY)).thenReturn(TEST_CABIN_TYPE_DTO_BALCONY);
        when(cabinTypeMapper.mapEntityToDto(TEST_CABIN_TYPE_SUITE)).thenReturn(TEST_CABIN_TYPE_DTO_SUITE);
        List<CabinTypeDto> actual = cabinTypeService.findAll();
        actual.sort(Comparator.comparingInt(CabinTypeDto::getId));
        TEST_CABIN_TYPE_DTO_LIST.sort(Comparator.comparingInt(CabinTypeDto::getId));
        Assertions.assertEquals(TEST_CABIN_TYPE_DTO_LIST,actual);


    }
}
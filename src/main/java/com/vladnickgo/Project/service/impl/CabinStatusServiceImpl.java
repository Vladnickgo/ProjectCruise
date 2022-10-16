package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.controller.dto.CabinStatusRequestDto;
import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.CabinStatusDao;
import com.vladnickgo.Project.dao.entity.CabinStatus;
import com.vladnickgo.Project.service.CabinStatusService;
import com.vladnickgo.Project.service.mapper.Mapper;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CabinStatusServiceImpl implements CabinStatusService {
    private final CabinStatusDao cabinStatusRepository;
    private final Mapper<CabinStatusDto, CabinStatus> mapper;
    private static final Logger LOGGER = Logger.getLogger(CabinStatusServiceImpl.class);

    public CabinStatusServiceImpl(CabinStatusDao cabinStatusRepository, Mapper<CabinStatusDto, CabinStatus> mapper) {
        this.cabinStatusRepository = cabinStatusRepository;
        this.mapper = mapper;
    }

    @Override
    public CabinStatusDto findCabinStatusByParameters(Integer cruiseId, Integer cabinTypeId) {
        CabinStatus cabinStatus = Optional.ofNullable(cabinStatusRepository.findCabinStatusByParameters(cruiseId, cabinTypeId))
                .orElseThrow(() -> new IllegalArgumentException("CabinStatus not found"));
        return mapper.mapEntityToDto(cabinStatus);
    }

    @Override
    public List<CabinStatusRequestDto> getCabinStatusListByParameter(Integer cruiseStatusId) {
        List<CabinStatusRequestDto> cabinStatusRequestDtoList = new ArrayList<>();
        cabinStatusRepository.getCabinStatusListByParameter(cruiseStatusId).entrySet()
                .stream().map(t -> cabinStatusRequestDtoList.add(CabinStatusRequestDto.newBuilder()
                        .cabinStatusId(t.getKey().getId())
                        .cabinId(t.getKey().getCabin().getId())
                        .cabinType(CabinTypeDto.newBuilder()
                                .id(t.getKey().getCabin().getCabinType().getId())
                                .cabinTypeName(t.getKey().getCabin().getCabinType().getCabinTypeName())
                                .numberOfBeds(t.getKey().getCabin().getCabinType().getNumberOfBeds())
                                .price(t.getKey().getCabin().getCabinType().getPrice())
                                .build())
                        .numberOfRoom(t.getValue())
                        .build())).collect(Collectors.toList());
        return cabinStatusRequestDtoList;
    }

    @Override
    public Integer findCabinStatusIdByParameters(Integer cruiseStatusId, Integer cabinStatusId) {
        return Optional.ofNullable(cabinStatusRepository.findCabinStatusIdByParameters(cruiseStatusId, cabinStatusId))
                .orElseThrow(() -> new IllegalArgumentException("CabinStatus not found"));
    }
}


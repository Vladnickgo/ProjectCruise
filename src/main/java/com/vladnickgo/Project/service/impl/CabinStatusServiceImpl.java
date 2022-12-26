package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinStatusDto;
import com.vladnickgo.Project.controller.dto.CabinStatusRequestDto;
import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.CabinStatusDao;
import com.vladnickgo.Project.dao.entity.CabinStatus;
import com.vladnickgo.Project.service.CabinStatusService;
import com.vladnickgo.Project.service.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CabinStatusServiceImpl implements CabinStatusService {
    private final CabinStatusDao cabinStatusRepository;
    private final Mapper<CabinStatusDto, CabinStatus> mapper;

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
        cabinStatusRepository.getCabinStatusListByParameter(cruiseStatusId)
                .forEach((key, value) -> cabinStatusRequestDtoList.add(CabinStatusRequestDto.newBuilder()
                .cabinStatusId(key.getId())
                .cabinId(key.getCabin().getId())
                .cabinType(CabinTypeDto.newBuilder()
                        .id(key.getCabin().getCabinType().getId())
                        .cabinTypeName(key.getCabin().getCabinType().getCabinTypeName())
                        .numberOfBeds(key.getCabin().getCabinType().getNumberOfBeds())
                        .price(key.getCabin().getCabinType().getPrice())
                        .build())
                .numberOfRoom(value)
                .build()));
        return cabinStatusRequestDtoList;
    }

    @Override
    public Integer findCabinStatusIdByParameters(Integer cruiseStatusId, Integer cabinStatusId) {
        return Optional.ofNullable(cabinStatusRepository.findCabinStatusIdByParameters(cruiseStatusId, cabinStatusId))
                .orElseThrow(() -> new IllegalArgumentException("CabinStatus not found"));
    }
}


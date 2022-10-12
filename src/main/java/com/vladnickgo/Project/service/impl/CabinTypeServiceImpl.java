package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.dao.CabinTypeDao;
import com.vladnickgo.Project.dao.entity.CabinType;
import com.vladnickgo.Project.service.CabinTypeService;
import com.vladnickgo.Project.service.mapper.Mapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CabinTypeServiceImpl implements CabinTypeService {
    private final CabinTypeDao cabinTypeRepository;
    private final Mapper<CabinTypeDto, CabinType> mapper;

    public CabinTypeServiceImpl(CabinTypeDao cabinTypeRepository, Mapper<CabinTypeDto, CabinType> mapper) {
        this.cabinTypeRepository = cabinTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CabinTypeDto> findAll() {
        return cabinTypeRepository.findAll().stream()
                .map(mapper::mapEntityToDto)
                .sorted(Comparator.comparing(CabinTypeDto::getPrice, Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }
}

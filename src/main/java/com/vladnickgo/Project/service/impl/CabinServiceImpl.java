package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.CabinDto;
import com.vladnickgo.Project.dao.CabinDao;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.service.CabinService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.validator.Validator;

import java.util.Optional;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.CABIN_NOT_FOUND;

public class CabinServiceImpl implements CabinService {

    private final CabinDao cabinRepository;
    private final Mapper<CabinDto, Cabin> cabinMapper;
    private final Validator<CabinDto> validator;

    public CabinServiceImpl(CabinDao cabinRepository, Mapper<CabinDto, Cabin> cabinMapper, Validator<CabinDto> validator) {
        this.cabinRepository = cabinRepository;
        this.cabinMapper = cabinMapper;
        this.validator = validator;
    }


    @Override
    public CabinDto findFreeCabinByParameters(Integer cabinTypeId, Integer cruiseId) {
        Cabin freeCabinByParameters = Optional.ofNullable(cabinRepository.findFreeCabinByParameters(cabinTypeId, cruiseId))
                .orElseThrow(() -> new IllegalArgumentException(CABIN_NOT_FOUND));
        return cabinMapper.mapEntityToDto(freeCabinByParameters);
    }
}

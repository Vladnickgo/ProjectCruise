package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.PortDao;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.service.PortService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class PortServiceImpl implements PortService {
    private final PortDao portRepository;
    private final Mapper<PortDto, Port> portMapper;
    private final Validator<PortDto> validator;

    public PortServiceImpl(PortDao portRepository, Mapper<PortDto, Port> portMapper, Validator<PortDto> validator) {
        this.portRepository = portRepository;
        this.portMapper = portMapper;
        this.validator = validator;
    }

    @Override
    public List<PortDto> findAll() {
        return portRepository.findAll().stream().map(portMapper::mapEntityToDto).collect(Collectors.toList());
    }
}

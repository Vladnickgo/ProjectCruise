package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.PortDao;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.service.PortService;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class PortServiceImpl implements PortService {
    private static final String PORT_ALREADY_EXIST_ERROR_MESSAGE = "A port with name %S already exist";
    private final PortDao portRepository;
    private final Mapper<PortDto, Port> portMapper;
    private final Validator<PortDto> validator;

    public PortServiceImpl(PortDao portRepository, Mapper<PortDto, Port> portMapper, Validator<PortDto> validator) {
        this.portRepository = portRepository;
        this.portMapper = portMapper;
        this.validator = validator;
    }

    @Override
    public Integer getNumberOfPages(PortRequestDtoUtil portRequestDtoUtil) {
        Integer itemsOnPage = portRequestDtoUtil.getItemsOnPage();
        Integer size = portRepository.countAll();
        return size / itemsOnPage + (size % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public void deletePortById(Integer id) {
        portRepository.deletePortById(id);
    }

    @Override
    public List<PortDto> findAllByPageAndSorting(PortRequestDtoUtil portRequestDtoUtil) {
        return portRepository.findAllByPageAndSorting(portRequestDtoUtil)
                .stream()
                .map(portMapper::mapEntityToDto)
                .sorted(portRequestDtoUtil.extractedComparator())
                .collect(Collectors.toList());
    }

    @Override
    public void addPort(PortDto portDto) {
        validator.validate(portDto);
        Port port = portMapper.mapDtoToEntity(portDto);
        if(portRepository.findByNameUa(portDto.getPortNameUa()).isPresent()){
            String message = String.format(PORT_ALREADY_EXIST_ERROR_MESSAGE, port.getPortNameUa());
            throw new EntityAlreadyExistException(message);
        }
        if(portRepository.findByNameEn(portDto.getPortNameEn()).isPresent()){
            String message = String.format(PORT_ALREADY_EXIST_ERROR_MESSAGE, port.getPortNameEn());
            throw new EntityAlreadyExistException(message);
        }
        portRepository.save(port);
    }

    @Override
    public List<PortDto> findAll() {
        return portRepository.findAll().stream()
                .map(portMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}

package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.entity.Port;

public class PortMapper implements Mapper<PortDto, Port> {
    @Override
    public Port mapDtoToEntity(PortDto portDto) {
        return Port.newBuilder()
                .id(portDto.getId())
                .portNameUa(portDto.getPortNameUa())
                .portNameEn(portDto.getPortNameEn())
                .countryUa(portDto.getCountryUa())
                .countryEn(portDto.getCountryEn())
                .build();
    }

    @Override
    public PortDto mapEntityToDto(Port port) {
        return PortDto.newBuilder()
                .id(port.getId())
                .portNameUa(port.getPortNameUa())
                .portNameEn(port.getPortNameEn())
                .countryUa(port.getCountryUa())
                .countryEn(port.getCountryEn())
                .build();
    }
}

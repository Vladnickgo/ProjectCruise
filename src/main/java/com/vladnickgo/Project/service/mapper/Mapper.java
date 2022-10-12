package com.vladnickgo.Project.service.mapper;

public interface Mapper<DTO,ENTITY> {
    ENTITY mapDtoToEntity(DTO dto);
    DTO mapEntityToDto(ENTITY entity);
}

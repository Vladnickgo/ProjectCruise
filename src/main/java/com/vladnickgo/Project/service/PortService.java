package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PortService {
    List<PortDto> findAll();

    void addPort(PortDto portDto);

    List<PortDto> findAllByPageAndSorting(PortRequestDtoUtil portRequestDtoUtil);

    Integer getNumberOfPages(PortRequestDtoUtil portRequestDtoUtil);

    void deletePortById(Integer id);
}

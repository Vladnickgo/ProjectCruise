package com.vladnickgo.Project.service;

import com.vladnickgo.Project.controller.dto.PortDto;

import java.util.List;

public interface PortService {
    List<PortDto> findAll();
}

package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;

import java.util.List;

public interface PortDao extends CrudDao<Port,Integer> {
    List<Port> findAllByPageAndSorting(PortRequestDtoUtil portRequestDtoUtil);

    Integer countAll();
}

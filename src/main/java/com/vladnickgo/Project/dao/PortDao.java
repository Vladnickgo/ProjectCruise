package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;

import java.util.List;
import java.util.Optional;

public interface PortDao extends CrudDao<Port,Integer> {
    List<Port> findAllByPageAndSorting(PortRequestDtoUtil portRequestDtoUtil);

    Integer countAll();

    Optional<Port> findByNameUa(String portNameEn);

    Optional<Port> findByNameEn(String portNameEn);

    void deletePortById(Integer id);
}

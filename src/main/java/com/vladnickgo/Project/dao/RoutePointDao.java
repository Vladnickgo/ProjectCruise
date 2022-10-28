package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.RoutePoint;

import java.util.List;

public interface RoutePointDao extends CrudDao<RoutePoint, Integer> {

    List<RoutePoint> findAllByRouteId(Integer routeId);

    void deleteById(Integer routePointId);
}

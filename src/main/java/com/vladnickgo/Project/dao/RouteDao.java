package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;

public interface RouteDao extends CrudDao<Route,Integer> {
    List<Route> findAllByNumberOfPageAndSorting(RouteRequestDtoUtil routeRequestDtoUtil);
    Integer countAll();
    Integer addRouteAndRoutePoint(Route route, Integer portId) throws SQLException;
}

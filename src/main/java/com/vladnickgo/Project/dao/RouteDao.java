package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RouteDao extends CrudDao<Route, Integer> {
    List<Route> findAllByNumberOfPageAndSorting(RouteRequestDtoUtil routeRequestDtoUtil);

    Integer countAll();

    Integer addRouteAndRoutePoint(Route route, Integer portId) throws SQLException;

    Optional<Route> findByName(String routeName);
}

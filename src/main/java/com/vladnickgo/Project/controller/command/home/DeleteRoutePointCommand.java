package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.RoutePointService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoutePointCommand implements Command {
    private final ApplicationContextInjector contextInjector=ApplicationContextInjector.getInstance();
    private final RoutePointService routePointService= contextInjector.getRoutePontService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer routeId = Integer.valueOf(request.getParameter("routeId"));
        Integer routePointId = Integer.valueOf(request.getParameter("routePointId"));
        routePointService.deleteRoutePointById(routePointId);
        return String.format("home?command=editRouteDataCommand&routeId=%d",routeId);
    }
}

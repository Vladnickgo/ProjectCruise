package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.RoutePointRequestDto;
import com.vladnickgo.Project.service.RoutePointService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRoutePointCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoutePointService routePointService = contextInjector.getRoutePontService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer portId = Integer.valueOf(request.getParameter("portId"));
        Integer routeId = Integer.valueOf(request.getParameter("routeId"));
        Integer dayNumber = Integer.valueOf(request.getParameter("dayNumber"));
        RoutePointRequestDto routePointRequestDto = RoutePointRequestDto.newBuilder()
                .routeId(routeId)
                .dayNumber(dayNumber)
                .portId(portId)
                .build();
        routePointService.createRoutePoint(routePointRequestDto);
        System.out.println("portId: " + portId);
        System.out.println("routeId: " + routeId);
        System.out.println("dayNumber: " + dayNumber);
        return String.format("home?command=editRouteDataCommand&routeId=%d", routeId);
    }
}

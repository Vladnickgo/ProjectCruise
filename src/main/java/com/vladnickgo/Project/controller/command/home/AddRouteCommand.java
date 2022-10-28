package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRouteCommand implements Command {
    private static final String ROUTE_CREATED_MESSAGE = "Route created";
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RouteService routeService = contextInjector.getRouteService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routeName = request.getParameter("routeName");
        String language = (String) request.getSession().getAttribute("language");
        System.out.println("AddRouteCommand: " + language);
        request.setAttribute("language", language);
        RouteDto routeDto = RouteDto.newBuilder()
                .routeName(routeName)
                .build();

        try {
            routeService.addRoute(routeDto);
            request.getSession().setAttribute("message", ROUTE_CREATED_MESSAGE);
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("message", e.getMessage());
        }
        return "home?command=editRoutesCommand";
    }
}

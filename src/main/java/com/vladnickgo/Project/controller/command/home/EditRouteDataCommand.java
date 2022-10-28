package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.RoutePointDto;
import com.vladnickgo.Project.service.RoutePointService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditRouteDataCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RoutePointService routePointService = contextInjector.getRoutePontService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        Integer routeId = Integer.valueOf(request.getParameter("routeId"));
        List<RoutePointDto> routePointDtoList = routePointService.findAllByRouteId(routeId);
        request.setAttribute("routePointDtoList", routePointDtoList);
        request.setAttribute("routeId", routeId);
        return PagesConstant.EDIT_ROUTE_DATA_PAGE;
    }
}

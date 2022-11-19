package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.controller.dto.RouteDto;
import com.vladnickgo.Project.controller.dto.RouteRequestDto;
import com.vladnickgo.Project.service.PortService;
import com.vladnickgo.Project.service.RouteService;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditRoutesCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final RouteService routeService = contextInjector.getRouteService();
    private final PortService portService = contextInjector.getPortService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String message = (String) request.getSession().getAttribute("message");
        request.getSession().removeAttribute("message");
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String routeId = request.getParameter("routeId");
        String routeName = request.getParameter("routeName");
        RouteRequestDto routeRequestDto = RouteRequestDto.newBuilder()
                .numberOfPage(numberOfPage)
                .recordsOnPage(recordsOnPage)
                .build();
        RouteRequestDtoUtil routeRequestDtoUtil = new RouteRequestDtoUtil(routeRequestDto);
        List<RouteDto> routeList = routeService.findAllByNumberOfPageAndSorting(routeRequestDtoUtil);
        Integer pages = routeService.getNumberOfPages(routeRequestDtoUtil.getItemsOnPage());
        List<PortDto> portList = portService.findAll();
        request.setAttribute("routeName",routeName);
        request.setAttribute("routeId",routeId!=null?Integer.valueOf(routeId):null);
        request.setAttribute("routeList", routeList);
        request.setAttribute("portList", portList);
        request.setAttribute("numberOfPage", routeRequestDtoUtil.getNumberOfPage());
        request.setAttribute("recordsOnPage", routeRequestDtoUtil.getItemsOnPage());
        request.setAttribute("totalPages", pages);
        request.setAttribute("message", message);
        return PagesConstant.EDIT_ROUTES_PAGE;
    }
}

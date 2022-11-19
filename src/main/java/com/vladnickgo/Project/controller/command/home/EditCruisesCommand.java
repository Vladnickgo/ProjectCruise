package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.*;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.RoutePointService;
import com.vladnickgo.Project.service.RouteService;
import com.vladnickgo.Project.service.ShipService;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class EditCruisesCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final CruiseService cruiseService = contextInjector.getCruiseService();
    private final RouteService routeService = contextInjector.getRouteService();
    private final RoutePointService routePointService = contextInjector.getRoutePontService();
    private final ShipService shipService = contextInjector.getShipService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String dateStart = request.getParameter("dateStart");
        LocalDate localDateStart = dateStart == null ? LocalDate.now().plusMonths(1) : LocalDate.parse(dateStart);
        String routeId = request.getParameter("routeId");
        String shipId = request.getParameter("shipId");
        String cruiseName = request.getParameter("cruiseName");
        String message = request.getParameter("message");
        if (message != null) {
            CruiseDto cruiseDto = (CruiseDto) request.getSession().getAttribute("cruiseDto");
            routeId = cruiseDto.getRouteID()==null?null:String.valueOf(cruiseDto.getRouteID());
            request.getSession().removeAttribute("cruiseDto");
        }
        CruiseRequestDto cruiseRequestDto = getCruiseRequestDto(request);
        CruiseRequestDtoUtil cruiseRequestDtoUtil = new CruiseRequestDtoUtil(cruiseRequestDto);
        List<CruiseResponseDto> cruiseList = cruiseService.findAll(cruiseRequestDtoUtil);
        Integer totalPages = cruiseService.getNumberOfPages(cruiseRequestDtoUtil.getItemsOnPage());
        List<RouteDto> routeList = routeService.findAllRoutes();
        if (routeId != null) {
            Integer routeIdInteger = Integer.valueOf(routeId);
            request.setAttribute("routeId", routeIdInteger);
            List<RoutePointDto> routePointDtoList = routePointService.findAllByRouteId(routeIdInteger);
            LocalDate localDateEnd = localDateStart.plusDays(routePointDtoList.size());
            List<ShipDto> shipList = shipService.findAllFreeShipsByDateStartAndDateEnd(localDateStart, localDateEnd);
            request.setAttribute("shipList", shipList);
            request.setAttribute("routePointList", routePointDtoList);
            request.setAttribute("dateEnd", localDateEnd);
        }
        request.setAttribute("shipId", shipId);
        request.setAttribute("cruiseName", cruiseName);
        request.setAttribute("cruiseList", cruiseList);
        request.setAttribute("routeList", routeList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("recordsOnPage", cruiseRequestDtoUtil.getItemsOnPage());
        request.setAttribute("numberOfPage", cruiseRequestDtoUtil.getNumberOfPage());
        request.setAttribute("sorting", cruiseRequestDtoUtil.getSorting());
        request.setAttribute("ordering", cruiseRequestDtoUtil.getOrdering());
        request.setAttribute("statusAvailable", cruiseRequestDtoUtil.getStatusAvailable());
        request.setAttribute("statusInProgress", cruiseRequestDtoUtil.getStatusInProgress());
        request.setAttribute("statusFinished", cruiseRequestDtoUtil.getStatusFinished());
        request.setAttribute("statusNotAvailable", cruiseRequestDtoUtil.getStatusNotAvailable());
        request.setAttribute("dateStart", dateStart == null ? LocalDate.now().plusMonths(1) : LocalDate.parse(dateStart));
        request.setAttribute("minDateStart", LocalDate.now().plusMonths(1));
        request.setAttribute("maxDateStart", LocalDate.now().plusMonths(12));
        request.setAttribute("message", message);
        request.setAttribute("command", command);
        return PagesConstant.EDIT_CRUISES_PAGE;
    }

    private CruiseRequestDto getCruiseRequestDto(HttpServletRequest request) {
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String statusAvailable = request.getParameter("statusAvailable");
        String statusInProgress = request.getParameter("statusInProgress");
        String statusFinished = request.getParameter("statusFinished");
        String statusNotAvailable = request.getParameter("statusNotAvailable");
        return CruiseRequestDto.newBuilder()
                .numberOfPage(numberOfPage)
                .recordsOnPage(recordsOnPage)
                .sorting(sorting)
                .ordering(ordering)
                .statusAvailable(statusAvailable)
                .statusInProgress(statusInProgress)
                .statusFinished(statusFinished)
                .statusNotAvailable(statusNotAvailable)
                .build();
    }

}

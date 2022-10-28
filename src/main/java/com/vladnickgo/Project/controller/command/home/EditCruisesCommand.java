package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseRequestDto;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.RouteService;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String statusAvailable = request.getParameter("statusAvailable");
        String statusInProgress = request.getParameter("statusInProgress");
        String statusFinished = request.getParameter("statusFinished");
        String statusNotAvailable = request.getParameter("statusNotAvailable");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");

        CruiseRequestDto cruiseRequestDto = CruiseRequestDto.newBuilder()
                .numberOfPage(numberOfPage)
                .recordsOnPage(recordsOnPage)
                .sorting(sorting)
                .ordering(ordering)
                .statusAvailable(statusAvailable)
                .statusInProgress(statusInProgress)
                .statusFinished(statusFinished)
                .statusNotAvailable(statusNotAvailable)
                .build();
        CruiseRequestDtoUtil cruiseRequestDtoUtil = new CruiseRequestDtoUtil(cruiseRequestDto);
        List<CruiseDto> cruiseList = cruiseService.findAll(cruiseRequestDtoUtil);
        Integer totalPages = cruiseService.getNumberOfPages(cruiseRequestDtoUtil.getItemsOnPage());

        request.setAttribute("cruiseList", cruiseList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("recordsOnPage", cruiseRequestDtoUtil.getItemsOnPage());
        request.setAttribute("numberOfPage", cruiseRequestDtoUtil.getNumberOfPage());
        request.setAttribute("sorting", cruiseRequestDtoUtil.getSorting());
        request.setAttribute("ordering", cruiseRequestDtoUtil.getOrdering());
        request.setAttribute("statusAvailable", cruiseRequestDtoUtil.getStatusAvailable());
        request.setAttribute("statusInProgress", statusInProgress);
        request.setAttribute("statusFinished", statusFinished);
        request.setAttribute("statusNotAvailable", statusNotAvailable);
        request.setAttribute("dateStart", dateStart == null ? LocalDate.now().plusMonths(3) : LocalDate.parse(dateStart));
        request.setAttribute("minDateStart", LocalDate.now().plusMonths(3));
        request.setAttribute("maxDateStart", LocalDate.now().plusMonths(12));
        request.setAttribute("dateEnd", dateEnd == null ? LocalDate.now().plusMonths(3).plusDays(3) : LocalDate.parse(dateEnd));
        request.setAttribute("minDateEnd", LocalDate.now().plusMonths(3).plusDays(3));
        request.setAttribute("maxDateEnd", LocalDate.now().plusMonths(12).plusDays(3));
        request.setAttribute("command", command);
        return PagesConstant.EDIT_CRUISES_PAGE;
    }
}

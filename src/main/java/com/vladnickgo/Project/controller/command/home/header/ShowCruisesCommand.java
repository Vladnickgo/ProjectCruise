package com.vladnickgo.Project.controller.command.home.header;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.CruiseRequestDto;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowCruisesCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final CruiseService cruiseService = contextInjector.getCruiseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        CruiseRequestDto cruiseRequestDto = CruiseRequestDto.newBuilder()
                .numberOfPage(numberOfPage)
                .recordsOnPage(recordsOnPage)
                .build();
        CruiseRequestDtoUtil cruiseRequestDtoUtil = new CruiseRequestDtoUtil(cruiseRequestDto);
        List<CruiseDto> allCruises = cruiseService.findAll(cruiseRequestDtoUtil);
        Integer pages = cruiseService.getNumberOfPages(cruiseRequestDtoUtil.getItemsOnPage());
        request.setAttribute("listOfCruises", allCruises);
        request.setAttribute("totalPages", pages);
        request.setAttribute("recordsOnPage", cruiseRequestDto.getRecordsOnPage());
        request.setAttribute("numberOfPage", cruiseRequestDtoUtil.getNumberOfPage());
        return PagesConstant.SHOW_CRUISES;
    }

}
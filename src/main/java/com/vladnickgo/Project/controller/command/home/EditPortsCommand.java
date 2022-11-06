package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.controller.dto.PortRequestDto;
import com.vladnickgo.Project.service.PortService;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditPortsCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PortService portService = contextInjector.getPortService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String itemsOnPage = request.getParameter("itemsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
        PortRequestDto portRequestDto = PortRequestDto.newBuilder()
                .recordsOnPage(itemsOnPage)
                .numberOfPage(numberOfPage)
                .sorting(sorting)
                .ordering(ordering)
                .build();
        PortRequestDtoUtil portRequestDtoUtil = new PortRequestDtoUtil(portRequestDto);
        List<PortDto> portList = portService.findAllByPageAndSorting(portRequestDtoUtil);
        Integer totalPages = portService.getNumberOfPages(portRequestDtoUtil);
        request.setAttribute("portList", portList);
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        request.setAttribute("itemsOnPage", portRequestDtoUtil.getItemsOnPage());
        request.setAttribute("sorting", portRequestDtoUtil.getSorting());
        request.setAttribute("ordering", portRequestDtoUtil.getOrdering());
        request.setAttribute("numberOfPage", portRequestDtoUtil.getNumberOfPage());
        System.out.println("numberOfPage: " + numberOfPage);
        request.setAttribute("totalPages", totalPages);
        return PagesConstant.EDIT_PORTS_PAGE;
    }
}
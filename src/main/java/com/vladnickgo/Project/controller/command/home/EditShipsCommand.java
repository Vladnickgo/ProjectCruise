package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CabinTypeDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.controller.dto.ShipRequestDto;
import com.vladnickgo.Project.service.CabinTypeService;
import com.vladnickgo.Project.service.ShipService;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditShipsCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final ShipService shipService = contextInjector.getShipService();
    private final CabinTypeService cabinTypeService = contextInjector.getCabinTypeService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String numberOfPage = request.getParameter("numberOfPage");
        String itemsOnPage = request.getParameter("recordsOnPage");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        ShipRequestDto shipRequestDto = ShipRequestDto.newBuilder()
                .numberOfPage(numberOfPage)
                .recordsOnPage(itemsOnPage)
                .sorting(sorting)
                .ordering(ordering)
                .build();
        ShipRequestDtoUtil shipRequestDtoUtil=new ShipRequestDtoUtil(shipRequestDto);
        Integer totalPages = shipService.getTotalPages(shipRequestDtoUtil.getItemsOnPage());
        List<ShipDto> shipList = shipService.findAllShipsByNumberOfPageAndRecordsOnPage(shipRequestDtoUtil);
        List<CabinTypeDto> allCabinTypes = cabinTypeService.findAll();
        request.setAttribute("numberOfPage",shipRequestDtoUtil.getNumberOfPage());
        request.setAttribute("recordsOnPage",shipRequestDtoUtil.getItemsOnPage());
        request.setAttribute("sorting",shipRequestDtoUtil.getSorting());
        request.setAttribute("ordering",shipRequestDtoUtil.getOrdering());
        request.setAttribute("allCabinTypes", allCabinTypes);
        request.setAttribute("totalPages",totalPages);
        request.setAttribute("shipList", shipList);
        request.setAttribute("command", command);
        return PagesConstant.EDIT_LINERS_PAGE;
    }
}

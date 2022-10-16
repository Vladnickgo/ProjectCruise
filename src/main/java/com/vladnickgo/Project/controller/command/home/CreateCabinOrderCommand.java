package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.CabinStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class CreateCabinOrderCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final CabinStatusService cabinStatusService = contextInjector.getCabinStatusService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer cabinTypeId = Integer.valueOf(request.getParameter("cabinTypeId"));
        Integer cruiseId = Integer.valueOf(request.getParameter("cruiseId"));
        Integer nights = Integer.valueOf(request.getParameter("nights"));
        String routeName = request.getParameter("routeName");
        String cabinTypeName = request.getParameter("cabinTypeName");
        String shipName = request.getParameter("shipName");
        String cruise = request.getParameter("cruise");
        LocalDate dateStart = LocalDate.parse(request.getParameter("dateStart"));
        LocalDate dateEnd = LocalDate.parse(request.getParameter("dateEnd"));
        Integer amount = Integer.valueOf(request.getParameter("amount"));
        String command = request.getParameter("command");
        try {
            Integer cabinStatusIdByParameters = cabinStatusService.findCabinStatusIdByParameters(cruiseId, cabinTypeId);
            request.setAttribute("cabinStatusId", cabinStatusIdByParameters);
            request.setAttribute("cabinTypeId", cabinTypeId);
            request.setAttribute("cruiseId", cruiseId);
            request.setAttribute("nights", nights);
            request.setAttribute("routeName", routeName);
            request.setAttribute("cabinTypeName", cabinTypeName);
            request.setAttribute("cruise", cruise);
            request.setAttribute("dateStart", dateStart);
            request.setAttribute("dateEnd", dateEnd);
            request.setAttribute("amount", amount);
            request.setAttribute("shipName", shipName);
            request.setAttribute("command", command);
        } catch (IllegalArgumentException e) {
            request.setAttribute("message", "notAvailable");
            return PagesConstant.NOT_AVAILABLE_CABIN;
        }
        return PagesConstant.CREATE_ORDER;
    }
}

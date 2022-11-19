package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.service.CruiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddCruiseCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final CruiseService cruiseService = contextInjector.getCruiseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String shipId = request.getParameter("shipId");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String routeId = request.getParameter("routeId");
        String cruiseName = request.getParameter("cruiseName");
        String nights = request.getParameter("nights");
        CruiseDto cruiseDto = CruiseDto.newBuilder()
                .cruiseName(cruiseName)
                .routeID(initParameter(routeId))
                .dateStart(initDate(dateStart))
                .dateEnd(initDate(dateEnd))
                .shipId(initParameter(shipId))
                .nights(initParameter(nights))
                .build();
        try {
            cruiseService.createCruise(cruiseDto);
            return "home?command=editCruisesCommand";
        } catch (IllegalArgumentException | SQLException e) {
            request.getSession().setAttribute("cruiseDto", cruiseDto);
            return "home?command=editCruisesCommand&message=" + e.getMessage();
        }

    }

    private Integer initParameter(String parameter) {
        return parameter.isBlank() ? null : Integer.valueOf(parameter);
    }

    private LocalDate initDate(String parameter) {
        return parameter.isBlank() ? null : LocalDate.parse(parameter);
    }
}

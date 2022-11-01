package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.service.CruiseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AddCruiseCommand implements Command {
    private final ApplicationContextInjector contextInjector=ApplicationContextInjector.getInstance();
    private final CruiseService cruiseService= contextInjector.getCruiseService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String shipId = request.getParameter("shipId");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String routeId = request.getParameter("routeId");
        String cruiseName = request.getParameter("cruiseName");
        String nights = request.getParameter("nights");
        System.out.println("shipId: " + shipId);
        System.out.println("dateStart: " + dateStart);
        System.out.println("dateEnd: " + dateEnd);
        System.out.println("routeId: " + routeId);
        System.out.println("cruiseName: " + cruiseName);
        CruiseDto cruiseDto = CruiseDto.newBuilder()
                .cruiseName(cruiseName)
                .routeID(Integer.valueOf(routeId))
                .dateStart(LocalDate.parse(dateStart))
                .dateEnd(LocalDate.parse(dateEnd))
                .shipId(Integer.valueOf(shipId))
                .nights(Integer.valueOf(nights))
                .build();
        cruiseService.createCruise(cruiseDto);
        return "home?command=editCruisesCommand";
    }
}

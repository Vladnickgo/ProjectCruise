package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLinerCommand implements Command {
    private final ApplicationContextInjector contextInjector=ApplicationContextInjector.getInstance();
    private final ShipService shipService= contextInjector.getShipService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer shipId = Integer.valueOf(request.getParameter("id"));
        shipService.deleteShipById(shipId);
        return "home?command=editLinersCommand";
    }
}

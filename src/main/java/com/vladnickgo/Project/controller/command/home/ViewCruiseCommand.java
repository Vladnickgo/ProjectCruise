package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.service.CruiseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewCruiseCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final CruiseService cruiseService = contextInjector.getCruiseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        Integer cruiseId = Integer.valueOf(request.getParameter("cruiseId"));
        request.setAttribute("command", command);
        CruiseDto cruiseById = cruiseService.findCruiseById(cruiseId);
        request.setAttribute("cruise", cruiseById);
        return PagesConstant.VIEW_CRUISE;
    }
}

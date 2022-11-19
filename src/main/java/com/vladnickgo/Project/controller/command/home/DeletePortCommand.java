package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePortCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PortService portService = contextInjector.getPortService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portId = request.getParameter("portId");
        String portNameUa = request.getParameter("portNameUa");
        String portNameEn = request.getParameter("portNameEn");
        portService.deletePortById(Integer.valueOf(portId));
        request.getSession().setAttribute("portName", portNameUa + "/" + portNameEn);
        request.getSession().setAttribute("message", "Port has been removed");
        return "home?command=editPortsCommand";
    }
}

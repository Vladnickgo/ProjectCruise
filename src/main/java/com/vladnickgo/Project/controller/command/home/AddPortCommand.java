package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPortCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PortService portService = contextInjector.getPortService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portNameUa = request.getParameter("portNameUa");
        String portNameEn = request.getParameter("portNameEn");
        String countryUa = request.getParameter("countryNameUa");
        String countryEn = request.getParameter("countryNameEn");
        PortDto portDto = PortDto.newBuilder()
                .portNameUa(portNameUa)
                .portNameEn(portNameEn)
                .countryUa(countryUa)
                .countryEn(countryEn)
                .build();
        try {
            portService.addPort(portDto);
            request.getSession().setAttribute("portDto",portDto);
            return "home?command=successfulPortAddingCommand";
        }catch (DataBaseRuntimeException |IllegalArgumentException e){
            request.getSession().setAttribute("portDto",portDto);
            request.getSession().setAttribute("message",e.getMessage());
            return "home?command=unsuccessfulPortAddingCommand";
        }
    }
}

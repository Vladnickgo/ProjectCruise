package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PortDto;
import com.vladnickgo.Project.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SuccessfulPortAddingCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PortService portService = contextInjector.getPortService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PortDto portDto = (PortDto)request.getSession().getAttribute("portDto");
        request.getSession().removeAttribute("portDto");
        List<PortDto> portList = portService.findAll();
        request.setAttribute("portList",portList);
        request.setAttribute("message","Port has been added");
        return PagesConstant.EDIT_PORTS_PAGE;
    }
}

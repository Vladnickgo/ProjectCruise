package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePortPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portId = request.getParameter("portId");
        String portNameUa = request.getParameter("portNameUa");
        String portNameEn = request.getParameter("portNameEn");
        String countryUa = request.getParameter("countryUa");
        String countryEn = request.getParameter("countryEn");
        System.out.println("portId: " + portId);
        request.setAttribute("portId",portId);
        request.setAttribute("portNameUa",portNameUa);
        request.setAttribute("portNameEn",portNameEn);
        request.setAttribute("countryUa",countryUa);
        request.setAttribute("countryEn",countryEn);
        return PagesConstant.DELETE_PORT_PAGE;
    }
}

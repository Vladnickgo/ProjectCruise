package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnsuccessfulRegisterCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UnsuccessfulRegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = (String) request.getSession().getAttribute("firstName");
        String lastName = (String) request.getSession().getAttribute("lastName");
        String email = (String) request.getSession().getAttribute("email");
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        LOGGER.info("errorMessage: " + errorMessage);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("errorMessage", errorMessage);
        return PagesConstant.REGISTRATION_PAGE;
    }
}

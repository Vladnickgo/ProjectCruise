package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginPageEmail = request.getParameter("loginPageEmail");
        String errorMessage = request.getParameter("errorMessage");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        request.setAttribute("loginPageEmail", loginPageEmail);
        request.setAttribute("errorMessage", errorMessage);
        LOGGER.info("errorMessage: " + errorMessage);
        return PagesConstant.LOGIN_PAGE;
    }
}

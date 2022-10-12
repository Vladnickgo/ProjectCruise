package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        request.getSession().removeAttribute("errorMessage");
        request.getSession().removeAttribute("firstName");
        request.getSession().removeAttribute("lastName");
        request.getSession().removeAttribute("email");
        return PagesConstant.REGISTRATION_PAGE;
    }
}

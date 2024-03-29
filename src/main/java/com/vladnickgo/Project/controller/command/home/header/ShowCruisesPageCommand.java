package com.vladnickgo.Project.controller.command.home.header;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowCruisesPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        return PagesConstant.SHOW_CRUISES;
    }
}

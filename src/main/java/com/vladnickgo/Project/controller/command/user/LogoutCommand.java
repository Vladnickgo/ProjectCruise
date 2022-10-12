package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return PagesConstant.LOGIN_PAGE;
    }
}

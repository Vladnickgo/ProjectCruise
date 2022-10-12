package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final UserService userService = contextInjector.getUserService();
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        try {
            UserDto user = userService.login(email, password);
            session.setAttribute("user", user);
        } catch (IllegalArgumentException exception) {
            String message = exception.getMessage();
            request.setAttribute("loginPageEmail", email);
            request.setAttribute("errorMessage", message);
            LOGGER.info("errorMessage: " + message);
            return String.format("user?command=loginPage&loginPageEmail=%S&errorMessage=%S", email, message);
        }
        return "home?command=homePage";
    }
}

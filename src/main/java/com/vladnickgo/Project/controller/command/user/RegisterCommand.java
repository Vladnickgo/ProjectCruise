package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
//import com.vladnickgo.Project.controller.dto.UserDto;
//import com.vladnickgo.Project.dao.entity.Role;
//import com.vladnickgo.Project.service.UserService;
//import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
//    private final UserService userService = contextInjector.getUserService();
    public static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

//        UserDto userDto = mapRequestToUserDto(request);
//        try {
//            request.getSession().removeAttribute("firstName");
//            request.getSession().removeAttribute("lastName");
//            request.getSession().removeAttribute("email");
//            request.getSession().removeAttribute("errorMessage");
////            userService.save(userDto);
//            request.setAttribute("userSaved", "true");
//            LOGGER.info("User saved");
//            return "user?command=successRegister";
//        } catch (IllegalArgumentException | EntityAlreadyExistException exception) {
//            String errorMessage = exception.getMessage();
//            LOGGER.info(errorMessage);
////            request.getSession().setAttribute("firstName", userDto.getFirstName());
////            request.getSession().setAttribute("lastName", userDto.getLastName());
////            request.getSession().setAttribute("email", userDto.getEmail());
//            request.getSession().setAttribute("errorMessage", errorMessage);
            return "user?command=unsuccessfulRegister";
        }
    }

//    private UserDto mapRequestToUserDto(HttpServletRequest request) {
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String confirmationPassword = request.getParameter("confirmationPassword");
//        return UserDto.newBuilder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email(email)
//                .password(password)
//                .confirmationPassword(confirmationPassword)
//                .role(Role.USER)
//                .build();
//    }
//}

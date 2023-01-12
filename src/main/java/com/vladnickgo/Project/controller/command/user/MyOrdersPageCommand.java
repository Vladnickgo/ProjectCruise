package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyOrdersPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cruiseName = request.getParameter("cruiseName");
        String paymentDate = request.getParameter("paymentDate");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        UserDto user = (UserDto)request.getSession().getAttribute("user");
        String cabinType = request.getParameter("cabinType");
        String cabinNumber = request.getParameter("cabinNumber");
        String amount = request.getParameter("amount");
        String routeName = request.getParameter("routeName");
        String userDocument = request.getParameter("userDocument");
        String orderStatusName = request.getParameter("orderStatusName");
        request.setAttribute("cruiseName", cruiseName);
        request.setAttribute("paymentDate", paymentDate);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("cabinType", cabinType);
        request.setAttribute("cabinNumber", cabinNumber);
        request.setAttribute("amount", amount);
        request.setAttribute("routeName", routeName);
        request.setAttribute("userDocument", userDocument);
        request.setAttribute("orderStatusName", orderStatusName);
        request.setAttribute("email",user.getEmail());
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        return PagesConstant.MY_ORDERS_PAGE;
    }
}

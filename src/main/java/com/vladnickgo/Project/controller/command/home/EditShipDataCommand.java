package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditShipDataCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getRequestData(request);
        return PagesConstant.EDIT_DATA_SHIP_PAGE;
    }

    public static void getRequestData(HttpServletRequest request) {
        String id = request.getParameter("id");
        String shipName = request.getParameter("shipName");
        String passengersCapacity = request.getParameter("passengersCapacity");
        String numberOfStaff = request.getParameter("numberOfStaff");
        String shipImage = request.getParameter("shipImage");
        request.setAttribute("id",id);
        request.setAttribute("shipName",shipName);
        request.setAttribute("passengersCapacity",passengersCapacity);
        request.setAttribute("numberOfStaff",numberOfStaff);
        request.setAttribute("shipImage",shipImage);
    }
}

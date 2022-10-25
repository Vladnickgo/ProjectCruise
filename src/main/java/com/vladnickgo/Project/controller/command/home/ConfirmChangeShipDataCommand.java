package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vladnickgo.Project.ApplicationConstant.SHIP_IMAGE_PATH;

public class ConfirmChangeShipDataCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final ShipService shipService = contextInjector.getShipService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String shipName = request.getParameter("shipName");
        String passengersCapacity = request.getParameter("passengersCapacity");
        String numberOfStaff = request.getParameter("newNumberOfStaff");
        String shipImage = String.format(SHIP_IMAGE_PATH,request.getParameter("shipImage"));
        ShipDto shipDto = ShipDto.newBuilder()
                .id(Integer.valueOf(id))
                .shipName(shipName)
                .passengersCapacity(Integer.valueOf(passengersCapacity))
                .numberOfStaff(Integer.valueOf(numberOfStaff))
                .shipImage(shipImage)
                .build();
        shipService.updateShip(shipDto);
        return "home?command=editLinersCommand";
    }
}

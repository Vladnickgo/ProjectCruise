package com.vladnickgo.Project.controller.command.home;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.ShipDto;
import com.vladnickgo.Project.service.ShipService;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import org.jetbrains.annotations.Nullable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.vladnickgo.Project.PagesConstant.SHIP_IMAGE_PATH;


public class AddShipCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final ShipService shipService = contextInjector.getShipService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShipDto shipDto = getShipDto(request);
        List<CabinRequestDto> cabinRequestDtoList = getCabinRequestDtoList(request);

        try {
            shipService.addShip(shipDto, cabinRequestDtoList);
        } catch (IllegalArgumentException | EntityAlreadyExistException | SQLException e) {
            return "home?command=editLinersCommand&message=" + e.getMessage();
        }
        return "home?command=editLinersCommand";
    }

    private ShipDto getShipDto(HttpServletRequest request) {
        String shipName = getStringParameter(request, "shipName");
        Integer numberOfStaff = getNumberOfStaff(request);
        String[] numbersOfCabin = request.getParameterValues("numberOfCabin");
        String[] cabinCapacities = request.getParameterValues("persons");
        String shipImage = getStringParameter(request, "shipImage");
        Integer passengers = getPassengers(numbersOfCabin, cabinCapacities);
        return ShipDto.newBuilder()
                .shipName(shipName)
                .numberOfStaff(numberOfStaff)
                .passengersCapacity(passengers)
                .shipImage(String.format(SHIP_IMAGE_PATH, shipImage))
                .build();
    }

    @Nullable
    private String getStringParameter(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter).isBlank() ? null : request.getParameter(parameter);
    }

    @Nullable
    private Integer getNumberOfStaff(HttpServletRequest request) {
        return request.getParameter("numberOfStaff").isBlank() ? null : Integer.valueOf(request.getParameter("numberOfStaff"));
    }

    private Integer getPassengers(String[] numbersOfCabin, String[] cabinCapacities) {
        int passengers = 0;
        for (int i = 0; i < numbersOfCabin.length; i++) {
            passengers += Integer.parseInt(numbersOfCabin[i]) * Integer.parseInt(cabinCapacities[i]);
        }
        return passengers == 0 ? null : passengers;
    }

    private List<CabinRequestDto> getCabinRequestDtoList(HttpServletRequest request) {
        List<CabinRequestDto> cabinRequestDtoList = new ArrayList<>();
        String[] cabinTypeIds = request.getParameterValues("cabinTypeIds");
        String[] numbersOfCabin = request.getParameterValues("numberOfCabin");
        for (int i = 0; i < cabinTypeIds.length; i++) {
            cabinRequestDtoList.add(CabinRequestDto.newBuilder()
                    .cabinTypeId(Integer.valueOf(cabinTypeIds[i]))
                    .numberOfCabins(Integer.valueOf(numbersOfCabin[i]))
                    .build());
        }
        return cabinRequestDtoList;
    }
}

package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CabinTypeResponseDto;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.ShipService;
import com.vladnickgo.Project.service.util.LocalDateDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdminStatisticCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final ShipService shipService = contextInjector.getShipService();
    private final CruiseService cruiseService = contextInjector.getCruiseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String cruiseId = request.getParameter("cruiseId");
        LocalDateDtoUtil localDateDtoUtil = new LocalDateDtoUtil(dateStart, dateEnd);
        LocalDateDto localDateDto = localDateDtoUtil.getLocalDateDto();
        Map<String, Integer> numberOfCruisesForShips = shipService.getNumberOfCruisesForShips(localDateDto);
        List<CruiseDto> cruiseDtoList = cruiseService.findAllByDates(localDateDto);
        List<CabinTypeResponseDto> cabinTypeResponseDtoList = cruiseService.getNumberOfAllAndBusyCabins(cruiseId);
        request.setAttribute("dateStart", localDateDtoUtil.getLocalDateDto().getDateStart());
        request.setAttribute("dateEnd", localDateDtoUtil.getLocalDateDto().getDateEnd());
        request.setAttribute("minDateStart", localDateDtoUtil.getMinDateStart());
        request.setAttribute("maxDateStart", localDateDtoUtil.getMaxDateStart());
        request.setAttribute("minDateEnd", localDateDtoUtil.getMinDateEnd());
        request.setAttribute("maxDateEnd", localDateDtoUtil.getMaxDateEnd());
        request.setAttribute("numberOfCruisesForShips", numberOfCruisesForShips);
        request.setAttribute("cruiseDtoList", cruiseDtoList);
        request.setAttribute("cabinTypeResponseDtoList", cabinTypeResponseDtoList);
        request.setAttribute("cruiseId", cruiseId);
        return PagesConstant.ADMIN_STATISTIC_PAGE;
    }
}

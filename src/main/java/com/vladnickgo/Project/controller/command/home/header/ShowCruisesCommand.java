package com.vladnickgo.Project.controller.command.home.header;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.CruiseDatesDto;
import com.vladnickgo.Project.controller.dto.CruiseDurationDto;
import com.vladnickgo.Project.controller.dto.CruiseResponseDto;
import com.vladnickgo.Project.controller.dto.CruiseRequestDto;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ShowCruisesCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final CruiseService cruiseService = contextInjector.getCruiseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberOfPage = request.getParameter("numberOfPage");
        String recordsOnPage = request.getParameter("recordsOnPage");
        try {
            CruiseDurationDto cruiseDurationDto = getCruiseDurationDto(request);
            CruiseDatesDto cruiseDatesDto = getCruiseDatesDto(request);
            String command = request.getParameter("command");
            request.setAttribute("command", command);
            CruiseRequestDto cruiseRequestDto = CruiseRequestDto.newBuilder()
                    .cruiseDurationDto(cruiseDurationDto)
                    .cruiseDatesDto(cruiseDatesDto)
                    .numberOfPage(numberOfPage)
                    .recordsOnPage(recordsOnPage)
                    .build();
            CruiseRequestDtoUtil cruiseRequestDtoUtil = new CruiseRequestDtoUtil(cruiseRequestDto);
            List<CruiseResponseDto> allCruises = cruiseService.findAllByDatesAndDuration(cruiseRequestDtoUtil);
            Integer pages = cruiseService.getNumberOfPagesByDatesAndDuration(cruiseRequestDtoUtil);
            request.setAttribute("bottomDuration", cruiseRequestDtoUtil.getBottomCruiseDuration());
            request.setAttribute("topDuration", cruiseRequestDtoUtil.getTopCruiseDuration());
            request.setAttribute("minDurationValue", cruiseRequestDtoUtil.getMinCruseDuration());
            request.setAttribute("maxDurationValue", cruiseRequestDtoUtil.getMaxCruiseDuration());
            request.setAttribute("minDateStart", cruiseRequestDtoUtil.getMinDateStart());
            request.setAttribute("maxDateEnd", cruiseRequestDtoUtil.getMaxDateEnd());
            request.setAttribute("dateStart", cruiseRequestDtoUtil.getDateStart());
            request.setAttribute("dateEnd", cruiseRequestDtoUtil.getDateEnd());
            request.setAttribute("listOfCruises", allCruises);
            request.setAttribute("totalPages", pages);
            request.setAttribute("recordsOnPage", cruiseRequestDto.getRecordsOnPage());
            request.setAttribute("numberOfPage", cruiseRequestDtoUtil.getNumberOfPage());
            return PagesConstant.SHOW_CRUISES;
        } catch (IllegalArgumentException e) {
            return PagesConstant.CRUISE_NOT_EXIST;
        }

    }

    private CruiseDatesDto getCruiseDatesDto(HttpServletRequest request) {
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        LocalDate minDateStart = cruiseService.getMinDateStartForStatusAvailable();
        LocalDate maxDateEnd = cruiseService.getMaxDateEndForStatusAvailable();
        return CruiseDatesDto.newBuilder()
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .minDateStart(minDateStart)
                .maxDateEnd(maxDateEnd)
                .build();
    }

    private CruiseDurationDto getCruiseDurationDto(HttpServletRequest request) {
        String bottomDurationStr = request.getParameter("bottomDuration");
        String topDurationStr = request.getParameter("topDuration");
        Integer maxDurationValue = cruiseService.getMaxCruiseDuration();
        Integer minDurationValue = cruiseService.getMinCruiseDuration();
        return CruiseDurationDto.newBuilder()
                .bottomDuration(bottomDurationStr)
                .topDuration(topDurationStr)
                .minDurationValue(minDurationValue)
                .maxDurationValue(maxDurationValue)
                .build();
    }

}
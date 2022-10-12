package com.vladnickgo.Project.controller.command.home.header;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
//import com.vladnickgo.Project.controller.dto.RoomStatusDto;
//import com.vladnickgo.Project.controller.dto.RoomStatusDtoRequest;
//import com.vladnickgo.Project.service.RoomStatusService;
//import com.vladnickgo.Project.service.util.RoomStatusDtoRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRoomsCommand implements Command {

    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
//    private final RoomStatusService roomStatusService = contextInjector.getRoomStatusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        removeSessionAttributes(request);

        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String recordsOnPage = request.getParameter("recordsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
        String statusFree = request.getParameter("statusFree");
        String statusBooked = request.getParameter("statusBooked");
        String checkInStr = request.getParameter("checkIn");
        String checkOutStr = request.getParameter("checkOut");
        String command = request.getParameter("command");
//        RoomStatusDtoRequest roomStatusDtoRequest = RoomStatusDtoRequest.newBuilder()
//                .hotelId(hotelId)
//                .hotelName(hotelName)
//                .sorting(sorting)
//                .ordering(ordering)
//                .itemsOnPage(recordsOnPage)
//                .numberOfPage(numberOfPage)
//                .statusFree(statusFree)
//                .statusBooked(statusBooked)
//                .signInStr(checkInStr)
//                .signOutStr(checkOutStr)
//                .build();

//        RoomStatusDtoRequestUtil roomStatusDtoRequestUtil = new RoomStatusDtoRequestUtil(roomStatusDtoRequest);
//        Integer totalPages = roomStatusService.getNumberOfPages(roomStatusDtoRequestUtil);
//        List<RoomStatusDto> roomStatusList = roomStatusService.findAll(roomStatusDtoRequestUtil);
//        sorting = roomStatusDtoRequestUtil.getSorting();
//        ordering = roomStatusDtoRequestUtil.getOrdering();
////        recordsOnPage = roomStatusDtoRequestUtil.getPagenableElementsDto().getItemsOnPage().toString();
//        numberOfPage = roomStatusDtoRequestUtil.getNumberOfPage().toString();
//        checkInStr = roomStatusDtoRequestUtil.getSignIn().toString();
//        checkOutStr = roomStatusDtoRequestUtil.getSignOut().toString();
//        statusFree = roomStatusDtoRequestUtil.getStatusFree();
//        statusBooked = roomStatusDtoRequestUtil.getStatusBooked();

        request.setAttribute("command", command);
        request.setAttribute("hotelId", hotelId);
        request.setAttribute("hotelName", hotelName);
        request.setAttribute("sorting", sorting);
        request.setAttribute("ordering", ordering);
        request.setAttribute("recordsOnPage", recordsOnPage);
//        request.setAttribute("totalPages", totalPages);
//        request.setAttribute("listOfRooms", roomStatusList);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("checkIn", checkInStr);
        request.setAttribute("checkOut", checkOutStr);
        request.setAttribute("statusFree", statusFree);
        request.setAttribute("statusBooked", statusBooked);
//        request.setAttribute("minCheckIn", roomStatusDtoRequestUtil.getMinSignIn());
//        request.setAttribute("maxCheckIn", roomStatusDtoRequestUtil.getMaxSignIn());
//        request.setAttribute("minCheckOut", roomStatusDtoRequestUtil.getMinSignOut());
//        request.setAttribute("maxCheckOut", roomStatusDtoRequestUtil.getMaxSignOut());
        return PagesConstant.SHOW_ROOMS;
    }

    private void removeSessionAttributes(HttpServletRequest request) {
        request.getSession().removeAttribute("statusFree");
        request.getSession().removeAttribute("statusBooked");
        request.getSession().removeAttribute("typeName");
        request.getSession().removeAttribute("numberOfBeds");
        request.getSession().removeAttribute("price");
        request.getSession().removeAttribute("hotelId");
        request.getSession().removeAttribute("hotelName");
        request.getSession().removeAttribute("roomId");
        request.getSession().removeAttribute("roomStatusId");
        request.getSession().removeAttribute("checkIn");
        request.getSession().removeAttribute("checkOut");
        request.getSession().removeAttribute("sorting");
        request.getSession().removeAttribute("ordering");
        request.getSession().removeAttribute("recordsOnPage");
        request.getSession().removeAttribute("days");
    }

}
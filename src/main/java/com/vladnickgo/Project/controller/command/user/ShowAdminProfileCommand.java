package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
//import com.vladnickgo.Project.controller.dto.UsersOrderDto;
//import com.vladnickgo.Project.controller.dto.UsersOrderRequestDto;
//import com.vladnickgo.Project.service.UsersOrderService;
//import com.vladnickgo.Project.service.util.UsersOrderRequestDtoUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAdminProfileCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
//    private final UsersOrderService usersOrderService = contextInjector.getUsersOrderService();
//
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String sorting = request.getParameter("sorting");
//        String ordering = request.getParameter("ordering");
//        String itemsOnPage = request.getParameter("itemsOnPage");
//        String numberOfPage = request.getParameter("numberOfPage");
//        String statusNotDone = request.getParameter("statusNotDone");
//        String statusCompleted = request.getParameter("statusCompleted");
//        String command = request.getParameter("command");
//        request.setAttribute("command", command);
//        UsersOrderRequestDto usersOrderRequestDto = UsersOrderRequestDto.newBuilder()
//                .sorting(sorting)
//                .ordering(ordering)
//                .itemsOnPage(itemsOnPage)
//                .numberOfPage(numberOfPage)
//                .statusNotDone(statusNotDone)
//                .statusCompleted(statusCompleted)
//                .build();
//        UsersOrderRequestDtoUtil usersOrderRequestDtoUtil = new UsersOrderRequestDtoUtil(usersOrderRequestDto);
//        List<UsersOrderDto> listOfOrders = usersOrderService.findAllByParameters(usersOrderRequestDtoUtil);
//        Integer totalPages = usersOrderService.totalPages(usersOrderRequestDtoUtil);
//        request.setAttribute("listOfOrders", listOfOrders);
//        request.setAttribute("sorting", usersOrderRequestDtoUtil.getSorting());
//        request.setAttribute("ordering", usersOrderRequestDtoUtil.getOrdering());
//        request.setAttribute("itemsOnPage", usersOrderRequestDtoUtil.getItemsOnPage());
//        request.setAttribute("numberOfPage", usersOrderRequestDtoUtil.getNumberOfPage());
//        request.setAttribute("statusNotDone", usersOrderRequestDtoUtil.getStatusNotDone());
//        request.setAttribute("statusCompleted", statusCompleted);
//        request.setAttribute("totalPages", totalPages);
        return PagesConstant.ADMIN_PROFILE;
    }

}

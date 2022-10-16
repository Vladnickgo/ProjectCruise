package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.PaymentService;
//import com.vladnickgo.Project.controller.dto.BookingDto;
//import com.vladnickgo.Project.controller.dto.BookingRequestDto;
//import com.vladnickgo.Project.controller.dto.UserDto;
//import com.vladnickgo.Project.service.BookingService;
//import com.vladnickgo.Project.service.util.BookingRequestDtoUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUserProfileCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PaymentService paymentService = contextInjector.getPaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("payment");
        String command = request.getParameter("command");
        request.setAttribute("command", command);

//        BookingRequestDto bookingRequestDto = getBookingRequestDto(request);
//        Integer pages = bookingService.getNumberOfPages(bookingRequestDto);
//        List<BookingDto> bookingsByUserIdAndParameters = bookingService.findBookingsByUserIdAndParameters(bookingRequestDto);
//        BookingRequestDtoUtil bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
//        String statusPaid = bookingRequestDtoUtil.getStatusPaid();
//        String statusNotPaid = bookingRequestDtoUtil.getStatusNotPaid();
//        String statusCanceled = bookingRequestDtoUtil.getStatusCanceled();
//        String sorting = bookingRequestDtoUtil.getSorting();
//        String ordering = bookingRequestDtoUtil.getOrdering();
//        Integer itemsOnPage = bookingRequestDtoUtil.getItemsOnPage();
//        Integer numberOfPage = bookingRequestDtoUtil.getNumberOfPage();
//
//        request.setAttribute("statusNotPaid", statusNotPaid);
//        request.setAttribute("statusPaid", statusPaid);
//        request.setAttribute("statusCanceled", statusCanceled);
//        request.setAttribute("sorting", sorting);
//        request.setAttribute("ordering", ordering);
//        request.setAttribute("itemsOnPage", itemsOnPage);
//        request.setAttribute("bookingsByUserIdAndParameters", bookingsByUserIdAndParameters);
//        request.setAttribute("numberOfPage", numberOfPage);
//        request.setAttribute("totalPages", pages);

        return PagesConstant.USER_PROFILE;

    }

//    private BookingRequestDto getBookingRequestDto(HttpServletRequest request) {
//        UserDto user = (UserDto) request.getSession().getAttribute("user");
//        Integer userId = user.getId();
//        String statusNotPaid = request.getParameter("statusNotPaid");
//        String statusPaid = request.getParameter("statusPaid");
//        String statusCanceled = request.getParameter("statusCanceled");
//        String sorting = request.getParameter("sorting");
//        String ordering = request.getParameter("ordering");
//        String itemsOnPage = request.getParameter("itemsOnPage");
//        String numberOfPage = request.getParameter("numberOfPage");
//        return BookingRequestDto.newBuilder()
//                .userId(userId)
//                .statusNotPaid(statusNotPaid)
//                .statusPaid(statusPaid)
//                .statusCanceled(statusCanceled)
//                .sorting(sorting)
//                .ordering(ordering)
//                .itemsOnPage(itemsOnPage)
//                .numberOfPage(numberOfPage)
//                .build();
//    }
}

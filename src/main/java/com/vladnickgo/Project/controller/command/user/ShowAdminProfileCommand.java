package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.service.PaymentService;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;
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
    private final PaymentService paymentService = contextInjector.getPaymentService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaymentRequestDto paymentRequestDto = getPaymentRequestDto(request);
        request.getSession().removeAttribute("message");
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        PaymentRequestDtoUtil paymentRequestDtoUtil = new PaymentRequestDtoUtil(paymentRequestDto);
        Integer pages = paymentService.getNumberOfPagesByFilterParameters(paymentRequestDtoUtil);
        List<PaymentResponseDto> paymentsList = paymentService.findPaymentsByFilterParameters(paymentRequestDtoUtil);
        setRequestAttributesForShowUserProfile(request, pages, paymentRequestDtoUtil, paymentsList);
        return PagesConstant.ADMIN_PROFILE;
    }

    static void setRequestAttributesForShowUserProfile(HttpServletRequest request, Integer pages, PaymentRequestDtoUtil paymentRequestDtoUtil, List<PaymentResponseDto> paymentsList) {
        request.setAttribute("paymentsList", paymentsList);
        request.setAttribute("statusInProgress", paymentRequestDtoUtil.getStatusInProgress());
        request.setAttribute("statusConfirmed", paymentRequestDtoUtil.getStatusConfirmed());
        request.setAttribute("statusCanceled", paymentRequestDtoUtil.getStatusCanceled());
        request.setAttribute("statusCompleted", paymentRequestDtoUtil.getStatusCompleted());
        request.setAttribute("sorting", paymentRequestDtoUtil.getSorting());
        request.setAttribute("ordering", paymentRequestDtoUtil.getOrdering());
        request.setAttribute("itemsOnPage", paymentRequestDtoUtil.getItemsOnPage());
        request.setAttribute("numberOfPage", paymentRequestDtoUtil.getNumberOfPage());
        request.setAttribute("totalPages", pages);
    }

    private PaymentRequestDto getPaymentRequestDto(HttpServletRequest request) {
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        String itemsOnPage = request.getParameter("itemsOnPage");
        String numberOfPage = request.getParameter("numberOfPage");
        String statusInProgress = request.getParameter("statusInProgress");
        String statusConfirmed = request.getParameter("statusConfirmed");
        String statusCanceled = request.getParameter("statusCanceled");
        String statusCompleted = request.getParameter("statusCompleted");
        return PaymentRequestDto.newBuilder()
                .sorting(sorting)
                .ordering(ordering)
                .itemsOnPage(itemsOnPage)
                .numberOfPage(numberOfPage)
                .statusInProgress(statusInProgress)
                .statusConfirmed(statusConfirmed)
                .statusCanceled(statusCanceled)
                .statusCompleted(statusCompleted)
                .build();
    }

}

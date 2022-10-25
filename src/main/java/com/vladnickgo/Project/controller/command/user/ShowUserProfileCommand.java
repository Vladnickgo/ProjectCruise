package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.service.PaymentService;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;

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
        PaymentRequestDto paymentRequestDto = getPaymentRequestDto(request);
        PaymentRequestDtoUtil paymentRequestDtoUtil = new PaymentRequestDtoUtil(paymentRequestDto);
        Integer pages = paymentService.getNumberOfPagesByUserIdAndSortingParameters(paymentRequestDtoUtil);
        List<PaymentResponseDto> paymentsList = paymentService.findPaymentsByUserIdAndSortingParameters(paymentRequestDtoUtil);
        ShowAdminProfileCommand.setRequestAttributesForShowUserProfile(request, pages, paymentRequestDtoUtil, paymentsList);
        return PagesConstant.USER_PROFILE;
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
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        return PaymentRequestDto.newBuilder()
                .userId(userId)
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


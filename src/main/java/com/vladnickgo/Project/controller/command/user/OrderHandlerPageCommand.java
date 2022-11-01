package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class OrderHandlerPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("payment", getPaymentResponseDtoByRequest(request));
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        return PagesConstant.ORDER_HANDLER_PAGE;
    }

    private PaymentResponseDto getPaymentResponseDtoByRequest(HttpServletRequest request) {
        String paymentNumber = request.getParameter("paymentNumber");
        String paymentDate = request.getParameter("paymentDate");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String cruiseName = request.getParameter("cruiseName");
        String routeName = request.getParameter("routeName");
        String cabinNumber = request.getParameter("cabinNumber");
        String cabinType = request.getParameter("cabinType");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String orderStatusName = request.getParameter("orderStatusName");
        String amount = request.getParameter("amount");
        String userDocument = request.getParameter("userDocument");
        return PaymentResponseDto.newBuilder()
                .paymentNumber(Integer.valueOf(paymentNumber))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .paymentDate(LocalDate.parse(paymentDate))
                .cruiseName(cruiseName)
                .routeName(routeName)
                .cabinNumber(Integer.valueOf(cabinNumber))
                .cabinType(cabinType)
                .dateStart(LocalDate.parse(dateStart))
                .dateEnd(LocalDate.parse(dateEnd))
                .orderStatusName(orderStatusName)
                .userDocument(userDocument)
                .amount(Integer.valueOf(amount))
                .build();
    }
}

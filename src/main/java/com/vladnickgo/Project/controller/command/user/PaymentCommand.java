package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class PaymentCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PaymentService paymentService = contextInjector.getPaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer cabinStatusId = Integer.valueOf(request.getParameter("cabinStatusId"));
        Integer cruiseId = Integer.valueOf(request.getParameter("cruiseId"));
        String idCard = request.getParameter("idCard");
        Integer amount = Integer.valueOf(request.getParameter("amount"));
        String documentPath=String.format("/views/img/copyOfDocuments/%s",idCard);
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        PaymentDto paymentDto = PaymentDto.newBuilder()
                .orderDto(OrderDto.newBuilder()
                        .userId(user.getId())
                        .userDocuments(documentPath)
                        .cabinStatusId(cabinStatusId)
                        .orderDate(LocalDate.now())
                        .cruiseId(cruiseId)
                        .build())
                .paymentDate(LocalDate.now())
                .amount(amount)
                .build();
        try {
            PaymentResponseDto paymentResponseDto = paymentService.addPayment(paymentDto);
            request.getSession().setAttribute("payment", paymentResponseDto);
            return "user?command=successPaymentCommand";
        } catch (IllegalArgumentException e) {
            return "user?command=paymentCommand";
        }
    }
}

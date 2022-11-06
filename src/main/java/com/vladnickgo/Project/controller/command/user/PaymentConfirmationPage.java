package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.dto.PaymentDocumentsDto;
import com.vladnickgo.Project.service.PaymentDocumentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class PaymentConfirmationPage implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PaymentDocumentsService paymentDocumentsService = contextInjector.getPaymentDocumentsService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cruiseId = request.getParameter("cruiseId");
        String cabinTypeId = request.getParameter("cabinTypeId");
        String cruise = request.getParameter("cruise");
        String cabinStatusId = request.getParameter("cabinStatusId");
        String nights = request.getParameter("nights");
        String routeName = request.getParameter("routeName");
        String cabinTypeName = request.getParameter("cabinTypeName");
        String shipName = request.getParameter("shipName");
        LocalDate dateStart = LocalDate.parse(request.getParameter("dateStart"));
        LocalDate dateEnd = LocalDate.parse(request.getParameter("dateEnd"));
        String amount = request.getParameter("amount");
        String idCard = request.getParameter("idCard");
        String cardNumber = request.getParameter("cardNumber");
        String cvvCode = request.getParameter("cvvCode");
        String command = request.getParameter("command");
        request.setAttribute("cruiseId", cruiseId);
        request.setAttribute("cabinStatusId", cabinStatusId);
        request.setAttribute("cabinTypeId", cabinTypeId);
        request.setAttribute("cruise", cruise);
        request.setAttribute("routeName", routeName);
        request.setAttribute("nights", nights);
        request.setAttribute("cruise", cruise);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("amount", amount);
        request.setAttribute("cabinTypeName", cabinTypeName);
        request.setAttribute("shipName", shipName);
        request.setAttribute("idCard", idCard);
        request.setAttribute("command", command);
        PaymentDocumentsDto paymentDocumentsDto = PaymentDocumentsDto.newBuilder()
                .idCard(idCard)
                .cardNumber(cardNumber)
                .cvvCode(cvvCode)
                .build();
        try {
            paymentDocumentsService.checkCardData(paymentDocumentsDto);
        } catch (IllegalArgumentException e) {
            request.setAttribute("cardErrorMessage", e.getMessage());
            return PagesConstant.CREATE_ORDER;
        }
        return PagesConstant.PAYMENT_CONFIRMATION_PAGE;
    }
}

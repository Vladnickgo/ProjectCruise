package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CancelPaymentCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final PaymentService paymentService = contextInjector.getPaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer paymentId = Integer.valueOf(request.getParameter("paymentId"));
        try {
            paymentService.cancelPayment(paymentId);
        } catch (SQLException e) {
            return PagesConstant.ERROR_PAGE;
        }
        return "user?command=showAdminProfile&statusInProgress=inProgress";
    }
}

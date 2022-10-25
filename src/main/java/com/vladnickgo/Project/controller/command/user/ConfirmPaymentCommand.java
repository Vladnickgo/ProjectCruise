package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.context.ApplicationContextInjector;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmPaymentCommand implements Command {
    private final ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private final OrderService paymentService = contextInjector.getOrderService();
    private static final Logger LOGGER = Logger.getLogger(ConfirmPaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer paymentId = Integer.valueOf(request.getParameter("paymentId"));
        paymentService.confirmOrderByPaymentId(paymentId);
        LOGGER.info("Order for payment "+paymentId +" updated");
        return "user?command=showAdminProfile&statusInProgress=inProgress";
    }
}

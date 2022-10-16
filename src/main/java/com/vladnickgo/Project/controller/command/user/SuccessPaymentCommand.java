package com.vladnickgo.Project.controller.command.user;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SuccessPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesConstant.SUCCESS_PAYMENT_PAGE;
    }
}

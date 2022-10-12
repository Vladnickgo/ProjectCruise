package com.vladnickgo.Project.controller;

import com.vladnickgo.Project.PagesConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ExceptionHandler extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class);

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException/*, AuthorisationFailException */{
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");

            if (code.equals(404)) {
                LOGGER.info("HTTP Status " + code);
                req.getRequestDispatcher(PagesConstant.PAGE_NOT_FOUND).forward(req, resp);
            }

            Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
            String exceptionName = throwable.getClass().getSimpleName();
            LOGGER.info("exceptionName: " + exceptionName);

            if ("IllegalArgumentException".equals(exceptionName)) {
                LOGGER.info(throwable.getMessage());
                req.getRequestDispatcher(PagesConstant.PAGE_NOT_FOUND).forward(req, resp);
            }

            if ("AuthorisationFailException".equals(exceptionName)) {
                req.getSession().invalidate();
                LOGGER.info("AuthorisationFailException "+"HTTP Status " + code);
                req.getRequestDispatcher(PagesConstant.NOT_AUTHORIZED_USER_PAGE).forward(req,resp);
            }

            if (code.equals(500)) {
                req.setAttribute("codeOfError", code);
                LOGGER.info("HTTP Status " + code);
                req.getRequestDispatcher(PagesConstant.INTERNAL_SERVER_ERROR_PAGE).forward(req, resp);
            }
            req.getRequestDispatcher(PagesConstant.LOGIN_PAGE).forward(req, resp);
        } catch (IOException | ServletException e) {
            LOGGER.info("Internal Server Error" + e);
            resp.sendRedirect(PagesConstant.INTERNAL_SERVER_ERROR_PAGE);
        }
    }
}

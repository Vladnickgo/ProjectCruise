package com.vladnickgo.Project.controller.filter;


import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.service.impl.exception.AuthorisationFailException;
import com.vladnickgo.Project.validator.ValidatorErrorMessage;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/home/*")
public class HomeSecurityFilter implements Filter {
    public static final Logger LOGGER = Logger.getLogger(HomeSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Log from HomeSecurityFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("utf-8");
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        String command = req.getParameter("command");
        if (user == null && ("showUserProfile".equals(command)||"showAdminProfile".equals(command)||"orderHandlerPage".equals(command))){
            LOGGER.info("AuthorisationFailException("+ ValidatorErrorMessage.NOT_AVAILABLE_PAGE+")");
            throw new AuthorisationFailException(ValidatorErrorMessage.NOT_AVAILABLE_PAGE);
        }
        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}

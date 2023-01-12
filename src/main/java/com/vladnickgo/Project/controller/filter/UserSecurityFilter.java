package com.vladnickgo.Project.controller.filter;


import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.service.impl.exception.AuthorisationFailException;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.NOT_AVAILABLE_PAGE;


@WebFilter(urlPatterns = "/user/*")
public class UserSecurityFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(UserSecurityFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Log from UserSecurityFilter, method doFilter()");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        String command = request.getParameter("command");
        if (user == null && ("showUserProfile".equals(command)||"showAdminProfile".equals(command)||"orderHandlerPage".equals(command))) {
            LOGGER.info("AuthorisationFailException("+NOT_AVAILABLE_PAGE+")");
            throw new AuthorisationFailException(NOT_AVAILABLE_PAGE);
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

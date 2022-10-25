package com.vladnickgo.Project.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LocalFilter implements Filter {
    private static final String UTF_8 = "UTF-8";
    private static final String LANGUAGE = "language";
    private static final String DEFAULT_LANGUAGE = "ua";
    private static final Logger LOGGER = Logger.getLogger(LocalFilter.class);

    public LocalFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("LocalFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        servletRequest.setCharacterEncoding(UTF_8);
        servletResponse.setCharacterEncoding(UTF_8);
        servletResponse.setContentType("text/html; charset=UTF-8");
        if (req.getParameter(LANGUAGE) != null) {
            req.getSession().setAttribute(LANGUAGE, req.getParameter(LANGUAGE));
            LOGGER.info(req.getSession().getAttribute("language"));
        }
        if (req.getParameter(LANGUAGE) == null) {
            req.getSession().setAttribute(LANGUAGE, DEFAULT_LANGUAGE);
            LOGGER.info(req.getSession().getAttribute("language"));
        }
        LOGGER.info("Logging from doFilter LocalFilter");
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("LocalFilter destroyed");
    }
}

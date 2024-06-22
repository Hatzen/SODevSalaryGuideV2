package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Needed when running frontend locally on a different port. TODO Should not run in production environment.
// https://stackoverflow.com/a/59304327/8524651
@Component
public class CustomeCORSFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(CustomeCORSFilter.class);

    public CustomeCORSFilter() {
        log.info("CustomeCORSFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Max-Age", "");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With,");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
package com.blackey.common.reqlog.filter;

import com.blackey.common.mdc.MDCUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : kk
 * @version : 2019-01-03
 */

public final class ConfuciusFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDCUtils.addNewLine();
        MDCUtils.addTransactionId();
        MDCUtils.addApplicationMDC();
        MDCUtils.addServletRequestMDC(request);
        try {
            filterChain.doFilter(request, response);
        }finally {
            MDCUtils.removeNewLine();
            MDCUtils.removeTransactionId();
            MDCUtils.removeApplicationMDC();
            MDCUtils.removeServletRequestMDC();
        }
    }

}

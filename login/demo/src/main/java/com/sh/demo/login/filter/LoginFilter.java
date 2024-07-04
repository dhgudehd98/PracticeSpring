package com.sh.demo.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/app/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("log.init() - 필터를 진행합니다.");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response  = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String requestURL = request.getRequestURI();
        log.debug("Filter 시작 : {}", requestURL);

        Object loginDto = request.getSession().getAttribute("user");
        if (loginDto == null) {
            log.debug("로그인 정보가 없습니다. 접근 할 수 없는 권한입니다.");
            response.sendRedirect("/app");
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            log.info("[{}] LoginFilter doFilter End", requestURL);
        }
    }
}
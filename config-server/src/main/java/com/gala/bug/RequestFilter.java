package com.gala.bug;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1) // 指定过滤器的执行顺序
@WebFilter(filterName = "CostTimeFilter", urlPatterns = "/*" )
@Component
public class RequestFilter implements Filter {
    private String url;
    /**
     * filter对象只会创建一次，init方法也只会执行一次。
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.url = filterConfig.getInitParameter("URL");
        System.out.println("Filter init...");
    }

    /**
     * 主要的业务代码编写方法
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println(start + " Filter doFilter before...");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        System.out.println(start + "url=" +req.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        System.out.println(end + "Filter doFilter after...");
    }

    /**
     * 在销毁Filter时自动调用。
     */
    @Override
    public void destroy() {
        System.out.println("Filter destroy...");
    }
}
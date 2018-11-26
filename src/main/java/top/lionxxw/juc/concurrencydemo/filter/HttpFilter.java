package top.lionxxw.juc.concurrencydemo.filter;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.example.threadlocal.RequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * http 过滤器

 * created on 2018/4/23 16:11
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class HttpFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter: {} , {}", Thread.currentThread().getId(), request.getRequestURI());
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

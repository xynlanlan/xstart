package com.example.start.common.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

    final static Logger logger = LoggerFactory.getLogger(CorsFilter.class);
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) resp;
        // 这里最好不要写通配符，如果允许多个域请求数据的话，可以直接用逗号隔开："http://www.baidu.com,http://google.com"
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept,X-Requested-With");
        chain.doFilter(req, resp);

    }
    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void destroy() {}
}

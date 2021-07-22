package top.zang.config.bodyReader;

import top.zang.config.SignatureInterceptor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  为了多次读取 servletRequest中body数据
 */
@Component
@WebFilter(urlPatterns = {"/*"},filterName = "channelFilter")
public class ChannelFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = null;
        String requestUri = request.getRequestURI();
        if(!SignatureInterceptor.upload_paths.contains(requestUri)) { // 上传图片等不打印请求内容日志
            if (servletRequest instanceof HttpServletRequest) {
                requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
            }
        }
        if(requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }
}


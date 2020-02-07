package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/5 10:06
 */
@Component
public class WebFilter extends ZuulFilter {



    @Override
    public String filterType() {
        // 前置过滤器
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return 任何值 都表示继续执行
     * setsendzullResponse(false) 表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = currentContext.getRequest();
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            //把头信息继续往下传
            currentContext.addZuulRequestHeader("Authorization", header);
        }
        return null;
    }
}

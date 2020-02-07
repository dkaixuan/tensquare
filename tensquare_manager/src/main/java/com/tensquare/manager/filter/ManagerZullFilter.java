package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tensquare.common.constants.ResultCodeEnum;
import com.tensquare.common.exception.TensquareException;
import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/2/5 11:03
 */
public class ManagerZullFilter extends ZuulFilter {


    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }

        if (request.getRequestURI().indexOf("login")>0) {

        }

        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");

                    if (roles.equals("admin")) {
                        requestContext.addZuulRequestHeader("Authorization", header);
                        //继续执行
                        return null;
                    }
                } catch (Exception e) {
                    requestContext.setSendZuulResponse(false);
                    requestContext.setResponseStatusCode(403);
                    throw new TensquareException(ResultCodeEnum.FORWARD_ERROR);
                }








            }
        }



        return null;
    }
}

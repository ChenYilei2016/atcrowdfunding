package com.chenyilei.atcrowdfunding.main.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/23- 13:23
 */

public class HttpInterceptor implements HandlerInterceptor {
    @Override
    //http://localhost:8080/contextpath/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        request.setAttribute("ctxPath",request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

package com.chenyilei.atcrowdfunding.mymain.interceptor;

import com.chenyilei.atcrowdfunding.bean.User;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2019/01/06- 14:03
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //定义白名单
        Set set = new HashSet();
        set.add("/login.htm");
        set.add("/doLogin.do");

        if(set.contains(httpServletRequest.getServletPath())){
            return true;
        }
        //检测有无登陆
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if(user != null){
            return true;
        }else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.htm");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

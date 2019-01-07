package com.chenyilei.atcrowdfunding.mymain.interceptor;

import com.chenyilei.atcrowdfunding.common.h.Const;
import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.util.Set;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2019/01/07- 11:59
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //我具有的权限路径
        Set<String> myUrl = (Set<String>) httpServletRequest.getSession().getAttribute(Const.MY_URIS);
        //此次访问的路径
        String interUrl = httpServletRequest.getServletPath();

        if(myUrl != null && myUrl.contains(interUrl)){
            //我具有的权限 放行
            return true;
        }else{
            //需要拦截的访问路径
            Set<String> allUrl = (Set<String>) httpServletRequest.getServletContext().getAttribute(Const.ALL_PERMISSION_URI);
            if(allUrl.contains(interUrl)){
                //我不具有的权限,并且在拦截路径中
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"login.htm");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

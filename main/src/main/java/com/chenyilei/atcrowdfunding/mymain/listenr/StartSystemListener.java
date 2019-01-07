package com.chenyilei.atcrowdfunding.mymain.listenr;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.diy.AjaxHelpler;
import com.chenyilei.atcrowdfunding.common.h.Const;
import com.chenyilei.atcrowdfunding.manager.service.PermissionService;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import sun.misc.Request;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/23- 22:37
 */
public class StartSystemListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("APP_PATH",servletContext.getContextPath());
        System.out.println("监听器ok");

        //加载所有访问路径 urlList 进入application域
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        PermissionService permissionService = webApplicationContext.getBean(PermissionService.class);
        Set<String> urlList = permissionService.queryAllList().stream().map(permission -> {
            return "/"+permission.getUrl();
        }).filter(x-> !StringUtils.isEmpty(x)).collect(Collectors.toSet());

        servletContext.setAttribute(Const.ALL_PERMISSION_URI,urlList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

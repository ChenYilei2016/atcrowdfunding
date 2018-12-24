package com.chenyilei.atcrowdfunding.mymain.listenr;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

package com.chenyilei.atcrowdfunding.testm;

import com.chenyilei.atcrowdfunding.manager.controller.TestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/23- 10:38
 */

@Configuration
public class MyConfig {

    @Bean
    public String wangbadan(){
        return "1233211234567";
    }



}

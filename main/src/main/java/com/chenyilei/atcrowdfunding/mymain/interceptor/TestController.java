package com.chenyilei.atcrowdfunding.mymain.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/22- 17:20
 */

@Controller
public class TestController {

    @Autowired
    public String wangbadan;

    @RequestMapping("/test")
    public String myTest(HttpServletRequest httpServletRequest){
        
        return "success";
    }

    @RequestMapping("/test2")
    public String myTest2(){
        throw new RuntimeException("echang来了");
    }

}

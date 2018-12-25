package com.chenyilei.atcrowdfunding.mymain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String myTest(@RequestParam(value = "page",defaultValue ="1") int page, HttpServletRequest request){
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        return "success";
    }

    @RequestMapping("/test2")
    public String myTest2(){
        throw new RuntimeException("echang来了");
    }

}

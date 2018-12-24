package com.chenyilei.atcrowdfunding.mymain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 10:36
 */

@Controller
public class DispatherController {

    @RequestMapping("/index.htm")
    public String index(){
        return "index";
    }
    @RequestMapping("/login.htm")
    public String login(){
        return "login";
    }

}

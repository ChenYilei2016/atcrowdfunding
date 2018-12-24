package com.chenyilei.atcrowdfunding.mymain.controller;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 10:36
 */

@Controller
public class DispatherController {
    @Autowired
    private UserService userService ;

    @RequestMapping("/index.htm")
    public String index(){
        return "index";
    }
    @RequestMapping("/login.htm")
    public String login(){
        return "login";
    }
    @RequestMapping("/main.htm")
    public String main1(){
        return "main";
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
    //同步的登陆请求
//    @RequestMapping("/doLogin")
//    public String doLogin(User tuser, String type,HttpSession session){
//        Map<String,Object> paramMap = new HashMap<String,Object>();
//        paramMap.put("loginacct", tuser.getLoginacct());
//        paramMap.put("userpswd", tuser.getUserpswd());
//        paramMap.put("type", type);
//
//        User user = userService.queryUserlogin(paramMap);
//
//        session.setAttribute("user", user);
//
//        return "redirect:/main.htm";
//    }

    //异步登陆
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object doLogin(User tuser, @RequestParam("type") String type, HttpSession session){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("loginacct", tuser.getLoginacct());
        paramMap.put("userpswd", tuser.getUserpswd());
        paramMap.put("type", type);

        User user = userService.queryUserlogin(paramMap);
        session.setAttribute("user", user);

        if(null ==user){
            return new AjaxResult(false,"登陆失败");
        }
        return new AjaxResult(true,"登陆成功");
    }
}

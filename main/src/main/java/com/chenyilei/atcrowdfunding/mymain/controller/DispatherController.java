package com.chenyilei.atcrowdfunding.mymain.controller;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.diy.AjaxHelpler;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.common.h.Const;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.helpers.ThreadLocalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public ThreadLocal threadLocal;


    @RequestMapping("/index.htm")
    public String index(){
        return "index";
    }
    @RequestMapping("/login.htm")
    public String login(){
        return "login";
    }
    @RequestMapping("/main.htm")
    public String main1(HttpSession httpSession){

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
        try {
            //保存用户的信息进入session
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("loginacct", tuser.getLoginacct());
            paramMap.put("userpswd", tuser.getUserpswd());
            paramMap.put("type", type);
            User user = userService.queryUserlogin(paramMap);
            session.setAttribute("user", user);

            //进行菜单menu的初始化
            Integer userId = user.getId();
            //根据userid 查询出 具有的role 对应的权限
            List<Permission> permissionList =userService.queryPermissionByUserId(userId);

            Set<String> myUrl = permissionList.stream().map(x -> "/" + x.getUrl()).collect(Collectors.toSet());
            session.setAttribute(Const.MY_URIS,myUrl);

            //组合permission 得到permissionRoot
            Permission permissionRoot = AjaxHelpler.PermissionZuHe(permissionList);
            session.setAttribute("permissionRoot",permissionRoot);
        }catch (Exception e){
            return new AjaxResult(false,"登陆失败");
        }
        return new AjaxResult(true,"登陆成功");
    }
}

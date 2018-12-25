package com.chenyilei.atcrowdfunding.manager.controller;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.common.h.Page;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //异步登陆
    @RequestMapping("/index-1")
    public String index(		@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
                                @RequestParam(value="pagesize",required=false,defaultValue="5")  Integer pagesize,
                                Map<String,Object> map){
        Page<User> page = userService.queryUserList(pageno,pagesize);
        map.put("page",page);

        return "user/index-1";
    }
}

package com.chenyilei.atcrowdfunding.manager.controller;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "user/index";
    }
    @RequestMapping("/index")
    @ResponseBody
    public Object index(		@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
                                @RequestParam(value="pagesize",required=false,defaultValue="2")  Integer pagesize,
                                @RequestParam(value = "queryText",required = false,defaultValue = "") String queryText){

        Page<User> page = userService.queryUserList(pageno,pagesize,queryText);

        AjaxResult ajaxResult =new AjaxResult(true,"ok");
        ajaxResult.setPage(page);

        return ajaxResult;
    }

    @RequestMapping("/toAdd.htm")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/doAdd.do")
    @ResponseBody
    public Object doAdd(User user){
        if(1 == userService.saveUser(user)){
            return new AjaxResult(true,"添加成功");
        }
        return new AjaxResult(false,"添加失败");
    }

    @RequestMapping("/toUpdate.htm")
    public ModelAndView toUpdate(@RequestParam("id") String id,Map map){
        User user = userService.queryUserById(id);
        map.put("user",user);
        return new ModelAndView("user/update",map);
    }

    @RequestMapping(value = "/doUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public Object doUpdate(User user){
        int a = userService.updateUser(user);
        if(1 == a){
            return new AjaxResult(true,"更新成功");
        }
        return new AjaxResult(false,"更新失败");
    }
}

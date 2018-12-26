package com.chenyilei.atcrowdfunding.manager.controller;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @RequestMapping("/doDelete.do")
    @ResponseBody
    public Object doDelete(@RequestParam("id") String id){
        int a = userService.deleteUser(id);
        if(1 == a){
            return new AjaxResult(true,"删除成功");
        }
        return new AjaxResult(false,"删除失败");
    }

    /**
     *  对应的表单数据
     *  (1) "ids=1,2,3"
     *  (2){
     *      id:"...",
     *      name:"..."
     *  }
     *
     */
    @RequestMapping("/doDeleteBatch.do")
    @ResponseBody
    public Object doDeleteBatch(@RequestParam("ids") List<Integer> ids){
        boolean a = userService.deleteUsers(ids.toArray(new Integer[ids.size()]));
        if(true == a){
            return new AjaxResult(true,"删除成功");
        }
        return new AjaxResult(false,"删除失败");
    }

    /**
     *  对应的一个整体json数据字符串
     *   dataType:"json",
*        contentType: 'application/json; charset=UTF-8',
*        data : JSON.stringify(sendJson)
     */
//    static class TEMP_T{
//        List<Integer> ids;
//
//        public List<Integer> getIds() {
//            return ids;
//        }
//
//        public void setIds(List<Integer> ids) {
//            this.ids = ids;
//        }
//    }
//    @RequestMapping("/doDeleteBatch.do")
//    @ResponseBody
//    public Object doTest(@RequestBody TEMP_T t){
//        boolean a = userService.deleteUsers(t.getIds().toArray(new Integer[t.getIds().size()]));
//        if(true == a){
//            return new AjaxResult(true,"删除成功");
//        }
//        return new AjaxResult(false,"删除失败");
//    }


}

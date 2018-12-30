package com.chenyilei.atcrowdfunding.manager.controller;

import com.chenyilei.atcrowdfunding.bean.Role;
import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.common.vo.Data;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 10:36
 */

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/index")
    public String toIndex(){
        return "user/index";
    }
    @RequestMapping("/doIndex")
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
//    @RequestMapping("/doDeleteBatch.do")
//    @ResponseBody
//    public Object doDeleteBatch(@RequestParam("ids") List<Integer> ids){
//        boolean a = userService.deleteUsers(ids.toArray(new Integer[ids.size()]));
//        if(true == a){
//            return new AjaxResult(true,"删除成功");
//        }
//        return new AjaxResult(false,"删除失败");
//    }

    /**
     *  对应的一个整体json数据字符串
     *   dataType:"json",
*        contentType: 'application/json; charset=UTF-8',
*        data : JSON.stringify(sendJson)
     */

    @RequestMapping("/doDeleteBatch.do")
    @ResponseBody
    public Object doTest(@RequestBody Data t){
        List<Integer> listId= t.getUsers().stream().map(x->x.getId()).collect(Collectors.toList());
        boolean a = userService.deleteUsers(listId.toArray(new Integer[listId.size()]));
        if(true == a){
            return new AjaxResult(true,"删除成功");
        }
        return new AjaxResult(false,"删除失败");
    }


    @RequestMapping("/assignRole")
    public String assignRole(@RequestParam("id")Integer id,Map map){
        List<Role> leftRoleList = new ArrayList<>();
        List<Role> rightRoleList = new ArrayList<>();

        //找到所有 Role
        List<Role> allRoleList = userService.queryAllRoleList();
        //已分配的role
        List<Integer> rightIds  = userService.queryRightRoleIds(id);

        for(Role temp : allRoleList){
            if(rightIds.contains(temp.getId())){
                rightRoleList.add(temp);
            }
            //未分配的role
            else{
                leftRoleList.add(temp);
            }
        }
        map.put("leftRoleList",leftRoleList);
        map.put("rightRoleList",rightRoleList);

        return "user/assignRole";
    }

    //分配角色
    @ResponseBody
    @RequestMapping("/doAssignRole")
    public Object doAssignRole(Integer userid,@RequestParam("ids[]")List<Integer> ids){
        AjaxResult result = new AjaxResult();
        try {
            userService.saveUserRoleRelationship(userid,ids);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("分配角色数据失败!");
        }
        return result;
    }
    //取消分配角色
    @ResponseBody
    @RequestMapping("/doUnAssignRole")
    public Object doUnAssignRole(Integer userid,@RequestParam("ids[]")List<Integer> ids){
        AjaxResult result = new AjaxResult();
        try {
            userService.deleteUserRoleRelationship(userid,ids);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("取消分配角色数据失败!");
        }
        return result;
    }
}

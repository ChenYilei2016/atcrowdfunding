package com.chenyilei.atcrowdfunding.mymain.controller;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.common.diy.AjaxHelpler;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 许可相关的维护:
 *    许可树数据的导入
 *
 * @author chenyilei
 * @date 2018/12/30- 17:18
 */

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/index.htm")
    public String index(){
        return "permission/index";
    }


//    [{
//        "id": 1,
//                "pid": 0,
//                "seqno": 0,
//                "name": "系统权限菜单",
//                "url": null,
//                "icon": "fa fa-sitemap",
//                "open": true,
//                "checked": false,
//                "children": [{
//            "id": 2,
//                    "pid": 1,
//                    "seqno": 0,
//                    "name": "控制面板",
//                    "url": "dashboard.htm",
//                    "icon": "fa fa-desktop",
//                    "open": true,
//                    "checked": false,
//                    "children": []
//        }]

    /**
     * 导入 递归树的数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadData.do",method = RequestMethod.POST)
    public AjaxResult loadData(){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            /**
             * 也可以不用递归 将每一个结点都遍历找好子结点 串起来就行了
             * <code>
             * for(遍历每一个结点){
             *      temp.children =  查出他的子节点变成List
             * }
             * </code>
             */

            /**
             * 还可以遍历每一个结点
             * 找到父节点 将自己设置进父节点的children 这样就可以构造一个 Id_permission 的Map了
             */
            List<Permission> root = new ArrayList<>();
            //查出所有的permission 减少数据库压力
            List<Permission> allList = permissionService.queryAllList();
            Permission myroot = allList.stream().filter(x -> x.getPid() == -1).collect(Collectors.toList()).get(0);
            root.add(myroot);
            /**
             * 递归  组合成树
             * {@link #zuHe(Permission, List)}
             */
            zuHe(myroot,allList);
            ajaxResult.setData(root);
        }catch (Exception e){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("加载树数据异常");
            e.printStackTrace();
        }
        return ajaxResult;
    }
    private void zuHe(Permission permission,List<Permission> allList ){
        for(Permission tempPermission : allList){
            if( permission.getId() == tempPermission.getPid() ){
                if(permission.getChildren() == null){
                    List list = new ArrayList();
                    list.add(tempPermission);
                    permission.setChildren(list);
                }else{
                    permission.getChildren().add(tempPermission);
                }
                zuHe(tempPermission,allList);
            }
        }
    }

    @RequestMapping("/toAdd.htm")
    public String toAdd(){
        return "permission/add";
    }

//    @RequestMapping("/doAdd.do")
//    @ResponseBody
//    public AjaxResult doAdd(Permission permission){
//        AjaxResult ajaxResult = new AjaxResult();
//        //保存 许可关系
//        try {
//            permissionService.addPermission(permission);
//        }catch (Exception e){
//            ajaxResult.setSuccess(false);
//            ajaxResult.setMessage("保存许可树信息失败");
//        }
//        return ajaxResult;
//    }
    @RequestMapping("/doAdd.do")
    @ResponseBody
    public Object doAdd(Permission permission){
        AjaxHelpler.start();
        //保存 许可关系
        try {
            permissionService.addPermission(permission);
        }catch (Exception e){
            AjaxHelpler.errorMessage("保存许可树信息失败");
            AjaxHelpler.success(false);
        }
        return AjaxHelpler.end();
    }
}

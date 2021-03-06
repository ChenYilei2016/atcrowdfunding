package com.chenyilei.atcrowdfunding.common.diy;

import com.chenyilei.atcrowdfunding.bean.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --封装 返回数据格式的工具
 *   方便了操作 使用方法:
 *   <code>
 *       {@link #start()} 用于一次请求的初始化
 *       {@link #end()} 用于返回json或其他格式的数据
 *       其余方法访问用于 增加返回参数
 *   </code>
 * --
 *
 * @author chenyilei
 * @date 2019/01/01- 10:03
 */
public class AjaxHelpler {
    private static final ThreadLocal<Map<String,Object>> result = new ThreadLocal<>();

    public static void start(){
        result.set(new HashMap<String,Object>());
        result.get().put("success",true); //默认为true
    }

    public static void errorMessage(String message){
        result.get().put("message",message);
    }

    public static void success(boolean success){
        result.get().put("success",success);
    }

    public static void elseObject(String property,Object obj){
        result.get().put(property,obj);
    }

    public static Object end(){
        return result.get();
    }

    public static Permission PermissionZuHe( List<Permission> permissionList) {
        //组合permission 得到permissionRoot
        Permission permissionRoot = null;
        Map map = new HashMap();
        for (Permission temp : permissionList) {
                map.put(temp.getId(), temp);
        }
        for (Permission temp : permissionList) {
            if (temp.getPid() == -1) {
                permissionRoot = temp;
            } else {
                Permission parent = (Permission) map.get(temp.getPid());
                if(parent.getChildren() == null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(temp);
            }
        }
        return permissionRoot;
    }
}

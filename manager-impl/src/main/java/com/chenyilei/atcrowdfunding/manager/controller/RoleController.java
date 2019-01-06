package com.chenyilei.atcrowdfunding.manager.controller;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.bean.Role;
import com.chenyilei.atcrowdfunding.common.h.AjaxResult;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.common.h.StringUtil;
import com.chenyilei.atcrowdfunding.common.vo.Data;
import com.chenyilei.atcrowdfunding.manager.service.PermissionService;
import com.chenyilei.atcrowdfunding.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}

	
	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}

	@RequestMapping("/assignPermission")
	public String assignPermission() {
		return "role/assignPermission";
	}

    @ResponseBody
    @RequestMapping("/doAssignPermission")
    public Object doAssignPermission(Integer roleid, Data datas){
        AjaxResult result = new AjaxResult();
        try {
            int count = roleService.saveRolePermissionRelationship(roleid,datas);
            result.setSuccess(count==datas.getIds().size());
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

	@ResponseBody
	@RequestMapping("/loadDataAsync")
	public Object loadDataAsync(Integer roleid){

		List<Permission> root = new ArrayList<Permission>();

		List<Permission> childredPermissons =  permissionService.queryAllList();


		//根据角色id查询该角色之前所分配过的许可.
		List<Integer> permissonIdsForRoleid = permissionService.queryPermissionidsByRoleid(roleid);


		Map<Integer,Permission> map = new HashMap<Integer,Permission>();//100

		for (Permission innerpermission : childredPermissons){
			map.put(innerpermission.getId(), innerpermission);
			if(permissonIdsForRoleid.contains(innerpermission.getId())){
                    innerpermission.setChecked(true);
			}
		}


		for (Permission permission : childredPermissons) { //100
			//通过子查找父
			//子菜单
			Permission child = permission ; //假设为子菜单
			if(child.getPid() == -1 ){  //这里我自己把数据库 根结点的父节点改成了-1
				root.add(permission);
			}else{
				//父节点
				Permission parent = map.get(child.getPid());
				if(parent.getChildren() == null){
				    parent.setChildren(new ArrayList<>());
                }
				parent.getChildren().add(child);
			}
		}
		return root ;
	}
    /**
     * 异步分页查询
     * @param pageno
     * @param pagesize
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery(String queryText,
                            @RequestParam(required = false, defaultValue = "1") Integer pageno,
                            @RequestParam(required = false, defaultValue = "2") Integer pagesize){
        AjaxResult result = new AjaxResult();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("pageno", pageno); // 空指针异常
            paramMap.put("pagesize", pagesize);

            if(StringUtil.isNotEmpty(queryText)){
                queryText = queryText.replaceAll("%", "\\\\%"); //斜线本身需要转译
                System.out.println("--------------"+queryText);
            }

            paramMap.put("queryText", queryText);

            // 分页查询数据
            Page<Role> rolePage = roleService.pageQuery(paramMap);

            result.setPage(rolePage);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return  result;
    }
}

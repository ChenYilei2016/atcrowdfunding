package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.Role;
import com.chenyilei.atcrowdfunding.bean.RolePermission;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.common.vo.Data;
import com.chenyilei.atcrowdfunding.manager.dao.RoleMapper;
import com.chenyilei.atcrowdfunding.manager.dao.RolePermissionMapper;
import com.chenyilei.atcrowdfunding.manager.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2019/01/05- 19:58
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

//     paramMap.put("pageno", pageno); // 空指针异常
//            paramMap.put("pagesize", pagesize);
//
//            if(StringUtil.isNotEmpty(queryText)){
//        queryText = queryText.replaceAll("%", "\\\\%"); //斜线本身需要转译
//        System.out.println("--------------"+queryText);
//    }
//
//            paramMap.put("queryText", queryText); String
    @Override
    public Page<Role> pageQuery(Map<String, Object> paramMap) {
        PageHelper.startPage((int)paramMap.get("pageno"),(int)paramMap.get("pagesize"));

        return new Page<>(new PageInfo(roleMapper.selectAll()));
    }

    @Override
    public int saveRolePermissionRelationship(Integer roleid, Data datas) {
        //将原先删光
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleid(roleid);
        rolePermissionMapper.delete(rolePermission);

        //将datas中的ids 全部再添加
        List<RolePermission> collect = datas.getIds().stream().map(x -> {
            RolePermission rolePermission1 = new RolePermission();
            rolePermission1.setRoleid(roleid);
            rolePermission1.setPermissionid(x);
            return rolePermission1;
        }).collect(Collectors.toList());

        return rolePermissionMapper.insertList(collect);

    }
}

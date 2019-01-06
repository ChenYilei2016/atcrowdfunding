package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.bean.RolePermission;
import com.chenyilei.atcrowdfunding.manager.dao.PermissionMapper;
import com.chenyilei.atcrowdfunding.manager.dao.RolePermissionMapper;
import com.chenyilei.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/30- 17:20
 */

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> queryAllList() {
        return permissionMapper.selectAll();
    }

    @Override
    public void addPermission(Permission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleid(roleid);
        return rolePermissionMapper.select(rolePermission).stream().map(x->x.getId()).collect(Collectors.toList());
    }
}

package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.manager.dao.PermissionMapper;
import com.chenyilei.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Permission> queryAllList() {
        return permissionMapper.selectAll();
    }

    @Override
    public void addPermission(Permission permission) {
        permissionMapper.insertSelective(permission);
    }
}

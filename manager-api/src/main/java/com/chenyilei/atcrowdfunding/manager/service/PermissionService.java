package com.chenyilei.atcrowdfunding.manager.service;

import com.chenyilei.atcrowdfunding.bean.Permission;

import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/30- 17:19
 */
public interface PermissionService {

    List<Permission> queryAllList();

    void addPermission(Permission permission);
}

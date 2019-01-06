package com.chenyilei.atcrowdfunding.manager.service;

import com.chenyilei.atcrowdfunding.bean.Role;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.common.vo.Data;

import java.util.Map;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2019/01/05- 19:58
 */
public interface RoleService {
    Page<Role> pageQuery(Map<String, Object> paramMap);

    int saveRolePermissionRelationship(Integer roleid, Data datas);
}

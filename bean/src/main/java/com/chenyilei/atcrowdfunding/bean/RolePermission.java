package com.chenyilei.atcrowdfunding.bean;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_role_permission")
public class RolePermission {
    @Id
    private Integer id;

    private Integer roleid;

    private Integer permissionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}
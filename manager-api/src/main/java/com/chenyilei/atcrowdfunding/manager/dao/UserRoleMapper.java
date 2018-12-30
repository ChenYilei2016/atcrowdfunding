package com.chenyilei.atcrowdfunding.manager.dao;

import com.chenyilei.atcrowdfunding.bean.UserRole;
import com.chenyilei.atcrowdfunding.common.ann.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRole> {

    void saveUserRoleRelationship(@Param("userid") Integer userid, @Param("ids") List<Integer> ids);

}
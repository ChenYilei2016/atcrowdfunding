package com.chenyilei.atcrowdfunding.manager.dao;

import com.chenyilei.atcrowdfunding.bean.Permission;
import com.chenyilei.atcrowdfunding.bean.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> , DeleteByIdListMapper<User,Integer>, InsertListMapper<User> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(User record);
//
//    User selectByPrimaryKey(Integer id);
//
//    List<User> selectAll();
//
//    int updateByPrimaryKey(User record);

	User queryUserlogin(Map<String, Object> paramMap);

	int DD(String id);

	@Select("SELECT DISTINCT tp.* " +
			"FROM t_user_role `ur` " +
			"JOIN t_role_permission `rp` ON ur.roleid = rp.roleid " +
			"JOIN t_permission `tp` ON tp.id = rp.permissionid " +
			"WHERE userid = #{userId} order by tp.id")
	List<Permission> queryPermissionByUserId(Integer userId);
}
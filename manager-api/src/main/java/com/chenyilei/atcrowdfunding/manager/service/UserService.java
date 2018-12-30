package com.chenyilei.atcrowdfunding.manager.service;

import com.chenyilei.atcrowdfunding.bean.Role;
import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.Page;

import java.util.List;
import java.util.Map;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 12:57
 */
public interface UserService {

    User queryUserlogin(Map<String, Object> paramMap);

    Page<User> queryUserList(Integer pageno, Integer pagesize, String queryText);

    int saveUser(User user);

    User queryUserById(String id);

    int updateUser(User user);

    int deleteUser(String id);

    boolean deleteUsers(Integer[] id);

    List<Role> queryAllRoleList();

    List<Integer> queryRightRoleIds(Integer id);

    void saveUserRoleRelationship(Integer userid, List<Integer> ids);

    void deleteUserRoleRelationship(Integer userid, List<Integer> ids);
}

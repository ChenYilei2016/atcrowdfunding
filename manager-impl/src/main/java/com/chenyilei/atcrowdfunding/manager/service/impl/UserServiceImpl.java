package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.*;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.dao.RoleMapper;
import com.chenyilei.atcrowdfunding.manager.dao.RolePermissionMapper;
import com.chenyilei.atcrowdfunding.manager.dao.UserMapper;
import com.chenyilei.atcrowdfunding.manager.dao.UserRoleMapper;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 12:57
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public User queryUserlogin(Map<String, Object> paramMap) {

        User user = userMapper.queryUserlogin(paramMap);

        if(user==null){
            return null;
//            throw new LoginFailException("用户账号或密码不正确!");
        }
        return user;
    }

    @Override
    public Page<User> queryUserList(Integer pageno, Integer pagesize, String queryText) {
        //分页
        PageHelper.startPage(pageno,pagesize);

        //根据查询字段 模糊匹配
        //如果有查询字段 加入模糊查询 "username","%"+queryText+"%" =>  concat("","","") 这样连接
        Example example = new Example(User.class);
        if(!StringUtils.isEmpty(queryText)){
//            斜线本身很骚!!!!   java 第一次转义 变成  \\%  再用replace 正则表达式 \\% --> \%  ->在sql中代表 %
            queryText = queryText.replaceAll("%","\\\\%");
            example.createCriteria().andLike("loginacct","%"+queryText+"%").orLike("username","%"+queryText+"%");
        }
        example.setOrderByClause("createtime desc");
        List<User> users = userMapper.selectByExample(example);

        PageInfo info = new PageInfo(users);
        Page<User> result = new Page<>(info);

        return result;
    }

    @Override
    public int saveUser(User user) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setCreatetime( s.format(new Date()));
        user.setUserpswd("root");
        return userMapper.insertSelective(user);
    }

    @Override
    public User queryUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean deleteUsers(Integer[] id) {
        return userMapper.deleteByIdList(Arrays.asList(id)) == id.length;
    }

    @Override
    public List<Role> queryAllRoleList() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Integer> queryRightRoleIds(Integer id) {
        UserRole userRole = new UserRole();
        userRole.setUserid(id);
        return userRoleMapper.select(userRole).stream().map(x->x.getRoleid()).collect(Collectors.toList());
    }

    @Override
    public void saveUserRoleRelationship(Integer userid, List<Integer> ids) {
        userRoleMapper.saveUserRoleRelationship(userid,ids);
    }

    @Override
    public void deleteUserRoleRelationship(Integer userid, List<Integer> ids) {
        //删除 是userid 且 包含在ids 中的 roleid
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo("userid",userid).andIn("roleid",ids);
        userRoleMapper.deleteByExample(example);
    }

    @Override
    public List<Permission> queryPermissionByUserId(Integer userId) {
        /**
         *  根据userid 查找 role的全部id
         *  根据roleId 查找 permission
         */
        return userMapper.queryPermissionByUserId(userId);
    }
}

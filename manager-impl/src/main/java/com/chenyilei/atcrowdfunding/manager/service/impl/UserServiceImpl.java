package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.dao.UserMapper;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Page<User> queryUserList(Integer pageno, Integer pagesize) {
        PageHelper.startPage(pageno,pagesize);
        List<User> users = userMapper.selectAll();
        PageInfo info = new PageInfo(users);

        Page<User> result = new Page<>(info);

        return result;
    }
}

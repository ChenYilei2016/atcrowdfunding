package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.dao.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/25- 13:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/spring-context.xml"})
public class UserServiceImplTest1 {
    @Autowired
    private UserMapper userMapper ;

    @Test
    public void queryUserList() {
        PageHelper.startPage(0,2);
        List<User> users = userMapper.selectAll();
        Page<User> result = new Page<>();
        PageInfo info = new PageInfo(users);

        System.out.println(result);
    }
}

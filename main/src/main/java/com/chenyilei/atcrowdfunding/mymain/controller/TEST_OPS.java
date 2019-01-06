package com.chenyilei.atcrowdfunding.mymain.controller;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/25- 13:11
 */

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/25- 13:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/spring-context.xml"})
public class TEST_OPS {
    @Autowired
    private UserMapper userMapper ;

    /**
     * 为数据库添加数据 测试
     */
    @Test
    public void ADD_USER(){
        List list = new ArrayList();
        for(int i = 100;i<200;i++){
            User user = new User();
            user.setLoginacct("user:"+i);
            user.setUserpswd("root");
            user.setCreatetime("2018-12-26 17:38:22");
            user.setEmail("7777");
            user.setUsername("user:"+i);
            list.add(user);
        }
        userMapper.insertList(list);
    }

    /**
     * 测试分页 以及相关数据的显示结果
     */
    @Test
    public void queryUserList() {
        PageHelper.startPage(2,2);
        List<User> users = userMapper.selectAll();
        Page<User> result = new Page<>();
        PageInfo info = new PageInfo(users);
        info.setNavigatePages(2);

        System.err.println("总页数:"+info.getPages());
        System.err.println("当前页数:"+info.getPageNum());
        System.err.println("总条数:"+info.getTotal());

        info.getList().forEach(System.err::println);

        System.err.println("数据"+users);
        System.err.println(info.getNavigateFirstPage());
        System.err.println(info.getNavigateLastPage());
        System.err.println(info.getNavigatePages());
    }
}

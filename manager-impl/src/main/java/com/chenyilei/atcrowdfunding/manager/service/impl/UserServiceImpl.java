package com.chenyilei.atcrowdfunding.manager.service.impl;

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.dao.UserMapper;
import com.chenyilei.atcrowdfunding.manager.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

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
        List<User> users = userMapper.selectByExample(example);

        PageInfo info = new PageInfo(users);
        Page<User> result = new Page<>(info);

        return result;
    }
}

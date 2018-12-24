package com.chenyilei.atcrowdfunding.manager.service;

import com.chenyilei.atcrowdfunding.bean.User;

import java.util.Map;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 12:57
 */
public interface UserService {

    User queryUserlogin(Map<String, Object> paramMap);
}

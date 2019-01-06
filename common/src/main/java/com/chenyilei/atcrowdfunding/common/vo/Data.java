package com.chenyilei.atcrowdfunding.common.vo;

import com.chenyilei.atcrowdfunding.bean.User;

import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/27- 14:09
 */
@lombok.Data
public class Data {
    private List<User> users;

    //给角色role 分配权限
    private List<Integer> ids;
}

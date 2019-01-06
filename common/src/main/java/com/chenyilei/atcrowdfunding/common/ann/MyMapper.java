package com.chenyilei.atcrowdfunding.common.ann;

import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/30- 11:10
 */
public interface MyMapper<T> extends Mapper<T>, InsertListMapper<T>, SelectByIdListMapper<T,Integer> , DeleteByIdListMapper<T,Integer> {

}

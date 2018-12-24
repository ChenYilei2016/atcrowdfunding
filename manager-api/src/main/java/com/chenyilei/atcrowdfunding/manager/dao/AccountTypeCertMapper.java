package com.chenyilei.atcrowdfunding.manager.dao;

import com.chenyilei.atcrowdfunding.bean.AccountTypeCert;
import java.util.List;

public interface AccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountTypeCert record);

    AccountTypeCert selectByPrimaryKey(Integer id);

    List<AccountTypeCert> selectAll();

    int updateByPrimaryKey(AccountTypeCert record);
}
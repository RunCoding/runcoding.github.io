package com.runcoding.dao.account;

import com.runcoding.pojo.po.account.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**账号 DAO 接口类*/
public interface AccountDao {


    /** 根据账号ID，获取账号*/
    Account findById(@Param("id") Long id);

    /**保存账号*/
    Long saveByAccount(Account city);

}

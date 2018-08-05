package com.runcoding.service;

import com.runcoding.pojo.po.account.Account;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface AccountService {

    /** 根据账号ID，获取账号*/
    Account findAccountById(Long id);

    /**保存账号*/
    boolean saveByAccount(Account account);
}

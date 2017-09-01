package com.runcoding.service.impl;

import com.runcoding.dao.account.AccountDao;
import com.runcoding.pojo.po.account.Account;
import com.runcoding.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号业务逻辑实现类
 * */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /** 根据账号ID，获取账号*/
    @Override
    public Account findAccountById(Long id) {
        return accountDao.findById(id);
    }

    /**保存账号*/
    @Override
    public boolean saveByAccount(Account account) {
        return accountDao.saveByAccount(account)>0;
    }


}

package com.runcoding.service.account;

import com.runcoding.model.po.account.AccountPo;


/**
 * @author xukai
 * @Date 2018-01-02 17:22:57
 */
public interface AccountService {
    
    /**
     * @author xukai
     * @Date 2018-01-02 17:22:57
     */
    AccountPo select(Long id);

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:22:57
     */
    int insert(AccountPo po);

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:22:57
     */
    int update(AccountPo po);

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:22:57
     */
    int delete(AccountPo po);
}
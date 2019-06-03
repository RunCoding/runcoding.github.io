package com.runcoding.dao.account;

import com.runcoding.model.po.account.AccountPo;
import org.apache.ibatis.annotations.Param;


/**
 * @author xukai
 * @Date 2018-01-02 17:22:57
 */
public interface AccountMapper {
    
    /**
     * @author xukai
     * @Date 2018-01-02 17:22:57
     */
    AccountPo select(@Param("id") Long id);

    
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
package com.runcoding.service.support;

import com.runcoding.model.po.account.AccountPo;
import com.runcoding.model.po.order.OrderPo;
import com.runcoding.service.account.AccountService;
import com.runcoding.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xukai
 * @email: runcoding@163.com
 * @created Time: 2018/1/2 17:32
 * @description 订单业务支持
 * Copyright (C), 2017-2018,
 **/
@Service
public class OrderSupportService {

    private Logger  logger = LoggerFactory.getLogger(OrderSupportService.class);

    @Autowired
    private AccountService accountService;


    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    public  void createOrder(OrderPo orderPo){
        int insertOrder = orderService.insert(orderPo);
        logger.info("insertOrder="+insertOrder);
        AccountPo account = AccountPo.builder().username("xukai").isDiscarded(1).build();
        int insertAccount = accountService.insert(account);
        logger.info("insertAccount="+insertAccount);
        String i = null;
        i.length();
    }


}

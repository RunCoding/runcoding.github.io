package com.runcoding.controller;

import com.runcoding.dao.account.AccountMapper;
import com.runcoding.model.po.account.AccountPo;
import com.runcoding.model.po.order.OrderPo;
import com.runcoding.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: xukai
 * @email: runcoding@163.com
 * @created Time: 2018/1/2 17:42
 * @description 订单服务
 **/
@RestController()
public class OrderController {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OrderService orderService;




    @PostMapping(value = "/get")
    @ResponseBody
    public OrderPo  getOrder(){
        //OrderPo orderPo    = orderService.select(1L);
        List<OrderPo> list = orderService.all();
       // AccountPo account  = accountMapper.select(1L);
        return null;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public OrderPo createOrder(@RequestBody OrderPo order){
        orderService.insert(order);
        return order;
    }

}

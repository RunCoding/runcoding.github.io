package com.runcoding.controller;

import com.alibaba.fastjson.JSON;
import com.runcoding.model.po.order.OrderPo;
import com.runcoding.service.support.OrderSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: xukai
 * @email: runcoding@163.com
 * @created Time: 2018/1/2 17:42
 * @description 订单服务
 **/
@RestController(value = "/order")
public class OrderController {

    @Autowired
    private OrderSupportService orderSupportService;

    @PostMapping(value = "/add")
    @ResponseBody
    public OrderPo createOrder(@RequestBody OrderPo order){

        //orderSupportService.createOrder(order);
        return order;
    }

}

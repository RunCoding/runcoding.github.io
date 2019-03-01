package com.runcoding.controller;

import com.runcoding.model.po.order.OrderPo;
import com.runcoding.service.order.OrderService;
import com.runcoding.service.support.OrderSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/get")
    @ResponseBody
    public OrderPo  getOrder(){
        OrderPo orderPo = orderService.select(1L);
        return orderPo;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public OrderPo createOrder(@RequestBody OrderPo order){
        orderService.insert(order);
        return order;
    }

}

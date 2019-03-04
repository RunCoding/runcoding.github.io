package com.runcoding.controller;

import com.runcoding.dao.order.OrderMapper;
import com.runcoding.model.po.order.OrderPo;
import com.runcoding.service.support.OrderSupportService;
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
    private OrderSupportService orderSupportService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping(value = "/get")
    @ResponseBody
    public OrderPo  getOrder(){
        OrderPo orderPo = orderMapper.select(1L);
        List<OrderPo> list = orderMapper.all();
        return orderPo;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public OrderPo createOrder(@RequestBody OrderPo order){
        orderMapper.insert(order);
        return order;
    }

}

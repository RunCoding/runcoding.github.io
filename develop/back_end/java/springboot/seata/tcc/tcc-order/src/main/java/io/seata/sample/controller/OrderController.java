package io.seata.sample.controller;

import io.seata.sample.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/create", produces = "application/json")
    public Boolean create(String userId, String commodityCode, Integer count) {
        return orderService.create(userId, commodityCode, count);
    }

    @PostMapping(value = "/commit", produces = "application/json")
    public Boolean commit(String userId, String commodityCode) {
        return orderService.commit(userId, commodityCode);
    }

    @DeleteMapping(value = "/rollback", produces = "application/json")
    public Boolean rollback(String userId, String commodityCode) {
        return orderService.rollback(userId, commodityCode);
    }

}

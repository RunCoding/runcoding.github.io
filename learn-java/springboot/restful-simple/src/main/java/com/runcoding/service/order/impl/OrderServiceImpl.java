package com.runcoding.service.order.impl;

import com.runcoding.dao.order.OrderMapper;
import com.runcoding.model.po.order.OrderPo;
import com.runcoding.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author xukai
 * @Date 2018-01-02 17:23:57
 */
@Service
public class OrderServiceImpl implements OrderService {

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:23:57
     */
    @Autowired
    private OrderMapper orderMapper;

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:23:57
     */
    @Override
    public OrderPo select(Long id) {
        return orderMapper.select(id);
    }

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:23:57
     */
    @Override
    @Transactional()
    public int insert(OrderPo po) {
        return orderMapper.insert(po);
    }

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:23:57
     */
    @Override
    public int update(OrderPo po) {
        return orderMapper.update(po);
    }

    
    /**
     * @author xukai
     * @Date 2018-01-02 17:23:57
     */
    @Override
    public int delete(OrderPo po) {
        return orderMapper.delete(po);
    }
}
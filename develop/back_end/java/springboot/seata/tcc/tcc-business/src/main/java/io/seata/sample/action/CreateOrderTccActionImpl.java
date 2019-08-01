package io.seata.sample.action;

import com.alibaba.fastjson.JSON;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.sample.entity.OrderDto;
import io.seata.sample.feign.OrderFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xukai
 * @date 2019-08-01
 * @desc: 创建订单TCC
 */
@Service
@Slf4j
public class CreateOrderTccActionImpl implements  CreateOrderTccAction {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Override
    public boolean prepare(BusinessActionContext actionContext, OrderDto order,
                           String userId, String commodityCode)  {
        String xid = actionContext.getXid();
        log.info("订单预创建Order prepare, xid={}, commodityCode:={}" ,xid, commodityCode);
        orderFeignClient.create(order.getUserId(), order.getCommodityCode(), order.getCount());
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Map<String, Object> context = actionContext.getActionContext();
        log.info("订单确认创建OrderOrder commit, xid:" + xid + ", context={}", JSON.toJSONString(context) );

        String userId = (String) actionContext.getActionContext("userId");
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        boolean commit = orderFeignClient.commit(userId, commodityCode);
        return commit;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Map<String, Object> context = actionContext.getActionContext();
        log.error("订单回滚OrderOrder rollback, xid:" + xid + ", context={}", JSON.toJSONString(context) );

        String userId = (String) actionContext.getActionContext("userId");
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        boolean rollback = orderFeignClient.rollback(userId, commodityCode);
        return rollback;
    }
}

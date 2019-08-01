package io.seata.sample.action;

import com.alibaba.fastjson.JSON;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.sample.feign.StorageFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xukai
 * @date 2019-08-01
 * @desc: 扣库存tcc
 */
@Service
@Slf4j
public class StorageTccActionImpl implements StorageTccAction{

    @Autowired
    private StorageFeignClient storageFeignClient;

    @Override
    public boolean prepare(BusinessActionContext actionContext,   String commodityCode, int count) {
        String xid = actionContext.getXid();
        log.info("库存预创建Storage prepare, xid:" + xid +  ", commodityCode={},orderCount:={}" ,commodityCode, count);
        storageFeignClient.deduct(commodityCode, count);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        /**业务特性，第一阶段预占用已经完成，此处无需处理业务*/
        log.info("库存提交Storage commit, xid:" + xid + ", context={}" , JSON.toJSONString(actionContext.getActionContext()));
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        log.error("库存回滚预占用 rollback, xid:" + xid + ", context={}" , JSON.toJSONString(actionContext.getActionContext()));
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        int count = (Integer) actionContext.getActionContext("count");
        boolean rollback = storageFeignClient.rollback(commodityCode, count);
        return rollback;
    }
}

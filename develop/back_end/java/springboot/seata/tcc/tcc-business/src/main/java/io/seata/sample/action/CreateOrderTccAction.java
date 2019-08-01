package io.seata.sample.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import io.seata.sample.entity.OrderDto;

/**
 * @author xukai
 * @date 2019-08-01
 * @desc: 订单创建tcc
 */
@LocalTCC
public interface CreateOrderTccAction {

    /**
     * Prepare boolean.
     * 资源预占用
     */
    @TwoPhaseBusinessAction(name = "CreateOrderTccAction" , commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext, OrderDto order,
                    @BusinessActionContextParameter(paramName = "userId") String userId,
                    @BusinessActionContextParameter(paramName = "commodityCode") String commodityCode);

    /**
     * Commit boolean.
     * 资源确认占用
     */
    boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     * 出现异常，回滚预占用的资源
     */
    boolean rollback(BusinessActionContext actionContext);

}

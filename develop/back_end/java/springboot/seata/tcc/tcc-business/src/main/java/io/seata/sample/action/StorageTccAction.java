package io.seata.sample.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author xukai
 * @date 2019-08-01
 * @desc: 扣库存tcc
 */
@LocalTCC
public interface StorageTccAction {

    /**
     * Prepare boolean.
     * 资源预占用
     */
    @TwoPhaseBusinessAction(name = "StorageTccAction" , commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext,
                    @BusinessActionContextParameter(paramName = "commodityCode") String commodityCode,
                    @BusinessActionContextParameter(paramName = "count")int count);

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

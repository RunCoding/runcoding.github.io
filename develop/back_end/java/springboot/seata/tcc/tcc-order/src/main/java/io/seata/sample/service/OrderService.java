package io.seata.sample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean create(String userId, String commodityCode, Integer count) {
        int orderMoney = count * 100;
        jdbcTemplate.update("insert order_tbl(user_id,commodity_code,count,money) values(?,?,?,?)",
            new Object[] {userId, commodityCode, count, orderMoney});
        log.info("create order commodityCode ={}",commodityCode);
        return  true;
    }

    public boolean commit(String userId, String commodityCode){
        log.info("幂等,允许不处理任何业务,commodityCode={}",commodityCode);
        return true;
    }

    public boolean rollback(String userId, String commodityCode){
        int update = jdbcTemplate.update("delete from order_tbl where user_id=? and commodity_code = ?",
                new Object[]{userId, commodityCode});
        if(update == 0){
            log.warn("幂等,允许空回滚");
            return  true;
        }
        log.info("delete order commodityCode={},isdel={}",commodityCode,update);
        return  true;
    }

}

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
public class StorageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean deduct(String commodityCode, int count) {
        int deduct = jdbcTemplate.update("update storage_tbl set count = count - ? where commodity_code = ?",
                new Object[]{count, commodityCode});
        log.info("扣库存,commodityCode={},count={}",commodityCode,count);
        return deduct > 0;
    }

    public boolean rollback(String commodityCode, int count) {
        /**通过扣库存日志表来处理幂等，回滚扣库存。下面代码会有幂等问题*/
        int deduct = jdbcTemplate.update("update storage_tbl set count = count + ? where commodity_code = ?",
                new Object[]{count, commodityCode});
        log.info("归还库存,commodityCode={},count={}",commodityCode,count);
        return deduct > 0;
    }


}

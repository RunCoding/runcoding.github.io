package io.seata.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */
@FeignClient(name = "order-service", url = "127.0.0.1:8082")
public interface OrderFeignClient {

    @GetMapping("/create")
    boolean create(@RequestParam("userId") String userId,
                @RequestParam("commodityCode") String commodityCode,
                @RequestParam("count") Integer count);

    @PostMapping("/commit")
    boolean commit(@RequestParam("userId") String userId,
                   @RequestParam("commodityCode") String commodityCode);

    @DeleteMapping("/rollback")
    boolean rollback(@RequestParam("userId") String userId,
                   @RequestParam("commodityCode") String commodityCode);

}

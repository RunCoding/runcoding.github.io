package io.seata.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @date 2019-08-01
 * @desc: 订单对象
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String userId;

    private String commodityCode;

    private Integer count;


}

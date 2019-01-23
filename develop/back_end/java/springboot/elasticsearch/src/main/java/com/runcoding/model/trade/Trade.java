package com.runcoding.model.trade;

import com.runcoding.model.trade.order.TradeOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xukai
 * @date 2019-01-23
 * @desc: 交易信息
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "trade-2019", type = "trade", shards = 1, replicas = 0)
@Builder
public class Trade {


    @ApiModelProperty("交易编号")
    @Id
    private String tradeId;

    @ApiModelProperty("用户编号")
    private Long userId;

    @ApiModelProperty("交易类型编号")
    private Long tradeTypeId;

    @ApiModelProperty("实付总金额")
    private BigDecimal totalRealAmount;

    @ApiModelProperty("交易状态")
    private Integer tradeStatus;

    @ApiModelProperty("交易订单列表")
    @Field(type = FieldType.Nested)
    private List<TradeOrder> tradeOrderOrders;

    @ApiModelProperty("交易创建时间")
    private Date createTime;

    @ApiModelProperty("交易修改时间")
    private Date updateTime;

}

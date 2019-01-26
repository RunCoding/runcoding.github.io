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
@Document(indexName = "trade-2019",
          type = "trade",
          shards = 4, /**节点数和Shard数相等时，ElasticSearch集群的性能可以达到最优。通常不推荐一个节点超过2个shard。*/
          replicas = 2 /**索引副本(Replica)机制的的思路很简单：为索引分片创建一份新的拷贝，它可以像原来的主分片一样处理用户搜索请求。
                          同时也顺便保证了数据的安全性。即如果主分片数据丢失，ElasticSearch通过索引副本使得数据不丢失。
                          索引副本可以随时添加或者删除，所以用户可以在需要的时候动态调整其数量。*/
)
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

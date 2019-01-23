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
          replicas = 2 /**Replica也是Shard，与shard不同的是，replica只会参与读操作，同时也能提高集群的可用性。
                      对于Replica来说，它的主要作用就是提高集群错误恢复的能力，
                      所以replica的数目与shard的数目以及node的数目相关，与shard不同的是，
                      replica的数目可以在集群建立之后变更，切代价较小，所以相比shard的数目而言，没有那么重要。*/
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

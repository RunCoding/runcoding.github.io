package com.runcoding.model.trade;

import com.runcoding.model.trade.order.TradeOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xukai
 * @date 2019-01-23
 * @desc: 交易信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "#{@indexName}",
          type = "trade",
          shards = 4, /**节点数和Shard数相等时，ElasticSearch集群的性能可以达到最优。通常不推荐一个节点超过2个shard。*/
          replicas = 2 /**索引副本(Replica)机制的的思路很简单：为索引分片创建一份新的拷贝，它可以像原来的主分片一样处理用户搜索请求。
                          同时也顺便保证了数据的安全性。即如果主分片数据丢失，ElasticSearch通过索引副本使得数据不丢失。
                          索引副本可以随时添加或者删除，所以用户可以在需要的时候动态调整其数量。*/
)
//@DynamicTemplates(mappingPath = "/mappings/test-dynamic_templates_mappings_two.json")
public class Trade {


    @ApiModelProperty("交易编号")
    @Id
    @Field(fielddata = true)
    private Long tradeId;

    @ApiModelProperty("用户编号")
    @Field()
    private Long userId;

    @ApiModelProperty("用户名称")
    @Field(type = FieldType.Keyword,index = false,fielddata = true)
    private String userName;

    @ApiModelProperty("交易名称")
    @Field(fielddata = true)
    private String tradeName;

    @ApiModelProperty("交易类型编号")
    @Field()
    private Long tradeTypeId;

    @ApiModelProperty("实付总金额")
    @Field(index = false)
    private BigDecimal totalRealAmount;

    @ApiModelProperty("交易状态")
    @Field()
    private Integer tradeStatus;

    @ApiModelProperty("交易订单列表")
    @Field(type = FieldType.Nested)
    private List<TradeOrder> tradeOrders;

    @ApiModelProperty("交易创建时间")
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date createTime;

    @ApiModelProperty("交易修改时间")
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date updateTime;

    /**地址位置 **/
    @GeoPointField
    private TradeGeoPoint location;

    @Field(type = FieldType.Object)
    private Map<String, String> names = new HashMap<>();

}

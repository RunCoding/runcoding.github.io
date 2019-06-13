package com.runcoding.model.trade.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author xukai
 * @Date 2019-01-23 17:23:57
 * @desc 交易订单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeOrder {

    @ApiModelProperty("交易编号")
    @Field()
    private String tradeNumber;

    @ApiModelProperty( "订单编号")
    @Field()
    private String orderNumber;

    @ApiModelProperty( "用户编号")
    @Field()
    private Long userId;

    @ApiModelProperty("订单总金额")
    @Field(index = false)
    private BigDecimal totalAmount;

    @ApiModelProperty("实付金额")
    @Field(index = false)
    private BigDecimal realAmount;

    @ApiModelProperty("优惠金额")
    @Field(index = false)
    private BigDecimal promotionAmount;

    @ApiModelProperty("运费金额")
    @Field(index = false)
    private BigDecimal freightAmount;

    @ApiModelProperty("订单状态")
    @Field()
    private Integer orderStatus;

    @ApiModelProperty("订单明细")
    @Field(type = FieldType.Nested)
    private List<OrderDetail>  orderDetails;

    @ApiModelProperty("订单创建时间")
    @Field()
    private Date createTime;

    @ApiModelProperty("订单修改时间")
    @Field()
    private Date updateTime;

}
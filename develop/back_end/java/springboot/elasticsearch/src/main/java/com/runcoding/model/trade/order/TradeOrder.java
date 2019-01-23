package com.runcoding.model.trade.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author xukai
 * @Date 2019-01-23 17:23:57
 * @desc 交易订单
 */
@Getter
@Setter
@ToString
public class TradeOrder {

    @ApiModelProperty("交易编号")
    private String tradeNumber;

    @ApiModelProperty( "订单编号")
    private String orderNumber;

    @ApiModelProperty( "用户编号")
    private Long userId;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("实付金额")
    private BigDecimal realAmount;

    @ApiModelProperty("优惠金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty("运费金额")
    private BigDecimal freightAmount;

    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    @ApiModelProperty("订单明细")
    private List<OrderDetail>  orderDetails;

    @ApiModelProperty("订单创建时间")
    private Date createTime;

    @ApiModelProperty("订单修改时间")
    private Date updateTime;

}
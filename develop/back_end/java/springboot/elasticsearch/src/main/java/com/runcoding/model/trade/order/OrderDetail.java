package com.runcoding.model.trade.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author xukai
 * @date 2019-01-23
 * @desc: 订单明细
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDetail {

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("商品skuId")
    private Long productSkuId;

    @ApiModelProperty("商品sku名称")
    private String productSkuName;

    @ApiModelProperty("商品sku编号")
    private String productSkuCode;

    @ApiModelProperty( "商品数量")
    private int qty;

    @ApiModelProperty("销售单价")
    private BigDecimal productSalePrice;

    @ApiModelProperty("(均摊)实付总金额")
    private BigDecimal realAmount;

    @ApiModelProperty("(均摊)优惠金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty("(均摊)运费金额")
    private BigDecimal freightAmount;


}

package com.runcoding.model.po.order;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * @author xukai
 * @Date 2018-01-02 17:23:57
 */
@Getter
@Setter
public class OrderPo {

    @ApiModelProperty( "")
    private Long id;

    @ApiModelProperty( "订单编号")
    private String orderNumber;

    @ApiModelProperty( "用户编号")
    private Long userId;

    @ApiModelProperty( "")
    private Date createTime;

    @ApiModelProperty( "")
    private Date updateTime;
}
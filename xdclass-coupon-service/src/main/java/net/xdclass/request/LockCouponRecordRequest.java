package net.xdclass.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author WJH
 * @date 2021/7/22 下午8:48
 * @QQ 1151777592
 */
@ApiModel(value = "优惠券锁定对象", description = "优惠券锁定对象")
@Data
public class LockCouponRecordRequest {

    /**
     * 优惠券记录🆔列表
     */
    @ApiModelProperty(value = "优惠券记录ID列表",example = "[1,2,3]")
    private List<Long> lockCouponRecordIds;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号",example = "123546897")
    private String orderOutTradeNo;
}

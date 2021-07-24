package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author WJH
 * @date 2021/7/24 下午4:23
 * @QQ 1151777592
 */
@ApiModel(value = "商品锁定对象", description = "商品锁定对象协议")
@Data
public class LockProductRequest {

    @ApiModelProperty(value = "订单ID", example = "2135468")
    @JsonProperty("order_out_trade_no")
    private String orderOutTradeNo;

    @ApiModelProperty(value = "订单项")
    @JsonProperty("order_item_list")
    private List<OrderItemRequest> orderItemList;

}

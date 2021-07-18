package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/19 上午7:39
 * @QQ 1151777592
 */
@ApiModel
@Data
public class CartItemRequest {

    @ApiModelProperty(value = "商品id", example = "11")
    @JsonProperty("product_id")
    private long productId;

    @ApiModelProperty(value = "购买数量", example = "1")
    @JsonProperty("buy_num")
    private int buyNum;
}

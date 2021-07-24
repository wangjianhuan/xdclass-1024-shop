package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/24 下午4:23
 * @QQ 1151777592
 */
@ApiModel(value = "商品子项")
@Data
public class OrderItemRequest {

    @ApiModelProperty(value = "商品ID", example = "1")
    @JsonProperty("product_id")
    private long productId;

    @ApiModelProperty(value = "购买数量", example = "1")
    @JsonProperty("buy_num")
    private int buyNum;

}

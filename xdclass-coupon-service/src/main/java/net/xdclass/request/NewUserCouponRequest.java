package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/17 下午7:08
 * @QQ 1151777592
 */
@ApiModel
@Data
public class NewUserCouponRequest {

    @ApiModelProperty(value = "用户ID",example = "19")
    @JsonProperty("user_id")
    private long userId;

    @ApiModelProperty(value = "名称", example = "王小建")
    @JsonProperty("name")
    private String name;
}

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
@Data
public class NewUserCouponRequest {

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("name")
    private String name;
}

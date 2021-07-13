package net.xdclass.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WJH
 * @class xdclass-1024-shop UserLoginRequest
 * @date 2021/7/13 上午10:38
 * @QQ 1151777592
 */
@Data
@ApiModel(value = "登录对象",description = "用户登录请求对象")
public class UserLoginRequest {

    @ApiModelProperty(value = "邮箱" ,example = "1151777592@qq.com")
    private String mail;

    @ApiModelProperty(value = "密码" ,example = "123456")
    private String pwd;
}

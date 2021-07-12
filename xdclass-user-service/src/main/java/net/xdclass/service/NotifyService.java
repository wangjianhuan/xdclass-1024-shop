package net.xdclass.service;

import net.xdclass.enums.SendCodeEnum;
import net.xdclass.utils.JsonData;

/**
 * @author WJH
 * @class xdclass-1024-shop NotifyService
 * @date 2021/7/11 下午10:44
 * @QQ 1151777592
 */
public interface NotifyService {

    /**
     * 发送验证码
     * @param sendCodeEnum 验证码类型
     * @param to 收件人
     * @return
     */
    JsonData sendCode(SendCodeEnum sendCodeEnum ,String to);

    /**
     * 验证码检查接口
     * @param sendCodeEnum
     * @param to
     * @param code
     * @return
     */
    boolean checkCode(SendCodeEnum sendCodeEnum , String to , String code);
}

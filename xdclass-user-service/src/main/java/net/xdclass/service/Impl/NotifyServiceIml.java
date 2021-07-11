package net.xdclass.service.Impl;

import net.xdclass.enums.SendCodeEnum;
import net.xdclass.component.MailService;
import net.xdclass.service.NotifyService;
import net.xdclass.utils.CheckUtil;
import net.xdclass.utils.CommonUtil;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WJH
 * @class xdclass-1024-shop NotifyServiceIml
 * @date 2021/7/11 下午11:03
 * @QQ 1151777592
 */
@Service
public class NotifyServiceIml implements NotifyService{

    @Autowired
    private MailService mailService;

    //验证码标题
    private static final String SUBJECT = "1024购物平台";

    //验证码内容
    private static final String CONTENT = "%s";


    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {

        if (CheckUtil.isEmail(to)){
            //邮箱验证码
            String code = CommonUtil.getRandCode(6);
            mailService.sendMail(to,SUBJECT,String.format(CONTENT, code));
            return JsonData.buildSuccess();
        }else if (CheckUtil.isPhone(to)){
            //短信验证码

        }

        return null;
    }
}

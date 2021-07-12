package net.xdclass.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.constant.CacheKey;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.component.MailService;
import net.xdclass.service.NotifyService;
import net.xdclass.utils.CheckUtil;
import net.xdclass.utils.CommonUtil;
import net.xdclass.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author WJH
 * @class xdclass-1024-shop NotifyServiceIml
 * @date 2021/7/11 下午11:03
 * @QQ 1151777592
 */
@Service
@Slf4j
public class NotifyServiceIml implements NotifyService {

    @Autowired
    private MailService mailService;

    /**
     * 验证码标题
     */
    private static final String SUBJECT = "1024购物平台";

    /**
     * 验证码内容
     */
    private static final String CONTENT = "%s";

    /**
     * 一分钟有效
     */
    private static final int CODE_EXPIRED = 60 * 1000;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {

        String cacheKey = String.format(CacheKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);

        String cacheValue = redisTemplate.opsForValue().get(cacheKey);

        /**
         * 如果不为空 ，则判断是否60秒内重复发送
         */
        if(StringUtils.isNotBlank(cacheValue)){
            long ttl = Long.parseLong(cacheValue.split("_")[1]);
            //当前时间戳 - 验证码发送的时间戳 ，如果小于60秒 ， 则不会重复发送
            if (CommonUtil.getCurrentTimestamp() - ttl < 1000 * 60) {
                log.info("重复发送验证码，时间间隔:{}", (CommonUtil.getCurrentTimestamp() - ttl) / 1000);
                return JsonData.buildResult(BizCodeEnum.CODE_LIMITED);
            }
        }

        //邮箱验证码
        String code = CommonUtil.getRandCode(6);
        String value = code + "_" + CommonUtil.getCurrentTimestamp();
        redisTemplate.opsForValue().set(cacheKey, value, CODE_EXPIRED, TimeUnit.MILLISECONDS);

        if (CheckUtil.isEmail(to)) {
            mailService.sendMail(to, SUBJECT, String.format(CONTENT, code));
            return JsonData.buildSuccess();
        } else if (CheckUtil.isPhone(to)) {
            // TODO: 2021/7/12  短信验证码
        }

        return null;
    }
}

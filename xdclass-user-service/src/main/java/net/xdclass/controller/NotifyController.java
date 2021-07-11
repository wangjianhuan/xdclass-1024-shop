package net.xdclass.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author WJH
 * @class xdclass-1024-shop NotifyController
 * @date 2021/7/11 下午4:56
 * @QQ 1151777592
 */
@RestController
@RequestMapping("/api/user/v1")
@Api(tags = "通知模块")
@Slf4j
public class NotifyController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 图形验证码有效时间
     */
    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;

    @RequestMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captcha = captchaProducer.createText();
        log.info("验证码" + captcha);

        redisTemplate.opsForValue().set(getCaptchaKey(request), captcha, CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);
        BufferedImage bufferedImage = captchaProducer.createImage(captcha);

        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("获取图形验证码失败：{}", e);
        }
    }

    /**
     * 获取缓存的KEY
     *
     * @param request
     * @return
     */
    private String getCaptchaKey(HttpServletRequest request) {
        String ip = CommonUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");

        String key = "user-service:captcha" + CommonUtil.MD5(ip + userAgent);

        log.info("IP地址" + ip);
        log.info("userAgent：" + userAgent);
        log.info("KEY：" + key);

        return key;
    }
}

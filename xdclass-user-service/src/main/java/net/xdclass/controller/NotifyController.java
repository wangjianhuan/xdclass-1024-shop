package net.xdclass.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @RequestMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captcha = captchaProducer.createText();
        log.info(captcha);
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
}

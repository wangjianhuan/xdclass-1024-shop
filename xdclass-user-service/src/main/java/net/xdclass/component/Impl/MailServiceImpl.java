package net.xdclass.component.Impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.component.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author WJH
 * @class xdclass-1024-shop MailServiceImpl
 * @date 2021/7/11 下午10:04
 * @QQ 1151777592
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    /**
     * SpringBoot 提供的读取配置文件 ，直接注入使用
     */
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Override
    public void sendMail(String to, String subject, String content) {

        //创建一个邮箱消息对象
        SimpleMailMessage message = new SimpleMailMessage();

        //配置邮箱发件人
        message.setFrom(from);

        //邮件收件人
        message.setTo(to);

        //邮件的主题
        message.setSubject(subject);

        //邮件的内容
        message.setText(content);

        mailSender.send(message);

        log.info("邮件发送成功:{}", message);
    }
}

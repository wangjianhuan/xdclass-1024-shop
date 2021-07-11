package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.UserApplication;
import net.xdclass.component.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WJH
 * @class xdclass-1024-shop SendMailTest
 * @date 2021/7/11 下午10:14
 * @QQ 1151777592
 */
@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SendMailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {

        mailService.sendMail("1151777592@qq.com", "欢迎来到德莱联盟", "这位大哥！你被逮捕了");
    }
}

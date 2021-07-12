package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.UserApplication;
import net.xdclass.component.MailService;
import net.xdclass.utils.CommonUtil;
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
public class MD5Test {

    @Test
    public void testSendMail() {
        System.out.println(CommonUtil.MD5(CommonUtil.MD5(CommonUtil.MD5("123456"))));
    }
}

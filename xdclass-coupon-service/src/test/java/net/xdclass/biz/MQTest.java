package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.CouponApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WJH
 * @date 2021/7/23 下午6:39
 * @QQ 1151777592
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponApplication.class)
@Slf4j
public class MQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendDelayMsg(){

        rabbitTemplate.convertAndSend("coupon.event.exchange","coupon.release.delay.routing.key","我是大帅比");
    }
}

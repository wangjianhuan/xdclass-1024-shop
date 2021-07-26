package net.xdcalss.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.OrderApplication;
import net.xdclass.model.OrderMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
@Slf4j
public class MQTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendDelayMsg(){

        rabbitTemplate.convertAndSend("order.event.exchange","order.close.delay.routing.key","this is product stock lock msg");

    }

    @Test
    public void testSendProductStockMessage(){
        OrderMessage orderMessage = new OrderMessage();

        orderMessage.setOutTradeNo("123456abc");

        rabbitTemplate.convertAndSend("order.event.exchange","order.close.delay.routing.key",orderMessage);

    }

}




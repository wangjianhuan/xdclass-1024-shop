package net.xdclass.MQ;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.model.OrderMessage;
import net.xdclass.service.ProductOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author WJH
 * @date 2021/7/26 下午4:07
 * @QQ 1151777592
 */
@Slf4j
@Component
@RabbitListener(queues = "${mqconfig.order_close_queue}")
public class ProductOrderMQListener {

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * 消息重复消费 幂等性保障
     * 并发情况下如何保障安全
     *
     * @param orderMessage
     * @param message
     * @param channel
     */
    @RabbitListener
    public void closeProductOrder(OrderMessage orderMessage, Message message, Channel channel) {
        log.info("监听到消息:closeProductOrder:{}", orderMessage);
        long msgTag = message.getMessageProperties().getDeliveryTag();

        try {
            boolean flag = productOrderService.closeProductOrder(orderMessage);
            if (flag) {
                channel.basicAck(msgTag, false);
            } else {
                channel.basicReject(msgTag, true);
            }
        } catch (IOException e) {
            log.error("定时关单失败：", orderMessage);
            try {
                channel.basicReject(msgTag, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}

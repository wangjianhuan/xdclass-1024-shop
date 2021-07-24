package net.xdclass.MQ;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.model.CouponRecordMessage;
import net.xdclass.model.ProductMessage;
import net.xdclass.service.ProductService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author WJH
 * @date 2021/7/24 下午8:00
 * @QQ 1151777592
 */
@Slf4j
@Component
@RabbitListener(queues = "${mqconfig.stock_release_queue}")
public class ProductStockMQListener {

    @Autowired
    private ProductService productService;

    /**
     * 重复消费-幂等性
     * 消费失败重新入队后最大重试次数
     * 如果消费失败不重入队，可以记录日志，然后插入到数据库人工排查
     * <p>
     * todo 消费者还有啥问题？？？
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitHandler
    public void releaseProductStock(ProductMessage productMessage, Message message, Channel channel) throws IOException {

        log.info("监听到消息：releaseCouponRecord消息内容：{}", productMessage);
        long msgTag = message.getMessageProperties().getDeliveryTag();

        boolean flag = productService.releaseProductStock(productMessage);

        try {
            if (flag) {
                //确认消息消费成功
                channel.basicAck(msgTag, false);
            } else {
                log.error("释放库存失败 flag=false,{}", productMessage);
                channel.basicReject(msgTag, true);
            }

        } catch (IOException e) {
            log.error("释放库存记录异常:{},msg:{}", e, productMessage);
            channel.basicReject(msgTag, true);
        }

    }

}

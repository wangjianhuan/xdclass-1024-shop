package net.xdclass.model;

import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/24 下午7:46
 * @QQ 1151777592
 */
@Data
public class ProductMessage {

    /**
     * 消息队列id
     */
    private Long messageId;

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 库存锁定工作单id
     */
    private Long taskId;


}

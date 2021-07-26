package net.xdclass.model;

import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/26 下午12:59
 * @QQ 1151777592
 */
@Data
public class OrderMessage {

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 订单号
     */
    private String outTradeNo;
}

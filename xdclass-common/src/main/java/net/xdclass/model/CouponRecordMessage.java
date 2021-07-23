package net.xdclass.model;

import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/23 下午7:03
 * @QQ 1151777592
 */
@Data
public class CouponRecordMessage {

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

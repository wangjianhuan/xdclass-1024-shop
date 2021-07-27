package net.xdclass.constant;

/**
 * @author WJH
 * @date 2021/7/27 下午5:48
 * @QQ 1151777592
 */
public class TimeConstant {

    /**
     * 支付订单的有效时长，超过为支付则关闭订单
     *
     * 订单超时，毫秒，默认30分钟
     *
     * 测试5分钟
     */
    //public static final long ORDER_PAY_TIMEOUT_MILLS = 30 * 60 * 1000;
    public static final long ORDER_PAY_TIMEOUT_MILLS = 5 * 60 * 1000;

}

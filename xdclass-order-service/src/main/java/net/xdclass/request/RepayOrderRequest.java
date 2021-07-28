package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author WJH
 * @date 2021/7/28 下午7:57
 * @QQ 1151777592
 */
@Data
public class RepayOrderRequest {

    /**
     * 订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 支付类型
     * 支付宝 微信 银行卡
     */
    @JsonProperty("pay_type")
    private String payType;

    /**
     * 支付种类
     * APP H5 PC
     */
    @JsonProperty("client_type")
    private String clientType;

}

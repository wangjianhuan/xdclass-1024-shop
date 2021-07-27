package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author WJH
 * @date 2021/7/19 下午10:47
 * @QQ 1151777592
 */
@Data
public class ConfirmOrderRequest {

    /**
     * 购物车使用的优惠券
     * 满减券
     * 传空或者小于0，则不用优惠券
     */
    @JsonProperty("coupon_record_id")
    private Long couponRecordId;

    /**
     * 最终购买的商品列表
     * 传递ID，购买数量从购物车中读取
     */
    @JsonProperty("product_ids")
    private List<Long> productIdList;

    /**
     * 支付类型 微信 支付宝 银行
     */
    @JsonProperty("pay_type")
    private String payType;

    /**
     * 客户端类型 APP PC H5
     */
    @JsonProperty("client_type")
    private String clientType;

    /**
     * 收获地址🆔
     */
    @JsonProperty("address_id")
    private Long addressId;

    /**
     * 总价格，前端传递， 后端需要验价
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    /**
     * 实际需要支付的价格
     * 如果用了优惠券，则是减去优惠券的价格
     * 如果没有优惠券，则是和totalAmount价格一样
     */
    @JsonProperty("real_pay_amount")
    private BigDecimal realAmount;
}
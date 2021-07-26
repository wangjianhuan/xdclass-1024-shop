package net.xdclass.component;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.PayInfoVO;
import net.xdclass.config.AliPayConfig;
import net.xdclass.config.PayUrlConfig;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.ClientType;
import net.xdclass.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author WJH
 * @date 2021/7/26 下午11:10
 * @QQ 1151777592
 */
@Slf4j
@Service
public class AlipayStrategy implements PayStrategy {

    @Autowired
    private PayUrlConfig payUrlConfig;


    @Override
    public String unifiedorder(PayInfoVO payInfoVO) {

        HashMap<String, String> content = new HashMap<>();

        //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复

        content.put("out_trade_no", payInfoVO.getOutTradeNo());
        log.info("订单号:{}", payInfoVO.getOutTradeNo());
        content.put("product_code", "FAST_INSTANT_TRADE_PAY");
        //订单总金额，单位为元，精确到小数点后两位
        content.put("total_amount", payInfoVO.getPayType().toString());
        //商品标题/交易标题/订单标题/订单关键字等。 注意：不可使用特殊字符，如 /，=，&amp; 等。
        content.put("subject", payInfoVO.getTitle());
        //商品描述，可空
        content.put("body", payInfoVO.getDescription());

        //前端也需要判断是否块关闭  如果快要到期，则不给支付
        double timeout = Math.floor(payInfoVO.getOrderPayTimeoutMills() / 1000 / 60);
        if (timeout < 1) {
            throw new BizException(BizCodeEnum.PAY_ORDER_PAY_TIMEOUT);
        }

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        content.put("timeout_express", Double.valueOf(timeout) + "m");

        String clientType = payInfoVO.getClientType();
        String form = "";

        try {

            if (clientType.equalsIgnoreCase(ClientType.H5.name())) {
                //H5手机端
                AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
                request.setBizContent(JSON.toJSONString(content));
                request.setNotifyUrl(payUrlConfig.getAlipayCallbackUrl());
                request.setReturnUrl(payUrlConfig.getAlipaySuccessReturnUrl());

                AlipayTradeWapPayResponse alipayResponse = AliPayConfig.getInstance().pageExecute(request);

                log.info("响应日志：{}",alipayResponse);
                if (alipayResponse.isSuccess()) {
                    form = alipayResponse.getBody();
                } else {
                    log.error("支付宝构建H5表单失败:alipayResponse={},payInfo={}",alipayResponse,payInfoVO);
                }

            } else if (clientType.equalsIgnoreCase(ClientType.PC.name())) {
                //PC端支付
                AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
                request.setBizContent(JSON.toJSONString(content));
                request.setNotifyUrl(payUrlConfig.getAlipayCallbackUrl());
                request.setReturnUrl(payUrlConfig.getAlipaySuccessReturnUrl());

                AlipayTradePagePayResponse alipayResponse = AliPayConfig.getInstance().pageExecute(request);

                log.info("响应日志：{}",alipayResponse);
                if (alipayResponse.isSuccess()) {
                    form = alipayResponse.getBody();
                } else {
                    log.error("支付宝构建PC表单失败:alipayResponse={},payInfo={}",alipayResponse,payInfoVO);
                }
            }
        } catch (AlipayApiException e) {
            log.error("支付宝构建表单异常:payInfo={},异常={}",payInfoVO,e);
        }

        return form;
    }


    @Override
    public String refund(PayInfoVO payInfoVO) {
        return null;
    }

    @Override
    public String queryPaySuccess(PayInfoVO payInfoVO) {
        return null;
    }
}
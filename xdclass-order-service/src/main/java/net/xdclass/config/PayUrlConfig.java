package net.xdclass.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author WJH
 * @date 2021/7/26 下午7:30
 * @QQ 1151777592
 */
@Data
@Configuration
public class PayUrlConfig {

    /**
     * 支付成功页面跳转
     */
    @Value("${alipay.success_return_url}")
    private String alipaySuccessReturnUrl;

    /**
     * 支付成功，回调接口
     */
    @Value("${alipay.callback_url}")
    private String alipayCallbackUrl;


}

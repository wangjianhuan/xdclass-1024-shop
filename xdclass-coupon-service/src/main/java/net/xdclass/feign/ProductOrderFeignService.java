package net.xdclass.feign;

import net.xdclass.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WJH
 * @date 2021/7/23 下午9:49
 * @QQ 1151777592
 */
@FeignClient(name = "xdclass-product-service")
public interface ProductOrderFeignService {

    /**
     * 查询订单状态
     * @param outTradeNo
     * @return
     */
    @GetMapping("api/order/v1/query_state")
    JsonData queryProductOrderState(@RequestParam("out-trade-no")String outTradeNo);
}

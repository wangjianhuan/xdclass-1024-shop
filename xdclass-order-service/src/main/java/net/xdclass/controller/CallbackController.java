package net.xdclass.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.AliPayConfig;
import net.xdclass.enums.ProductOrderPayTypeEnum;
import net.xdclass.service.ProductOrderService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author WJH
 * @date 2021/7/27 下午5:13
 * @QQ 1151777592
 */
@Slf4j
@Api("订单回调通知模块")
@Controller
@RequestMapping("/api/callback/order/v1")
public class CallbackController {

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * 支付宝回调通知
     * @param request
     * @param response
     * @return
     */
    @PostMapping("alipay")
    public String alipayCallback(HttpServletRequest request, HttpServletResponse response){

        //将异步通知中收到的所有参数存储到map中
        Map<String,String> paramsMap = convertRequestParamsToMap(request);
        log.info("支付宝回调通知结果:{}",paramsMap);
        //调用SDK验证签名
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AliPayConfig.ALIPAY_PUB_KEY, AliPayConfig.CHARSET, AliPayConfig.SIGN_TYPE);
            if(signVerified){

                JsonData jsonData = productOrderService.handlerOrderCallbackMsg(ProductOrderPayTypeEnum.ALIPAY,paramsMap);

                if(jsonData.getCode() == 0){
                    //通知结果确认成功，不然会一直通知，八次都没返回success就认为交易失败
                    return "success";
                }

            }

        } catch (AlipayApiException e) {
            log.info("支付宝回调验证签名失败:异常：{}，参数:{}",e,paramsMap);
        }

        return "failure";
    }

    /**
     * 将request中的参数转换成Map
     * @param request
     * @return
     */
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> paramsMap = new HashMap<>(16);
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int size = values.length;
            if (size == 1) {
                paramsMap.put(name, values[0]);
            } else {
                paramsMap.put(name, "");
            }
        }
        System.out.println(paramsMap);
        return paramsMap;
    }
}

package net.xdclass.service;

import net.xdclass.request.ConfirmOrderRequest;
import net.xdclass.utils.JsonData;

/**
 * @author WJH
 * @date 2021/7/19 下午10:36
 * @QQ 1151777592
 */
public interface ProductOrderService {

    /**
     * 创建订单
     * @param orderRequest
     * @return
     */
    JsonData confirmOrder(ConfirmOrderRequest orderRequest);
}

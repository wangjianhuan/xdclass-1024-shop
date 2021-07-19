package net.xdclass.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.request.ConfirmOrderRequest;
import net.xdclass.service.ProductOrderService;
import net.xdclass.utils.JsonData;
import org.springframework.stereotype.Service;

/**
 * @author WJH
 * @date 2021/7/19 下午10:36
 * @QQ 1151777592
 */
@Service
@Slf4j
public class ProductOrderServiceImpl implements ProductOrderService {

    /**
     * 创建订单
     * @param orderRequest
     * @return
     */
    @Override
    public JsonData confirmOrder(ConfirmOrderRequest orderRequest) {
        // TODO
        return null;
    }
}

package net.xdclass.service;

import net.xdclass.request.CartItemRequest;

/**
 * @author WJH
 * @date 2021/7/18 下午10:55
 * @QQ 1151777592
 */
public interface CartService {

    /**
     * 添加商品到购物车
     * @param cartItemRequest
     * @return
     */
    void addToCart(CartItemRequest cartItemRequest);
}

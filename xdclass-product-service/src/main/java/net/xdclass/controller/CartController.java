package net.xdclass.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.xdclass.request.CartItemRequest;
import net.xdclass.service.CartService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WJH
 * @date 2021/7/18 下午10:52
 * @QQ 1151777592
 */
@Api("购物车")
@RestController
@RequestMapping("/api/cart/v1/")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation("添加到购物车")
    @PostMapping("add")
    public JsonData addToCart(@ApiParam("购物项") @RequestBody CartItemRequest cartItemRequest){

        cartService.addToCart(cartItemRequest);

        return JsonData.buildSuccess();
    }
}

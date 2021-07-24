package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.VO.ProductVO;
import net.xdclass.request.LockProductRequest;
import net.xdclass.service.ProductService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WJH
 * @since 2021-07-14
 */
@Api("产品模块")
@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("产品分页接口")
    @GetMapping("/product_page")
    public JsonData pageProductList(
            @ApiParam(value = "当前页") @RequestParam(value = "page", defaultValue = "1") int page,
            @ApiParam(value = "每页显示多少条") @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Map<String, Object> pageResult = productService.page(page, size);
        return JsonData.buildSuccess(pageResult);
    }

    @ApiOperation("商品详情")
    @GetMapping("/detail/{product_id}")
    public JsonData detail(@ApiParam(value = "商品ID", required = true) @PathVariable("product_id") long productId) {

        ProductVO productVO = productService.findDetailById(productId);
        return JsonData.buildSuccess(productVO);
    }

    /**
     * 商品库存锁定
     *
     * @return
     */
    @ApiOperation("商品库存锁定")
    @PostMapping("lock_products")
    public JsonData lockProducts(@ApiParam("商品库存锁定") @RequestBody LockProductRequest lockProductRequest) {

        JsonData jsonData = productService.lockProductStock(lockProductRequest);

        return jsonData;

    }
}


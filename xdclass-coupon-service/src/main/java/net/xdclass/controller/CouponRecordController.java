package net.xdclass.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.service.CouponRecordService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WJH
 * @since 2021-07-14
 */
@RestController
@RequestMapping("/api/coupon_record/v1/")
public class CouponRecordController {

    @Autowired
    private CouponRecordService couponRecordService;

    @ApiOperation("分页查询领券记录")
    @GetMapping("page")
    public JsonData page(@ApiParam(value = "当前页") @RequestParam(value = "page", defaultValue = "1") int page,
                         @ApiParam(value = "每页显示多少条") @RequestParam(value = "size", defaultValue = "10") int size){

        Map<String,Object> pageResult = couponRecordService.page(page,size);

        return JsonData.buildSuccess(pageResult);
    }
}


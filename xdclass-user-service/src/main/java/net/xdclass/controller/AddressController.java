package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.exception.BizException;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.service.AddressService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 电商-公司收发货地址表 前端控制器
 * </p>
 *
 * @author 王建欢
 * @since 2021-07-11
 */
@Api(tags = "收获地址模块")
@RestController
@RequestMapping("/api/address/v1/")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @ApiOperation("新增收货地址")
    @PostMapping("add")
    public JsonData add(@ApiParam("地址对象") @RequestBody AddressAddRequest addressAddRequest){

        addressService.add(addressAddRequest);
        return JsonData.buildSuccess();
    }

    @ApiOperation("根据id查找地址详情")
    @GetMapping("find/{address_id}")
    public Object detail(@ApiParam(value = "地址id",required = true) @PathVariable("address_id") Long addressId){

        return addressService.detail(addressId);
    }
}


package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xdclass.VO.BannerVO;
import net.xdclass.service.BannerService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WJH
 * @since 2021-07-14
 */
@Api("轮播图模块")
@RestController
@RequestMapping("/api/banner/v1")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation("轮播图列表")
    @GetMapping("list")
    public JsonData list(){

        List<BannerVO> list = bannerService.list();
        return JsonData.buildSuccess(list);
    }

}


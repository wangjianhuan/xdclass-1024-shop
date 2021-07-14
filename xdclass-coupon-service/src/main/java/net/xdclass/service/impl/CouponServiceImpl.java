package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.CouponVO;
import net.xdclass.enums.CouponCategoryEnum;
import net.xdclass.enums.CouponPublishEnum;
import net.xdclass.mapper.CouponMapper;
import net.xdclass.model.CouponDO;
import net.xdclass.service.CouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WJH
 * @date 2021/7/14 下午10:35
 * @QQ 1151777592
 */
@Service
@Slf4j
public class CouponServiceImpl implements CouponService {


    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Map<String, Object> pageCouponActivity(int page, int size) {

        Page<CouponDO> pageInfo = new Page<>(page,size);

        IPage<CouponDO> couponDOIPage =  couponMapper.selectPage(pageInfo, new QueryWrapper<CouponDO>()
            .eq("publish",CouponPublishEnum.PUBLISH)
            .eq("category", CouponCategoryEnum.PROMOTION)
            .orderByDesc("create_time"));


        Map<String,Object> pageMap = new HashMap<>(3);
        //总条数
        pageMap.put("total_record", couponDOIPage.getTotal());
        //总页数
        pageMap.put("total_page",couponDOIPage.getPages());

        pageMap.put("current_data",couponDOIPage.getRecords().stream().map(obj->beanProcess(obj)).collect(Collectors.toList()));


        return pageMap;
    }

    private CouponVO beanProcess(CouponDO couponDO) {
        CouponVO couponVO = new CouponVO();
        BeanUtils.copyProperties(couponDO,couponVO);
        return couponVO;
    }
}

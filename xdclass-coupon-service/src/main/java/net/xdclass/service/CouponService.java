package net.xdclass.service;

import net.xdclass.enums.CouponCategoryEnum;
import net.xdclass.utils.JsonData;

import java.util.Map;

/**
 * @author WJH
 * @date 2021/7/14 下午10:33
 * @QQ 1151777592
 */
public interface CouponService {

    /**
     * 分页查询优惠券
     * @param page
     * @param size
     * @return
     */
    Map<String,Object> pageCouponActivity(int page, int size);

    /**
     * 领取优惠券
     * @param couponId
     * @param category
     * @return
     */
    JsonData addCoupon(long couponId, CouponCategoryEnum category);
}

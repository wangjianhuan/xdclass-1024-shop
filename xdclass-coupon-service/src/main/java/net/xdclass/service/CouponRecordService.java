package net.xdclass.service;

import java.util.Map;

/**
 * @author WJH
 * @date 2021/7/17 上午11:37
 * @QQ 1151777592
 */
public interface CouponRecordService {

    /**
     * 分页查询优惠券领取记录
     * @param page
     * @param size
     * @return
     */
    Map<String, Object> page(int page, int size);
}

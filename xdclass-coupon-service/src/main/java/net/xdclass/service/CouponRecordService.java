package net.xdclass.service;

import net.xdclass.VO.CouponRecordVO;
import net.xdclass.model.CouponRecordMessage;
import net.xdclass.request.LockCouponRecordRequest;
import net.xdclass.utils.JsonData;

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

    /**
     * 查询优惠券记录详情
     * @param recordId
     * @return
     */
    CouponRecordVO findById(long recordId);

    /**
     * 锁定优惠券
     * @param recordRequest
     * @return
     */
    JsonData lockCouponRecords(LockCouponRecordRequest recordRequest);

    /**
     * 释放优惠券记录
     * @param recordMessage
     * @return
     */
    boolean releaseCouponRecord(CouponRecordMessage recordMessage);
}

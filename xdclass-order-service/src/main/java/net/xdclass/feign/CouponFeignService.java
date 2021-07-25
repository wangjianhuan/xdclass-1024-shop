package net.xdclass.feign;

import net.xdclass.request.LockCouponRecordRequest;
import net.xdclass.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author WJH
 * @date 2021/7/25 下午9:04
 * @QQ 1151777592
 */
@FeignClient(name = "xdclass-coupon-service")
public interface CouponFeignService {

    /**
     * 查询用户的优惠券是否可用，防止水平权限
     * @param recordId
     * @return
     */
    @GetMapping("/api/coupon-record/v1/detail/{record_id}")
    JsonData findUserCouponRecordById(@PathVariable("record_id") long recordId);

    /**
     * 锁定优惠券
     * @param lockCouponRecordRequest
     * @return
     */
    @PostMapping("/api/coupon_record/v1/lock_records")
    JsonData lockCouponRecords(@RequestBody LockCouponRecordRequest lockCouponRecordRequest);
}

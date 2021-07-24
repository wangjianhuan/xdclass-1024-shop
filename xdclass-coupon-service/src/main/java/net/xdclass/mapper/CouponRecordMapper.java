package net.xdclass.mapper;

import net.xdclass.model.CouponRecordDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author WJH
 * @since 2021-07-14
 */
public interface CouponRecordMapper extends BaseMapper<CouponRecordDO> {

    /**
     * 批量更新使用优惠券记录
     *
     * @param userId
     * @param useState
     * @param lockCouponRecordIds
     * @return
     */
    int lockUseStateBatch(@Param("userId") Long userId, @Param("useState") String useState, @Param("lockCouponRecordIds") List<Long> lockCouponRecordIds);

    /**
     * 更新优惠券使用记录
     *
     * @param couponRecordId
     * @param useState
     */
    void updateState(@Param("couponRecordId") Long couponRecordId, @Param("useState") String useState);
}

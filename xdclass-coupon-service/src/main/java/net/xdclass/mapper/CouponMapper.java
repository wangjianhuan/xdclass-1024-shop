package net.xdclass.mapper;

import net.xdclass.model.CouponDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WJH
 * @since 2021-07-14
 */
public interface CouponMapper extends BaseMapper<CouponDO> {

    /**
     * 扣减库存
     * @param couponId
     * @return
     */
    int reduceStock(long couponId);
}

package net.xdclass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.xdclass.model.ProductOrderDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author WJH
 * @date 2021/7/19 下午9:55
 * @QQ 1151777592
 */
public interface ProductOrderMapper extends BaseMapper<ProductOrderDO> {

    /**
     * 更新订单状态
     * @param outTradeNo
     * @param newState
     * @param oldState
     */
    void updateOrderPayState(@Param("outTradeNo") String outTradeNo, @Param("newState")String newState, @Param("oldState")String oldState);
}

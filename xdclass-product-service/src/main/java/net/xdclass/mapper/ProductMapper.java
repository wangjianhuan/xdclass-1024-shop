package net.xdclass.mapper;

import net.xdclass.model.ProductDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author WJH
 * @since 2021-07-18
 */
public interface ProductMapper extends BaseMapper<ProductDO> {

    /**
     * 锁定商品库存
     *
     * @param productId
     * @param buyNum
     * @return
     */
    int lockProductStock(@Param("product_id") long productId, @Param("buy_num") int buyNum);

    /**
     * 解锁商品库存
     *
     * @param productId
     * @param buyNum
     */
    void unlockProductStock(@Param("product_id") Long productId, @Param("buy_num") Integer buyNum);
}
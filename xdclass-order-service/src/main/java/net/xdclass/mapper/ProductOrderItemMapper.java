package net.xdclass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.xdclass.model.ProductOrderItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WJH
 * @date 2021/7/19 下午9:55
 * @QQ 1151777592
 */
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItemDO> {

    /**
     * 批量插入
     * @param list
     */
    void insertBatch(@Param("orderItemList") List<ProductOrderItemDO> list);
}
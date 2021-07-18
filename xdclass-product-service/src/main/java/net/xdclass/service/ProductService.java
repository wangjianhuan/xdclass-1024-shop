package net.xdclass.service;

import net.xdclass.VO.ProductVO;

import java.util.Map;

/**
 * @author WJH
 * @date 2021/7/18 下午1:35
 * @QQ 1151777592
 */
public interface ProductService {

    /**
     * 分页查询商品列表
     * @param page
     * @param size
     * @return
     */
    Map<String, Object> page(int page, int size);

    /**
     * 根据ID商品详情查询
     * @param productId
     * @return
     */
    ProductVO findDetailById(long productId);
}

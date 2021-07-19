package net.xdclass.service;

import net.xdclass.VO.ProductVO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * 分页查询商品列表
     * @param page
     * @param size
     * @return
     */
    Map<String,Object> page(int page, int size);

    /**
     * 根据id找商品详情
     * @param productId
     * @return
     */
    ProductVO findDetailById(long productId);

    /**
     * 根据ID批量查询商品
     * @param productIdList
     * @return
     */
    List<ProductVO> findProductsByIdBatch(List<Long> productIdList);
}

package net.xdclass.service;

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
}

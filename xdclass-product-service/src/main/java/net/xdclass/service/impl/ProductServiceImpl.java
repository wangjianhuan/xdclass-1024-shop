package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.ProductVO;
import net.xdclass.mapper.ProductMapper;
import net.xdclass.model.ProductDO;
import net.xdclass.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WJH
 * @date 2021/7/18 下午1:35
 * @QQ 1151777592
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 分页查询商品列表
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> page(int page, int size) {

        Page<ProductDO> pageInfo = new Page<>(page, size);

        IPage<ProductDO> productDOTPage = productMapper.selectPage(pageInfo, null);

        HashMap<String, Object> pageMap = new HashMap<>(3);

        pageMap.put("total_record",productDOTPage.getTotal());
        pageMap.put("total_page",productDOTPage.getPages());
        pageMap.put("current_data",productDOTPage.getRecords().stream().map(obj->{
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(obj,productVO);
            productVO.setStock(obj.getStock()-obj.getLockStock());
            return productVO;
        }).collect(Collectors.toList()));
        return pageMap;

    }
}

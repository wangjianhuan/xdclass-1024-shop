package net.xdclass.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.CouponRecordVO;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.mapper.CouponRecordMapper;
import net.xdclass.model.CouponRecordDO;
import net.xdclass.model.LoginUser;
import net.xdclass.service.CouponRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WJH
 * @date 2021/7/17 上午11:43
 * @QQ 1151777592
 */
@Service
@Slf4j
public class CouponRecordServiceImpl implements CouponRecordService {

    @Autowired
    private CouponRecordMapper couponRecordMapper;


    @Override
    public Map<String, Object> page(int page, int size) {

        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        //封装分页信息
        Page<CouponRecordDO> pageInfo = new Page<>(page, size);

        IPage<CouponRecordDO> recordDOIPage = couponRecordMapper.selectPage(pageInfo, new QueryWrapper<CouponRecordDO>()
                .eq("user_id", loginUser.getId()).orderByDesc("create_time"));

        Map<String, Object> pageMap = new HashMap<>(3);

        pageMap.put("total_record", recordDOIPage.getTotal());
        pageMap.put("total_page", recordDOIPage.getPages());
        pageMap.put("current_data", recordDOIPage.getRecords().stream().map(obj -> beanProcess(obj)).collect(Collectors.toList()));

        return pageMap;
    }

    /**
     * 查询优惠券记录详情
     *
     * @param recordId
     * @return
     */
    @Override
    public CouponRecordVO findById(long recordId) {

        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        CouponRecordDO couponRecordDO = couponRecordMapper.selectOne(new QueryWrapper<CouponRecordDO>()
                .eq("id", recordId).eq("user_id", loginUser.getId()));

        if (couponRecordDO == null) {
            return null;
        }

        return beanProcess(couponRecordDO);
    }

    private CouponRecordVO beanProcess(CouponRecordDO couponRecordDO) {


        CouponRecordVO couponRecordVO = new CouponRecordVO();
        BeanUtils.copyProperties(couponRecordDO, couponRecordVO);
        return couponRecordVO;
    }
}

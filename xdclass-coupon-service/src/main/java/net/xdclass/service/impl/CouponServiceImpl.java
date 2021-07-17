package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.CouponVO;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.CouponCategoryEnum;
import net.xdclass.enums.CouponPublishEnum;
import net.xdclass.enums.CouponStateEnum;
import net.xdclass.exception.BizException;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.mapper.CouponMapper;
import net.xdclass.mapper.CouponRecordMapper;
import net.xdclass.model.CouponDO;
import net.xdclass.model.CouponRecordDO;
import net.xdclass.model.LoginUser;
import net.xdclass.request.NewUserCouponRequest;
import net.xdclass.service.CouponService;
import net.xdclass.utils.CommonUtil;
import net.xdclass.utils.JsonData;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author WJH
 * @date 2021/7/14 下午10:35
 * @QQ 1151777592
 */
@Service
@Slf4j
public class CouponServiceImpl implements CouponService {


    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponRecordMapper couponRecordMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Map<String, Object> pageCouponActivity(int page, int size) {

        Page<CouponDO> pageInfo = new Page<>(page, size);

        IPage<CouponDO> couponDOIPage = couponMapper.selectPage(pageInfo, new QueryWrapper<CouponDO>()
                .eq("publish", CouponPublishEnum.PUBLISH)
                .eq("category", CouponCategoryEnum.PROMOTION)
                .orderByDesc("create_time"));


        Map<String, Object> pageMap = new HashMap<>(3);
        //总条数
        pageMap.put("total_record", couponDOIPage.getTotal());
        //总页数
        pageMap.put("total_page", couponDOIPage.getPages());

        pageMap.put("current_data", couponDOIPage.getRecords().stream().map(obj -> beanProcess(obj)).collect(Collectors.toList()));


        return pageMap;
    }

    /**
     * 领取优惠券
     * 1、获取优惠券是否存在
     * 2、检验优惠券是否可以领取： 时间 、库存 、超过限制
     * 3、扣减库存
     * 4、保存领取记录
     *
     * @param couponId
     * @param category
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JsonData addCoupon(long couponId, CouponCategoryEnum category) {


//        String uuid = CommonUtil.generateUUID();
//        String lockKey = "lock:coupon:"+couponId;
//        Boolean lockFlag = redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, Duration.ofMinutes(30));
//
//        if (lockFlag) {
//            //加锁成功
//            log.info("加锁成功:{}",couponId);
//            try{
//
//                //执行业务逻辑
//                CouponDO couponDO = couponMapper.selectOne(new QueryWrapper<CouponDO>()
//                        .eq("id", couponId)
//                        .eq("category", category.name())
//                        .eq("publish", CouponPublishEnum.PUBLISH));
//                if (couponDO == null) {
//                    throw new BizException(BizCodeEnum.COUPON_NO_EXITS);
//                }
//
//                //优惠券是否可以领取
//                this.checkCoupon(couponDO, loginUser.getId());
//
//                //构建领券记录
//                CouponRecordDO couponRecordDO = new CouponRecordDO();
//                BeanUtils.copyProperties(couponDO,couponRecordDO);
//                couponRecordDO.setCreateTime(new Date());
//                couponRecordDO.setUseState(CouponStateEnum.NEW.name());
//                couponRecordDO.setUserId(loginUser.getId());
//                couponRecordDO.setUserName(loginUser.getName());
//                couponRecordDO.setCouponId(couponId);
//                couponRecordDO.setId(null);
//
//                //   扣减库存
//                int rows =couponMapper.reduceStock(couponId);
//
//                if (rows == 1) {
//                    //库存扣减成功才保存记录
//                    couponRecordMapper.insert(couponRecordDO);
//                }else {
//                    log.warn("优惠券发放失败:{},用户:{}",couponDO,loginUser);
//                    throw new BizException(BizCodeEnum.COUPON_NO_STOCK);
//                }
//
//            }finally {
//                //释放锁
//                String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
//
//                Integer result = redisTemplate.execute(new DefaultRedisScript<>(script, Integer.class), Arrays.asList(lockKey), uuid);
//                log.info("解锁：{}",result);
//            }
//
//        }else {
//            //加锁失败
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //自旋
//            addCoupon(couponId,category);
//        }

        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        String lockKey = "lock:coupon:" + couponId;

        RLock lock = redissonClient.getLock(lockKey);

        //多线程进入会阻塞
        lock.lock();
        try {

            CouponDO couponDO = couponMapper.selectOne(new QueryWrapper<CouponDO>()
                    .eq("id", couponId)
                    .eq("category", category.name())
                    .eq("publish", CouponPublishEnum.PUBLISH));
            if (couponDO == null) {
                throw new BizException(BizCodeEnum.COUPON_NO_EXITS);
            }

            //优惠券是否可以领取
            this.checkCoupon(couponDO, loginUser.getId());

            //构建领券记录
            CouponRecordDO couponRecordDO = new CouponRecordDO();
            BeanUtils.copyProperties(couponDO, couponRecordDO);
            couponRecordDO.setCreateTime(new Date());
            couponRecordDO.setUseState(CouponStateEnum.NEW.name());
            couponRecordDO.setUserId(loginUser.getId());
            couponRecordDO.setUserName(loginUser.getName());
            couponRecordDO.setCouponId(couponId);
            couponRecordDO.setId(null);

            //   扣减库存
            int rows = couponMapper.reduceStock(couponId);

            if (rows == 1) {
                //库存扣减成功才保存记录
                couponRecordMapper.insert(couponRecordDO);
            } else {
                log.warn("优惠券发放失败:{},用户:{}", couponDO, loginUser);
                throw new BizException(BizCodeEnum.COUPON_NO_STOCK);
            }
        } finally {
            lock.unlock();
        }

        return JsonData.buildSuccess();
    }

    /**
     * 新用户注册发放优惠券
     * <p>
     * 用户微服务调用的时候，没传递token
     * 本地直接调用优惠券的方法，需要调用一个登录用户存储在threadlocal中
     *
     * @param newUserCouponRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JsonData initNewUserCoupon(NewUserCouponRequest newUserCouponRequest) {

        LoginUser loginUser = new LoginUser();
        loginUser.setId(newUserCouponRequest.getUserId());
        loginUser.setName(newUserCouponRequest.getName());
        LoginInterceptor.threadLocal.set(loginUser);

        //查询新用户有哪些新人券
        List<CouponDO> couponDOList = couponMapper.selectList(new QueryWrapper<CouponDO>()
                .eq("category", CouponCategoryEnum.NEW_USER.name()));

        for (CouponDO couponDO : couponDOList) {
            //幂等性
            this.addCoupon(couponDO.getId(), CouponCategoryEnum.NEW_USER);
        }

        return JsonData.buildSuccess();
    }

    /**
     * 检查是否可以领取
     *
     * @param couponDO
     * @param userId
     */
    private void checkCoupon(CouponDO couponDO, Long userId) {

        //库存是否足够
        if (couponDO.getStock() <= 0) {
            throw new BizException(BizCodeEnum.COUPON_NO_STOCK);
        }

        //是否在可以领取时间内
        long time = CommonUtil.getCurrentTimestamp();
        long start = couponDO.getStartTime().getTime();
        long end = couponDO.getEndTime().getTime();
        if (time < start || time > end) {
            throw new BizException(BizCodeEnum.COUPON_OUT_OF_TIME);
        }

        //用户是否超过限制
        int recordNum = couponRecordMapper.selectCount(new QueryWrapper<CouponRecordDO>()
                .eq("coupon_id", couponDO.getId())
                .eq("user_id", userId));

        if (recordNum >= couponDO.getUserLimit()) {
            throw new BizException(BizCodeEnum.COUPON_OUT_OF_LIMIT);
        }
    }

    private CouponVO beanProcess(CouponDO couponDO) {
        CouponVO couponVO = new CouponVO();
        BeanUtils.copyProperties(couponDO, couponVO);
        return couponVO;
    }
}
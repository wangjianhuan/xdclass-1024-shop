package net.xdclass.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.feign.CouponFeignService;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.mapper.UserMapper;
import net.xdclass.model.LoginUser;
import net.xdclass.model.UserDO;
import net.xdclass.request.NewUserCouponRequest;
import net.xdclass.request.UserLoginRequest;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.service.NotifyService;
import net.xdclass.service.UserService;
import net.xdclass.utils.CommonUtil;
import net.xdclass.utils.JWTUtil;
import net.xdclass.utils.JsonData;
import net.xdclass.VO.UserVO;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author WJH
 * @class xdclass-1024-shop UserServiceImpl
 * @date 2021/7/12 下午10:33
 * @QQ 1151777592
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private NotifyService notifyService;


    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * * 邮箱验证码验证
     * * 密码加密
     * * 账号唯一性检查
     * * 插入数据库
     * * 新注册用户福利发放
     *
     * @param registerRequest
     * @return
     */
    @Override
//    @GlobalTransactional
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JsonData register(UserRegisterRequest registerRequest) {

        boolean checkCode = false;
        //校验验证码
        if (StringUtils.isNotBlank(registerRequest.getMail())) {
            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, registerRequest.getMail(), registerRequest.getCode());
        }

        if (!checkCode) {
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(registerRequest, userDO);

        userDO.setCreateTime(new Date());
        if (userDO.getSlogan()==null) {
            userDO.setSlogan("人生需要动态规划，学习需要贪心算法");
        }
        //设置密码
        //生成秘钥
        userDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));
        //密码 + 加盐处理
        String cryptPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(), userDO.getSecret());
        userDO.setPwd(cryptPwd);

        // 账号唯一性检查

        if (checkUnique(userDO.getMail())) {
            int rows = userMapper.insert(userDO);
            log.info("rows:{},注册成功:{}", rows, userDO.toString());

            //  新用户注册成功，初始化信息，发放福利等
            userRegisterInitTask(userDO);

            //模拟异常
//            int b = 1/0;
            return JsonData.buildSuccess();
        } else {
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_REPEAT);
        }

    }

    /**
     * 1、根据mail去DB查找有没有这条数据
     * 2、有的话验证密码 没有就返回没注册
     *
     * @param userLoginRequest
     * @return
     */
    @Override
    public JsonData login(UserLoginRequest userLoginRequest) {

        List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<UserDO>().eq("mail", userLoginRequest.getMail()));

        if (userDOList != null && userDOList.size() == 1) {
            //已经注册
            UserDO userDO = userDOList.get(0);
            String crypt = Md5Crypt.md5Crypt(userLoginRequest.getPwd().getBytes(), userDO.getSecret());
            if (crypt.equals(userDO.getPwd())) {
                //登录成功
                LoginUser loginUser = LoginUser.builder().build();
                BeanUtils.copyProperties(userDO, loginUser);

                String token = JWTUtil.geneJsonWebToken(loginUser);


                return JsonData.buildSuccess(token);
            } else {
                return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
            }
        } else {
            //未注册
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
        }

    }

    /**
     * 查询用户详情
     *
     * @return
     */
    @Override
    public UserVO findUserDetail() {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("id", loginUser.getId()));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
    }

    /**
     * 校验用户账号唯一
     *
     * @param mail
     * @return
     */
    private boolean checkUnique(String mail) {

        return true;
    }


    /**
     * 用户注册，初始化福利信息
     *
     * @param userDO
     */
    private void userRegisterInitTask(UserDO userDO) {

        NewUserCouponRequest request = new NewUserCouponRequest();
        request.setUserId(userDO.getId());
        request.setName(userDO.getName());
        JsonData jsonData = couponFeignService.addNewUserCoupon(request);
        log.info("发放新用户注册优惠券:{},结果:{}",request.toString(),jsonData.toString());

    }
}

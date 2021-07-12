package net.xdclass.service;

import net.xdclass.request.UserRegisterRequest;
import net.xdclass.utils.JsonData;

/**
 * @author WJH
 * @class xdclass-1024-shop UserService
 * @date 2021/7/12 下午10:32
 * @QQ 1151777592
 */
public interface UserService {

    /**
     * 用户注册接口
     * @param registerRequest
     * @return
     */
    JsonData register(UserRegisterRequest registerRequest);
}
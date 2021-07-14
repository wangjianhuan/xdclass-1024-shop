package net.xdclass.service;

import net.xdclass.model.AddressDO;
import net.xdclass.request.AddressAddRequest;

/**
 * @author WJH
 * @class xdclass-1024-shop AddressService
 * @date 2021/7/11 下午12:03
 * @QQ 1151777592
 */
public interface AddressService {

    /**
     * 查找用户
     * @param id
     * @return
     */
    AddressDO detail(Long id);

    /**
     * 新增收货地址
     * @param addressAddRequest
     */
    void add(AddressAddRequest addressAddRequest);
}

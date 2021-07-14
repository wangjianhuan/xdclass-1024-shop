package net.xdclass.service;

import net.xdclass.request.AddressAddRequest;
import net.xdclass.vo.AddressVO;

import java.util.List;

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
    AddressVO detail(Long id);

    /**
     * 新增收货地址
     * @param addressAddRequest
     */
    void add(AddressAddRequest addressAddRequest);

    /**
     * 根据ID删除地址
     * @param addressId
     * @return
     */
    int del(int addressId);

    /**
     * 查找用户全部收货地址
     * @return
     */
    List<AddressVO> listUserAllAddress();
}

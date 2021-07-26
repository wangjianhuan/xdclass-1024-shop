package net.xdclass.component;

import net.xdclass.VO.PayInfoVO;

/**
 * @author WJH
 * @date 2021/7/26 下午11:11
 * @QQ 1151777592
 */
public interface PayStrategy {


    /**
     * 下单
     *
     * @return
     */
    String unifiedorder(PayInfoVO payInfoVO);


    /**
     * 退款
     *
     * @param payInfoVO
     * @return
     */
    default String refund(PayInfoVO payInfoVO) {
        return "";
    }


    /**
     * 查询支付是否成功
     *
     * @param payInfoVO
     * @return
     */
    default String queryPaySuccess(PayInfoVO payInfoVO) {
        return "";
    }


}


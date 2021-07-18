package net.xdclass.constant;

/**
 * @author WJH
 * @class xdclass-1024-shop CacheKey
 * @date 2021/7/12 上午10:59
 * @QQ 1151777592
 */
public class CacheKey {

    /**
     * 注册验证码 第一个类型 第二个接受号码
     */
    public static final String CHECK_CODE_KEY = "code:%s:%s";

    /**
     * 购物车 HASH 结构 ，key是用户唯一标识
     */
    public static final String CART_KEY = "cart:%s";
}

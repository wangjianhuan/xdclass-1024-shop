package net.xdclass.enums;

/**
 * @author WJH
 * @date 2021/7/14 上午10:36
 * @QQ 1151777592
 */
public enum AddressStatusEnum {

    /**
     * 是默认收货地址
     */
    DEFAULT_STATUS(1),

    /**
     * 非默认收货地址
     */
    COMMON_STATUS(0);

    private int status;

    private AddressStatusEnum(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

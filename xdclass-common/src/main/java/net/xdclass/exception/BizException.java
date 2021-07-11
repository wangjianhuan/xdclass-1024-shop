package net.xdclass.exception;

import lombok.Data;
import net.xdclass.enums.BizCodeEnum;

/**
 * @author WJH
 * @class xdclass-1024-shop BizException
 * @date 2021/7/11 下午1:42
 * @QQ 1151777592
 */
@Data
public class BizException extends RuntimeException{

    private int code;

    private String msg;

    public BizException(int code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();

    }
}

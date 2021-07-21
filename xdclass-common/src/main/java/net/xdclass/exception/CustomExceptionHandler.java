package net.xdclass.exception;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.utils.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WJH
 * @class xdclass-1024-shop ExceptionHandler
 * @date 2021/7/11 下午1:47
 * @QQ 1151777592
 */
//@ControllerAdvice
//@Slf4j
//public class CustomExceptionHandler {
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public JsonData handler(Exception e){
//        //判断是不是自定义异常
//        if (e instanceof  BizException){
//            BizException bizException = (BizException) e;
//            log.error("[业务异常{}]",e);
//            return JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMsg());
//        }else {
//            log.error("[系统异常{}]",e);
//            return JsonData.buildError("全局异常，未知错误");
//        }
//    }
//}

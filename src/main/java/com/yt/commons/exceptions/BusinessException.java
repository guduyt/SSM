package com.yt.commons.exceptions;

import com.yt.commons.utils.MessageUtil;

/**
 * BusinessException
 * 业务异常
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/29 16:04
 */
public class BusinessException extends BaseException{
    public BusinessException(int messageCode,String message){
        super(messageCode,message);
    }

    public BusinessException(int messageCode,String messageTemplate ,Object... args){
        super(messageCode, MessageUtil.generate(messageTemplate, args));
    }
}

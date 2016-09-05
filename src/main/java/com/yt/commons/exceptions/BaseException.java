package com.yt.commons.exceptions;

import com.yt.commons.utils.MessageUtil;

/**
 * BaseException
 * 自定义异常基类
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 10:44
 */
public class BaseException extends RuntimeException {

    /*异常的错误码*/
    private int messageCode;

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public BaseException(){
        super("自定义异常，请联系相关工程师");
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(int messageCode,String message){
        super(MessageUtil.generate(messageCode, message));
        this.messageCode=messageCode;
    }


    public BaseException(String message,Throwable cause){
        super(message,cause);
    }
    public BaseException(int messageCode,String message,Throwable cause){
        super(MessageUtil.generate(messageCode, message),cause);
        this.messageCode=messageCode;
    }


    public BaseException(Throwable cause){
        super(cause);
    }
    public BaseException(int messageCode,Throwable cause){
        super(cause);
        this.messageCode=messageCode;
    }

}

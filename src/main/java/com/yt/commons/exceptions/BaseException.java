package com.yt.commons.exceptions;

/**
 * BaseException
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 10:44
 */
public class BaseException extends RuntimeException {



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
        super(message);
        this.messageCode=messageCode;
    }

    public BaseException(int messageCode,String message,Throwable cause){
        super(message,cause);
        this.messageCode=messageCode;
    }

    public BaseException(int messageCode,Throwable cause){
        super(cause);
        this.messageCode=messageCode;
    }
}

package com.yt.commons.exceptions;

import com.yt.commons.utils.MessageUtils;

/**
 * BaseException
 * 自定义异常基类
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 10:44
 */
public class BaseException extends RuntimeException {

    /*异常的错误码*/
    private int code;


    public BaseException() {
        super("自定义异常，请联系相关工程师");
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code) {
        super("自定义异常，请联系相关工程师");
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(MessageUtils.generate(code, message));
        this.code = code;
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(int code, String message, Throwable cause) {
        super(MessageUtils.generate(code, message), cause);
        this.code = code;
    }


    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

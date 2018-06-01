package com.yt.commons.exceptions;

/**
 * ValidateException
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/5/17 23:21
 */
public class ValidateException extends BusinessException {
    private static final long serialVersionUID =1L;

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(int code) {
        super(code);
    }

    public ValidateException(int code, String message) {
        super(code, message);
    }
}

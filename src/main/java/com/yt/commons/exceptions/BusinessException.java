package com.yt.commons.exceptions;

import com.yt.commons.utils.MessageUtils;

/**
 * BusinessException
 * 业务异常
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/29 16:04
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID =1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code) {
        super(code);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code, String messageTemplate, Object... args) {
        super(code, MessageUtils.generate(messageTemplate, args));
    }
}

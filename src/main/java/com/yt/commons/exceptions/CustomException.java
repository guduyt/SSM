package com.yt.commons.exceptions;

import com.yt.commons.utils.MessageUtils;

/**
 * 自定义异常
 * Created by yt on 2016/8/26.
 */
public class CustomException extends BaseException {
    private static final long serialVersionUID =1L;

    public CustomException(int messageCode, String message) {
        super(messageCode, message);
    }

    public CustomException(int messageCode, String message, Throwable cause) {
        super(messageCode, message, cause);
    }

    public CustomException(int messageCode, String messageTemplate, Object... args) {
        super(messageCode, MessageUtils.generate(messageTemplate, args));
    }
}

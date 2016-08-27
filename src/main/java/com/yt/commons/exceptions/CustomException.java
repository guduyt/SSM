package com.yt.commons.exceptions;

/**
 * Created by yt on 2016/8/26.
 */
public class CustomException extends BaseException {

    public  CustomException(String message){
        super(message);
    }

    public CustomException(int messageCode,String message){
        super(messageCode,message);
    }
}

package com.yt.interceptor.exception;

import com.alibaba.fastjson.JSON;
import com.yt.commons.exceptions.BaseException;
import com.yt.commons.utils.ResponseUtil;
import com.yt.commons.utils.Util;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * ExceptionInterceptor
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 9:29
 */
public class ExceptionInterceptor extends AbstractHandlerMethodExceptionResolver {

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod, Exception e) {
        httpServletResponse.setStatus(500);
        Class exClass=e.getClass();

        //如果不是自定义错误或者方法的参数错误，写入错误日志
        if(!ClassUtils.isAssignable(exClass, IllegalArgumentException.class))
            Util.log.error(e.getMessage(),e);

        String message="";
        if (ClassUtils.isAssignable(exClass, BaseException.class)) { //自定义异常
            message= e.getMessage();

        }else  if(ClassUtils.isAssignable(exClass, IllegalArgumentException.class)) {//方法的参数错误
            message=((IllegalArgumentException)e).getMessage() ;

        }/*else if ( ClassUtils.isAssignable(exClass,AuthenticationException.class)
                || ClassUtils.isAssignable(exClass,AccessDeniedException.class)){
            message="登录失败，用户名或密码错误！" ;
        }*/
        else if (ClassUtils.isAssignable(exClass,NullPointerException.class) ||ClassUtils.isAssignable(exClass,ArrayIndexOutOfBoundsException.class)) { //未经初始化的对象
            message="运行时访问数据失败！" ;

        }else if (ClassUtils.isAssignable(exClass,DataAccessException.class)) { //数据库操作
            message="运行时操作数据库失败！" ;

        }else if (ClassUtils.isAssignable(exClass,ArithmeticException.class)) {//数学运算异常
            message="运行数据运算失败！" ;

        }else if (ClassUtils.isAssignable(exClass,ClassCastException.class)) {//类型强制转换错误
            message="运行数据转换失败！" ;

        }else {
            if (ClassUtils.isAssignable(exClass,IOException.class)) {//IO异常
                message="系统异常，读取数据失败,请稍后重试！";
            }else  if (ClassUtils.isAssignable(exClass,SQLException.class)) {//操作数据库异常
                message="系统异常，操作数据库失败,请稍后重试！";
            }else if (ClassUtils.isAssignable(exClass,ClassNotFoundException.class)) {//类不存在
                message="系统异常，程序加载类失败,请联系工程师！";
            }else if (ClassUtils.isAssignable(exClass,NoSuchMethodError.class)) {//方法末找到异常
                message="系统异常，程序加载类方法失败,请联系工程师！";
            }else if (ClassUtils.isAssignable(exClass,InternalError.class)) {//Java虚拟机发生了内部错误
                message="应用程序要崩溃了,请联系工程师！";
            } else   { //程序内部未知错误
                message="应用程序内部未知错误,请联系工程师！";
            }
        }

        //对于Ajax请求与页面请求区别处理
        if(isAjaxRequest(httpServletRequest)){
            Map map=new HashMap<>();
            map.put("success",Boolean.valueOf(false));
            map.put("message",message);

            ResponseUtil.renderJson(httpServletResponse, JSON.toJSONString(map));
        }
        else {
            ModelAndView modelAndView=new ModelAndView("redirect:/www/error/500.html");
            modelAndView.getView();
            modelAndView.addObject("message",message) ;
            return modelAndView;
        }

        return null;
    }


    private Boolean isAjaxRequest(HttpServletRequest request){
         return StringUtils.isNotBlank(request.getHeader("X-Requested-With"));
    }
}

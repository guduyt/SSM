package com.yt.interceptor.exception;

import com.alibaba.fastjson.JSON;
import com.yt.commons.exceptions.BaseException;
import com.yt.commons.utils.LogUtils;
import com.yt.commons.utils.ResponseUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ExceptionInterceptor
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 9:29
 */
@ControllerAdvice
public class ExceptionInterceptor extends AbstractHandlerMethodExceptionResolver {

    private int code = -1;
    private String message = "未知错误,请联系工程师！";

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod, Exception e) {
        httpServletResponse.setStatus(500);
        Class exClass = e.getClass();

        //如果不是自定义错误或者方法的参数错误，写入错误日志
        if (!ClassUtils.isAssignable(exClass, IllegalArgumentException.class))
            LogUtils.LOGGER.error(e.getMessage(), e);

        if (ClassUtils.isAssignable(exClass, BaseException.class)) { //自定义异常
            code = ((BaseException) e).getCode();
            message = e.getMessage();

        } else if (ClassUtils.isAssignable(exClass, IllegalArgumentException.class)) {//方法的参数错误
            message = e.getMessage();

        } else if (ClassUtils.isAssignable(exClass, NullPointerException.class) || ClassUtils.isAssignable(exClass, ArrayIndexOutOfBoundsException.class)) { //未经初始化的对象
            message = "访问数据失败！";

        } else if (ClassUtils.isAssignable(exClass, DataAccessException.class)) { //数据库操作
            message = "操作数据库失败！";

        } else if (ClassUtils.isAssignable(exClass, ArithmeticException.class)) {//数学运算异常
            message = "数据运算失败！";

        } else if (ClassUtils.isAssignable(exClass, ClassCastException.class)) {//类型强制转换错误
            message = "数据转换失败！";

        } else {

            message = "应用程序内部未知错误,请联系工程师！";
        }

        return this.handleResponse(httpServletRequest, httpServletResponse, handlerMethod, code, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleMethodArgumentNotValidException(
            MethodArgumentNotValidException validationException, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod) {
        httpServletResponse.setStatus(400);
        BindingResult bindingResult = validationException.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            message = fieldError.getDefaultMessage();
            code = 9;
        }
        return this.handleResponse(httpServletRequest, httpServletResponse, handlerMethod, code, message);

    }

    private ModelAndView handleResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod, int code, String message) {
        if (isAjaxRequest(httpServletRequest, handlerMethod)) {
            Map map = new HashMap<>();
            map.put("code", code);
            map.put("message", message);

            ResponseUtils.renderJson(httpServletResponse, JSON.toJSONString(map));
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/www/error/500.html");
            modelAndView.getView();
            modelAndView.addObject("code", code);
            modelAndView.addObject("message", message);
            return modelAndView;
        }
        return null;
    }

    private Boolean isAjaxRequest(HttpServletRequest request, HandlerMethod handlerMethod) {
        if (null != handlerMethod) {
            if (null != handlerMethod.getBean().getClass().getDeclaredAnnotation(RestController.class))
                return true;
            if (null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {
                return true;
            }
        }
        if (request.getHeader("accept").contains("application/json"))
            return true;
        return StringUtils.isNotBlank(request.getHeader("X-Requested-With"));
    }
}

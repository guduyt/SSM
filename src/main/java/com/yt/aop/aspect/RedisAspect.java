package com.yt.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * RedisAspect
 * 基于拦截器拦截缓存请求，并打印日志
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/13 10:31
 */
@Component
@Aspect
public class RedisAspect {


    @After("execution(* com.yt.commons.cache.IRedis.*(..)) && execution(public * *(..)) && !execution(public * getJedis(..))")
    public void setAfter(JoinPoint joinPoint) {
        String s="";
        for (int i=0 ;i<joinPoint.getArgs().length;i++){
           s+=  joinPoint.getArgs()[i].toString() +" ";
        }
    }
}

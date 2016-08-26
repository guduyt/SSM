package com.yt.aop.aspect;

import com.yt.commons.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * RedisAspect
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/13 10:31
 */
@Component
@Aspect
public class RedisAspect {


   /* @Before("execution(* com.yt.commons.cache.RedisCache.*(..))")
    public void setBefore(JoinPoint joinPoint) {
        redisCache.setJedisConnection((JedisConnection) redisCache.getJedisConnectionFactory().getConnection());
    }*/

    @After("execution(* com.yt.commons.cache.IRedis.*(..)) && execution(public * *(..)) && !execution(public * getJedis(..))")
    public void setAfter(JoinPoint joinPoint) {
        String s="";
        for (int i=0 ;i<joinPoint.getArgs().length;i++){
           s+=  joinPoint.getArgs()[i].toString() +" ";
        }
        DateUtils.log.info(joinPoint.toString()+s);
    }
}

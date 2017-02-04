package com.yt.aop.aspect;

import com.yt.mybatis.model.BaseModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by yt on 2017-1-24.
 */
@Component
@Aspect
public class DalAspect {
    private static final String CREATOR = "creator";
    private static final String EDITOR = "editor";

    private static final String CREATE_TIME = "createTime";
    private static final String EDIT_TIME = "editTime";

    @Pointcut("execution(* com.yt.mybatis.model.BaseMapper.insert*(..))")
    public void insertPointcut() {
        //声明插入操作拦截点
    }


    @Pointcut("execution(* com.yt.mybatis.model.BaseMapper.update*(..))")
    public void updatePointcut() {
        //声明更新操作拦截点
    }


    @Before("updatePointcut()")
    public void beforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0)
            return;

        if (args[0] instanceof BaseModel) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(args[0]);
            if (beanWrapper.isWritableProperty(EDITOR)) {
                beanWrapper.setPropertyValue(EDITOR, "admin_editor");
            }
            if (beanWrapper.isWritableProperty(EDIT_TIME)) {
                beanWrapper.setPropertyValue(EDIT_TIME, null);
            }
        } else if (args[0] instanceof Map) {
            Map map = (Map) args[0];
            if (map.containsKey(EDITOR)) {
                map.put(EDITOR, "admin_editor");
            }

            if (map.containsKey(EDIT_TIME)) {
                map.put(EDIT_TIME, null);
            }
        }
    }

    @Before("insertPointcut()")
    public void beforeInsert(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0)
            return;

        if (args[0] instanceof BaseModel) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(args[0]);
            if (beanWrapper.isWritableProperty(CREATOR)) {
                beanWrapper.setPropertyValue(CREATOR, "admin");
            }
            if (beanWrapper.isWritableProperty(CREATE_TIME)) {
                beanWrapper.setPropertyValue(CREATE_TIME, null);
            }
        } else if (args[0] instanceof Map) {
            Map map = (Map) args[0];
            if (map.containsKey(CREATOR)) {
                map.put(CREATOR, "admin");
            }
            if (map.containsKey(CREATE_TIME)) {
                map.put(CREATE_TIME, null);
            }
        }
    }
}

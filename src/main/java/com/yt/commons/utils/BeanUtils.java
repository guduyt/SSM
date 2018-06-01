package com.yt.commons.utils;

import com.yt.commons.exceptions.BusinessException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yt on 2017-1-23.
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    private BeanUtils() {

    }

    public static void copy(Object source, Object target) {
        try {
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        } catch (Exception ex) {
            throw new BusinessException("数据对象属性类型转换错误", ex);
        }
    }

    public static <S, T> List<T> copy(List<S> source, Class<T> targetClass) {
        Assert.notNull(targetClass, "目标对象类型不能为空");

        List<T> list = new ArrayList<T>();
        if (source == null || source.isEmpty()) {
            return list;
        }

        try {
            for (S s : source) {
                if (s == null) {
                    list.add(null);
                } else {
                    T t = targetClass.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(s, t);
                    list.add(t);
                }
            }
            return list;

        } catch (Exception ex) {
            throw new BusinessException("数据对象属性类型转换错误", ex);
        }
    }
}

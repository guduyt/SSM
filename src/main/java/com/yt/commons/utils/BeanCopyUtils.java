package com.yt.commons.utils;

import com.yt.commons.exceptions.BusinessException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Created by yt on 2017-1-23.
 */
public class BeanCopyUtils {
    private BeanCopyUtils() {

    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception ex) {
            throw new BusinessException("数据对象属性类型转换错误", ex);
        }
    }

    public static void copyProperty(Object bean, String name, Object value) {
        try {
            BeanUtils.copyProperty(bean, name, value);
        } catch (Exception ex) {
            throw new BusinessException("数据对象属性类型转换错误", ex);
        }
    }
}

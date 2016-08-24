package com.yt.commons;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContextUtils
 *获取spring注册bean的容器
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/20 11:39
 */
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @SuppressWarnings ("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.context= applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     *  根据一个bean的类型获取配置文件中相应的bean
     * @param tClass
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static  <T> T getBeanByClass(Class<T> tClass)throws BeansException{
           return context.getBean(tClass);
    }

    /**
     * 根据一个bean的id获取配置文件中相应的bean
     * @param beanName
     * @param <T>
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) throws BeansException{
        return (T)context.getBean(beanName);
    }

    /**
     *根据所给名称匹配的bean定义，存在则返回true
     * @param name
     * @return
     */
    public static Boolean existBean(String name){
           return context.containsBean(name);
    }

    /**
     *  判断以给定名字注册的bean定义是singleton。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static Boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return  context.isSingleton(name);
    }

    /**
     *  判断以给定名字注册的bean定义是prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static Boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return  context.isPrototype(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name
     * @return
     */
    public static String[] getAliases(String name){
        return context.getAliases(name);
    }
}

package com.lefanfs.apicenter.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by fanshuai on 16/11/26.
 */
public class ServiceLocator implements ApplicationContextAware {
    private static ApplicationContext c;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        c = applicationContext;
    }

    public static <T> T getBean(Class<T> t){
        return (T)c.getBean(t);
    }

    public static Object getBean(String beanName){
        return c.getBean(beanName);
    }
}

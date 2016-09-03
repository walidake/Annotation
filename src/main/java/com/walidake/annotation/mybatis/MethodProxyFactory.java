package com.walidake.annotation.mybatis;

import java.lang.reflect.Proxy;

/**
 * 动态代理工厂
 * @author walidake
 *
 */
public class MethodProxyFactory {
	
    @SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
        final MethodProxy methodProxy = new MethodProxy();
        return (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(), 
                new Class[]{clazz}, 
                methodProxy);
    }
    
}

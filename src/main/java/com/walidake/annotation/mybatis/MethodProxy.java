package com.walidake.annotation.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 方法动态代理
 * 
 * @author walidake
 *
 */
public class MethodProxy implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] parameters)
			throws Throwable {
		return DaoOperatorFactory.handle(method, parameters);
	}

}
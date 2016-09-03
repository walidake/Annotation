package com.walidake.annotation.mybatis;

import org.junit.Test;

/**
 * 自定义mybatis测试样例
 * 
 * @author walidake
 *
 */
public class MybatisTest {
	
	@Test
	public void addUser(){
		UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
    	mapper.addUser("walidake", "665908");
	}
	
	@Test
	public void findUsers(){
		UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
    	System.out.println(mapper.findUsers());
	}
	
	@Test
	public void getUser(){
		UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
    	System.out.println(mapper.getUser("walidake"));
	}
	
	@Test
	public void updateUser(){
		UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
    	mapper.updateUser("123456", "walidake");
	}
	
	@Test
	public void deleteUser(){
		UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
    	mapper.deleteUser("walidake");
	}
}

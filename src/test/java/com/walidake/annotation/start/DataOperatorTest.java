package com.walidake.annotation.start;

import com.walidake.annotation.mybatis.MethodProxyFactory;
import com.walidake.annotation.mybatis.UserMapper;


public class DataOperatorTest{
	
	public static void main(String[] args) {
		UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
		mapper.addUser("walidake","665908");
	}
}

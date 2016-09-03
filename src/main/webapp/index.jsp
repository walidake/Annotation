<%@page import="com.walidake.annotation.mybatis.MethodProxyFactory"%>
<%@page import="com.walidake.annotation.mybatis.UserMapper"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<body>
<%
	UserMapper mapper = MethodProxyFactory.getBean(UserMapper.class);
	mapper.addUser("walidake","665908");
 %>
</body>
</html>

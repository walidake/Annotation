package com.walidake.annotation.mybatis;

import java.util.List;

public interface UserMapper {

	@Insert("insert into user (name,password) values (?,?)")
	public void addUser(String name, String password);

	@Select("select * from user")
	public List<User> findUsers();

	@Select("select * from user where name = ?")
	public User getUser(String name);

	@Update("update user set password=? where name=?")
	public void updateUser(String password, String name);

	@Delete("delete from user where name=?")
	public void deleteUser(String name);

}

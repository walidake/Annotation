/** 
 *
 * @ClassName: Person 
 *
 * @Description: TODO
 *
 * @author walidake
 *
 * @date 2016年6月14日 上午11:28:54 
 *
 **/
package com.walidake.annotation.hibernate;


/**
 * 测试数据
 * 
 * @author walidake
 *
 */
@Table
public class Person {
	@PrimaryKey
	private int id;
	
	@Column(isNull = false, length = 20)
	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

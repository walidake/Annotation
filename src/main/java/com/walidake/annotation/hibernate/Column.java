/** 
 *
 * @ClassName: Column 
 *
 * @Description: TODO
 *
 * @author walidake
 *
 * @date 2016年6月14日 上午10:52:35 
 *
 **/
package com.walidake.annotation.hibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	// 列名 默认为""
	String name() default "";

	// 长度 默认为255
	int length() default 255;

	// 是否为varchar 默认为true
	boolean varchar() default true;

	// 是否为空 默认可为空
	boolean isNull() default true;
}

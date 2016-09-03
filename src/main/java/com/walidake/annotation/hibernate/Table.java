/** 
	*
	* @ClassName: Table 
	*
	* @Description: TODO
	*
	* @author walidake
	*
	* @date 2016年6月14日 上午10:51:24 
	*
**/ 
package com.walidake.annotation.hibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 可根据需要自行定制功能
 * @author walidake
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	String name() default "";
}

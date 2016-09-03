/** 
	*
	* @ClassName: PrimaryKey 
	*
	* @Description: TODO
	*
	* @author walidake
	*
	* @date 2016年6月14日 下午5:35:11 
	*
**/ 
package com.walidake.annotation.hibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 有需要可以拆分成更小粒度
 * @author walidake
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrimaryKey {
	String name() default "";
}

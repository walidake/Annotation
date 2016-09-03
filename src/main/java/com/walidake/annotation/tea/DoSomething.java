package com.walidake.annotation.tea;


public @interface DoSomething {
	
	public String value();
	
	public String name() default "write";
	
}

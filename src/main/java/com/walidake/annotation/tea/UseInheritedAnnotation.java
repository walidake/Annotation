package com.walidake.annotation.tea;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Inheritable{
	
}

@Documented
@interface UnInheritable{
	
}

public class UseInheritedAnnotation{
    @UnInheritable
    @Inheritable
    public static class Super{
    	
    }
    
    public static class Sub extends  Super {
    	
    }
    
    /**
    public static void main(String... args){
    	
        Super instance=new Sub();
        System.out.println(Arrays.toString(instance.getClass().getAnnotations()));
    }
    **/
}

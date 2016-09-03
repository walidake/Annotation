package com.walidake.annotation.tea;

import java.lang.annotation.Documented;

@Documented
@interface DocumentedAnnotation{
	
}

@interface UnDocumentedAnnotation{
	
}

@DocumentedAnnotation
@UnDocumentedAnnotation
public class UseDocumentedAnnotation{}

package com.shopping.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log4Method {
    LogLevel logLevel() default LogLevel.DEBUG;
    Class classType() default Object.class;
    String[] excludeFieldName() default {};
}

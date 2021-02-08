package com.shopping.common.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log4Method {
    LogLevel logLevel() default LogLevel.DEBUG;
    Class classType() default Object.class;
    String[] excludeFieldName() default {};
}

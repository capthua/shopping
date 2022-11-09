package com.shopping.common.log.log4method;

import com.shopping.common.log.LogLevel;

import java.lang.annotation.*;

/**
 * 此注解添加到方法上,可以将方法的参数和返回值输出到日志里
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log4Method {
    LogLevel logLevel() default LogLevel.DEBUG;
    Class classType() default Object.class;
    String[] excludeFieldName() default {};
}

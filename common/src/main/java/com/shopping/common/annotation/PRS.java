package com.shopping.common.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PRS {

    String value() default "";

}

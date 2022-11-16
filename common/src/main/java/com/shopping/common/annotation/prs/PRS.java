package com.shopping.common.annotation.prs;

import java.lang.annotation.*;

/**
 * @author CaptHua
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PRS {

    String value() default "";

}

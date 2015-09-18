/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.aop.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切面类
 *
 * @author zhaohang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 包名
     */
    String pkg() default "";

    /**
     * 类名
     */
    String cls() default "";

    /**
     * 注解
     *
     * @since 2.2
     */
    Class<? extends Annotation> annotation() default Aspect.class;
}

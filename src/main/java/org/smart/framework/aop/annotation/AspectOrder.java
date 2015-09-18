/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切面顺序
 *
 * @author zhaohang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectOrder {

    int value();
}

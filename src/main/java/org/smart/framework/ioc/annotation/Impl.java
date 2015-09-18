/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定接口的实现类
 *
 * @author zhaohang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Impl {

    Class<?> value();
}

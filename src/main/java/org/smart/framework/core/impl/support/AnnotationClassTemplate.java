/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.core.impl.support;

import java.lang.annotation.Annotation;

/**
 * 用于获取注解类的模板类
 * 
 * @author zhaohang
 */
public abstract class AnnotationClassTemplate extends ClassTemplate {

	protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }

}

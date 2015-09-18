/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.core.impl.support;

/**
 * 用于获取子类的模板类
 * 
 * @author zhaohang
 */
public abstract class SupperClassTemplate extends ClassTemplate {

	protected final Class<?> superClass;

    protected SupperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}

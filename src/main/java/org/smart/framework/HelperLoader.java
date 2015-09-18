/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework;

import org.smart.framework.aop.AopHelper;
import org.smart.framework.dao.DatabaseHelper;
import org.smart.framework.ioc.BeanHelper;
import org.smart.framework.ioc.IocHelper;
import org.smart.framework.mvc.ActionHelper;
import org.smart.framework.mvc.VelocityHelper;
import org.smart.framework.plugin.PluginHelper;
import org.smart.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 * @author zhaohang
 */
public final class HelperLoader {

    public static void init() {
        // 定义需要加载的 Helper 类
        Class<?>[] classList = {
            DatabaseHelper.class,
            ActionHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            VelocityHelper.class,
            PluginHelper.class,
        };
        // 按照顺序加载类
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}

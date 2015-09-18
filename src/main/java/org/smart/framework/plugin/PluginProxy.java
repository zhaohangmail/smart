/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.plugin;

import java.util.List;
import org.smart.framework.aop.proxy.Proxy;

/**
 * 插件代理
 *
 * @author zhaohang
 */
public abstract class PluginProxy implements Proxy {

    public abstract List<Class<?>> getTargetClassList();
}

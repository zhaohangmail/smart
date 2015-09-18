/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.plugin;

import javax.servlet.ServletContext;

/**
 * 基于 Web 的插件抽象实现，拥有 Plugin 接口的所有功能
 * <br/>
 * 可在子类中注册 Servlet、Filter、Listener 等
 *
 * @author zhaohang
 */
public abstract class WebPlugin implements Plugin {

    public void init() {
    }

    public void destroy() {
    }

    public abstract void register(ServletContext servletContext);
}

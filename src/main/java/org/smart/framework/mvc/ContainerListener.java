/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.smart.framework.Constant;
import org.smart.framework.util.StringUtil;
import org.smart.framework.HelperLoader;
import org.smart.framework.plugin.Plugin;
import org.smart.framework.plugin.PluginHelper;
import org.smart.framework.plugin.WebPlugin;

/**
 * 容器监听器
 * 
 * @author zhaohang
 */
@WebListener
public class ContainerListener implements ServletContextListener {

	//初始化方法
	public void contextInitialized(ServletContextEvent sce) {
		 // 获取 ServletContext
        ServletContext servletContext = sce.getServletContext();
        // 初始化相关 Helper 类
        HelperLoader.init();
        // 添加 Servlet 映射
        addServletMapping(servletContext);
        // 注册 WebPlugin
        registerWebPlugin(servletContext);
	}
	
	//销毁方法
	public void contextDestroyed(ServletContextEvent arg0) {
		destroyPlugin();
	}

	private void addServletMapping(ServletContext context) {
        // 用 DefaultServlet 映射所有静态资源
        registerDefaultServlet(context);
        // 用 JspServlet 映射所有 JSP 请求
        //registerJspServlet(context);
    }

	private void registerDefaultServlet(ServletContext context) {
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        defaultServlet.addMapping("/favicon.ico");
        String resPath = Constant.RES_PATH;
        if (StringUtil.isNotEmpty(resPath)) {
            defaultServlet.addMapping(resPath + "*");
        }
    }

//    private void registerJspServlet(ServletContext context) {
//        ServletRegistration jspServlet = context.getServletRegistration("jsp");
//        jspServlet.addMapping("/index.jsp");
//        String jspPath = Constant.JSP_PATH;
//        if (StringUtil.isNotEmpty(jspPath)) {
//            jspServlet.addMapping(jspPath + "*");
//        }
//    }
    
    private void registerWebPlugin(ServletContext servletContext) {
        List<Plugin> pluginList = PluginHelper.getPluginList();
        for (Plugin plugin : pluginList) {
            if (plugin instanceof WebPlugin) {
                WebPlugin webPlugin = (WebPlugin) plugin;
                webPlugin.register(servletContext);
            }
        }
    }

    private void destroyPlugin() {
        List<Plugin> pluginList = PluginHelper.getPluginList();
        for (Plugin plugin : pluginList) {
            plugin.destroy();
        }
    }
    
}

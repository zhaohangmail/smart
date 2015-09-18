/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework;
import org.smart.framework.core.ConfigHelper;

/**
 * @author zhaohang
 *
 * 常量定义
 */
public interface Constant {
	
	//系统配置
	String UTF_8 = "UTF-8";
    String CONFIG_PROPS = "smart.properties";
    String SQL_PROPS = "sql.peroperties";
    
    String VELOCITY_CONFIG_PATH = ConfigHelper.getString("smart.app.velocity_config_path","velocity.properties");
    String VELOCITY_PATH = ConfigHelper.getString("smart.app.velocity_path","/WEB-INF/view/");
    String PLUGIN_PACKAGE = ConfigHelper.getString("smart.app.plugin", "plugin");
    String RES_PATH = ConfigHelper.getString("smart.app.res_path", "/res/");
    String UPLOAD_PATH = ConfigHelper.getString("smart.app.upload_path", "/upload/");
    String HOME_PAGE = ConfigHelper.getString("smart.app.home_page", "/index.html");
    int UPLOAD_LIMIT = ConfigHelper.getInt("smart.app.upload_limit", 10);
    
}
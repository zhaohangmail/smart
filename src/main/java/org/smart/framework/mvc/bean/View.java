/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.bean;

import java.util.HashMap;
import java.util.Map;
import org.smart.framework.core.bean.BaseBean;

/**
 * 封装视图对象
 *
 * @author zhaohang
 */
public class View extends BaseBean {

    private static final long serialVersionUID = 2618566229876110371L;
	private String path;              // 视图路径
    private Map<String, Object> data; // 相关数据

    public View(String path) {
        this.path = path;
        data = new HashMap<String, Object>();
    }

    public View data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

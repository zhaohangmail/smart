/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import org.smart.framework.util.CastUtil;


/**
 * 封装请求参数
 *
 * @author zhaohang
 */
public class Params extends LinkedHashMap<String, Object>{

	private static final long serialVersionUID = -4773781685332063693L;

	public Params() {}
	
	public Params(Map<String,Object> map) {
		super(map);
	}
	
	public String getString(String key) {
        return CastUtil.castString(get(key));
    }

    public double getDouble(String key) {
        return CastUtil.castDouble(get(key));
    }

    public long getLong(String key) {
        return CastUtil.castLong(get(key));
    }

    public int getInt(String key) {
        return CastUtil.castInt(get(key));
    }

    public Object get(String key) {
        return super.get(key);
    }
    
    public Object put(String key, Object value) {
        return super.put(key,value);
    }
}

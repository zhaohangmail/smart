/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc;

/**
 * 封装 Request 对象相关信息
 * 
 * @author zhaohang
 */
public class Requester {

	private String requestMethod;
	private String requestPath;
	
	public Requester(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }
	
	public String getRequestMethod() {
		return requestMethod;
	}
	
	public String getRequestPath() {
		return requestPath;
	}
	
}

/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.mvc.UploadHelper;
import org.smart.framework.mvc.VelocityHelper;
import org.smart.framework.mvc.ViewResolver;
import org.smart.framework.mvc.bean.Result;
import org.smart.framework.mvc.bean.View;
import org.smart.framework.util.WebUtil;

/**
 * 默认视图解析器
 *
 * @author zhaohang
 */
public class DefaultViewResolver implements ViewResolver {

	private static final Logger logger = LoggerFactory.getLogger(DefaultViewResolver.class);
	
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult) {
    	try {
    		if (actionMethodResult != null) {
    			// Action 返回值可为 View 或 Result
    			if (actionMethodResult instanceof View) {
    				// 若为 View，则需考虑两种视图类型（重定向 或 转发）
    				View view = (View) actionMethodResult;
    				// 获取路径
    				String path = view.getPath();
    				if (view.isRedirect()) {
    					// 重定向请求
    					WebUtil.redirectRequest(path, request, response);
    				} else {
    					// 初始化请求属性
    					Map<String, Object> data = view.getData();
    					// 转发请求
    					VelocityHelper.mergeTemplate(path, data, response);
    				}
    			} else {
    				// 若为 Result，则需考虑两种请求类型（文件上传 或 普通请求）
    				Result result = (Result) actionMethodResult;
    				if (UploadHelper.isMultipart(request)) {
    					// 对于 multipart 类型，说明是文件上传，需要转换为 HTML 格式并写入响应中
    					WebUtil.writeHTML(response, result);
    				} else {
    					// 对于其它类型，统一转换为 JSON 格式并写入响应中
    					WebUtil.writeJSON(response, result);
    				}
    			}
    		}
    	} catch (Exception e) {
    		logger.error(" 返回视图出错 ! ");
    		e.printStackTrace();
    	}
    }
}

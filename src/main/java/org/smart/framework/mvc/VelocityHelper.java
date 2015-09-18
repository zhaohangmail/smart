/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.Constant;
import org.smart.framework.util.MapUtil;
import org.smart.framework.util.PropsUtil;
import org.smart.framework.util.WebUtil;

/**
 * velocity 模板解析器
 *
 * @author zhaohang
 */
public class VelocityHelper{

	private static final Logger logger = LoggerFactory.getLogger(VelocityHelper.class);
	private static final VelocityEngine ve = new VelocityEngine();
	
	static {
		try {
			Properties configProp = PropsUtil.loadProps(Constant.VELOCITY_CONFIG_PATH);
			configProp.put("file.resource.loader.path", WebUtil.getRootPath() + Constant.VELOCITY_PATH);
			ve.init(configProp);
		} catch (Exception e) {
			logger.error(" 初始化velocity出错 ! ");
			throw new RuntimeException();
		}
	}
	
	public static void mergeTemplate(String templateName, Map<String,Object> data,  HttpServletResponse response) {
		VelocityContext context = new VelocityContext();
		if (MapUtil.isNotEmpty(data)) {
			for (Map.Entry<String, Object> entity : data.entrySet()) {
				context.put(entity.getKey(), entity.getValue());
			}
		}
		try {
			Template template = ve.getTemplate(templateName, Constant.UTF_8);
			template.merge(context, response.getWriter());
		} catch (Exception e) {
			logger.error(" 渲染页面 mergeTemplate 出错 ! ");
			e.printStackTrace();
		}
	}
	
	
}

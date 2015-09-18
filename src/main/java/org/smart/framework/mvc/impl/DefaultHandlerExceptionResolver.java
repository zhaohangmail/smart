/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.Constant;
import org.smart.framework.mvc.HandlerExceptionResolver;
import org.smart.framework.mvc.fault.AuthcException;
import org.smart.framework.mvc.fault.AuthzException;
import org.smart.framework.util.WebUtil;

/**
 * 默认 Handler 异常解析器
 *
 * @author zhaohang
 */
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultHandlerExceptionResolver.class);

    public void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        // 判断异常原因
        Throwable cause = e.getCause();
        if (cause == null) {
            logger.error(e.getMessage(), e);
            WebUtil.sendError(HttpServletResponse.SC_BAD_REQUEST, "", response);
        }
        if (cause instanceof AuthcException) {
            // 分两种情况进行处理
            if (WebUtil.isAJAX(request)) {
                // 跳转到 403 页面
                WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
            } else {
                // 重定向到首页
                WebUtil.redirectRequest(Constant.HOME_PAGE, request, response);
            }
        } else if (cause instanceof AuthzException) {
            // 跳转到 403 页面
            WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
        } else {
            // 跳转到 500 页面
            WebUtil.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cause.getMessage(), response);
        }
    }
}

/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.fault;

/**
 * 认证异常（当非法访问时抛出）
 *
 * @author zhaohang
 */
public class AuthcException extends RuntimeException {

    private static final long serialVersionUID = 4563005000961337888L;

	public AuthcException() {
        super();
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }
}

/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.fault;

/**
 * 授权异常（当权限无效时抛出）
 *
 * @author zhaohang
 */
public class AuthzException extends RuntimeException {

    private static final long serialVersionUID = 3688799171421663798L;

	public AuthzException() {
        super();
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }
}

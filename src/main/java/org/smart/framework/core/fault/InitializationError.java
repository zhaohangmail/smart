/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.core.fault;

/**
 * 初始化错误
 *
 * @author zhaohang
 */
public class InitializationError extends Error {

    private static final long serialVersionUID = -6777519863279632862L;

	public InitializationError() {
        super();
    }

    public InitializationError(String message) {
        super(message);
    }

    public InitializationError(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationError(Throwable cause) {
        super(cause);
    }
}

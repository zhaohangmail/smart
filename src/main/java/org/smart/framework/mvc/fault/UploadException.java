/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.fault;

/**
 * 上传异常（当文件上传失败时抛出）
 *
 * @author zhaohang
 */
public class UploadException extends RuntimeException {

    private static final long serialVersionUID = -5221865276372640893L;

	public UploadException() {
        super();
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }
}

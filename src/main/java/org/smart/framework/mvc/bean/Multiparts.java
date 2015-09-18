/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.mvc.bean;

import java.util.ArrayList;
import java.util.List;
import org.smart.framework.core.bean.BaseBean;

/**
 * 封装批量文件上传对象
 *
 * @author zhaohang
 */
public class Multiparts extends BaseBean {

    private static final long serialVersionUID = -754971981086465894L;
	private List<Multipart> multipartList = new ArrayList<Multipart>();

    public Multiparts(List<Multipart> multipartList) {
        this.multipartList = multipartList;
    }

    public int size() {
        return multipartList.size();
    }

    public List<Multipart> getAll() {
        return multipartList;
    }

    public Multipart getOne() {
        return size() == 1 ? multipartList.get(0) : null;
    }
}

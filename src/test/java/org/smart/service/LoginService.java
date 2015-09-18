/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.service;
import java.util.Map;

import org.smart.dao.LoginDao;
import org.smart.framework.mvc.bean.Params;
import org.smart.framework.tx.annotation.Service;

/**
 * 
 *
 * @author zhaohang
 */
@Service
public class LoginService {

	
	public Map<String,Object> login(Params param) {
		return new LoginDao().login(param);
	}
}

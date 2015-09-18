/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.dao;

import java.util.Map;

import org.smart.framework.dao.DatabaseHelper;
import org.smart.framework.mvc.bean.Params;


/**
 * 
 *
 * @author zhaohang
 */
public class LoginDao {

	public Map<String,Object> login(Params param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from users where id = :id ");
		Map<String,Object> map = DatabaseHelper.single(sql.toString(), param);
		return map;
	}
}

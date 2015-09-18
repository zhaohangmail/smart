/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.dao.bean;

import java.util.List;

/**
 * 
 *
 * @author zhaohang
 */
public class SqlEntity {
	
	private String sql;
	
	private List<Object> param;

	/**
	 * @param sql
	 * @param param
	 */
	public SqlEntity(String sql, List<Object> param) {
		super();
		this.sql = sql;
		this.param = param;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParam() {
		return param;
	}

	public void setParam(List<Object> param) {
		this.param = param;
	}
}

/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.dao;

import java.util.List;
import java.util.Map;

import org.smart.framework.dao.bean.Pager;


/**
 * 数据访问器
 * 
 * @author zhaohang
 * @param <T>
 */
public interface DataAccessor {
	
	/**
     * 查询单条记录
	 */
	Map<String,Object> single(String sql, List<Object> param);
	
    /**
     * 查询多条记录
     */
	List<Map<String,Object>> list(String sql, List<Object> param);
	
	/**
	 * 插入记录(单条或多条)
	 */
	int add(String sql,List<Object> param); 
	
    /**
     * 修改记录
     */
	int update(String sql, List<Object> param);

	/**
	 * 查询分页记录
	 * @param sql
	 * @return
	 */
	Pager<Map<String, Object>> pager(String sql, String countSql, List<Object> list, int page, int limit);
	
    /**
     * 查询对应的数据，返回多条记录
     */

    /**
     * 查询对应的数据，返回单条记录（列名 => 数据）
     */

    /**
     * 查询对应的数据，返回多条记录（列名 => 数据）
     */

    /**
     * 查询对应的数据，返回单条数据（列名 => 数据）
     */

    /**
     * 查询对应的数据，返回多条数据（列名 => 数据）
     */

    /**
     * 查询指定列名对应的数据，返回多条数据（列名对应的数据 => 列名与数据的映射关系）
     */

    /**
     * 查询记录条数，返回总记录数
     */

    /**
     * 执行更新操作（包括：update、insert、delete），返回所更新的记录数
     */

    /**
     * 插入一条记录，返回插入后的主键
     */
}

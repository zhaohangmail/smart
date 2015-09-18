/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.dao.bean.SqlEntity;
import org.smart.framework.util.CollectionUtil;
import org.smart.framework.util.MapUtil;
import org.smart.framework.util.StringUtil;

/**
 * 封装 SQL 语句相关操作
 *
 * @author zhaohang
 */
public class SqlHelper {

	private static final Logger logger = LoggerFactory.getLogger(SqlHelper.class);
	
    /**
     * 生成 select 语句
     * @param table 表名
     */
    public static String generateQuery(String table) {
    	String query = "";
    	if (StringUtil.isEmpty(table)) {
    		logger.error(" 表名table 不能为空 ! ");
    		throw new RuntimeException();
    	}
    	query += " select * from " + table ;
        return query;
    }

    /**
     * 生成 insert 语句
     */

    /**
     * 生成 select 分页语句（数据库类型为：mysql、oracle、mssql）
     */


    public static String generateWhere(String condition) {
        String where = "";
        if (StringUtil.isNotEmpty(condition)) {
            where += " where " + condition;
        }
        return where;
    }

    /**
     * 生成 order by 排序
     */
    public static String generateOrder(String sort) {
        StringBuffer order = new StringBuffer(20);
        if (StringUtil.isNotEmpty(sort)) {
        	order.append(" order by ").append(sort);
        }
        return order.toString();
    }

    public static void appendSqlForMySql(StringBuilder sql, String table, String where, String order, int pageStart, int pageEnd) {
        /*
            select * from 表名 where 条件 order by 排序 limit 开始位置, 结束位置
         */
        sql.append("select * from ").append(table);
        sql.append(where);
        sql.append(order);
        sql.append(" limit ").append(pageStart).append(", ").append(pageEnd);
    }

    public static void appendSqlForOracle(StringBuilder sql, String table, String where, String order, int pageStart, int pageEnd) {
        /*
            select a.* from (
                select rownum rn, t.* from 表名 t where 条件 order by 排序
            ) a
            where a.rn >= 开始位置 and a.rn < 结束位置
        */
        sql.append("select a.* from (select rownum rn, t.* from ").append(table).append(" t");
        sql.append(where);
        sql.append(order);
        sql.append(") a where a.rn >= ").append(pageStart).append(" and a.rn < ").append(pageEnd);
    }

    public static void appendSqlForMsSql(StringBuilder sql, String table, String where, String order, int pageStart, int pageEnd) {
        /*
            select top 结束位置 * from 表名 where 条件 and id not in (
                select top 开始位置 id from 表名 where 条件 order by 排序
            ) order by 排序
        */
        sql.append("select top ").append(pageEnd).append(" * from ").append(table);
        if (StringUtil.isNotEmpty(where)) {
            sql.append(where).append(" and ");
        } else {
            sql.append(" where ");
        }
        sql.append("id not in (select top ").append(pageStart).append(" id from ").append(table);
        sql.append(where);
        sql.append(order);
        sql.append(") ").append(order);
    }
    
    /**
     * 解析sql语句 
     * 例如  update table set id = :id 
     * 将 :id 替换成 id 
     * 并把 :id 对应的值放入 list 中
     * @param sql 语句
     * @param param 条件
     */
    public static SqlEntity parseSql(String sql, Map<String,Object> param) {
    	if (StringUtil.isEmpty(sql)) {
    		logger.error(" sql 语句不能为空 ! ");
    		throw new RuntimeException();
    	}
    	if (MapUtil.isEmpty(param)) {
    		return new SqlEntity(sql, null);
    	}
    	List<Object> list = new ArrayList<Object>();
    	StringBuffer sqlBuf = new StringBuffer(sql + " ");
    	while(sqlBuf.indexOf(":") > -1) {
    		int start = sqlBuf.indexOf(":");
    		int end = start + sqlBuf.substring(sqlBuf.indexOf(":")).indexOf(" ");
    		list.add(param.get(sqlBuf.substring(start, end).substring(1)));
    		sqlBuf = sqlBuf.replace(start, end, "?");
    	}
    	return new SqlEntity(sqlBuf.toString(), list);
    }
    
    /**
     * 生成insert语句 , 可以一次插入多条数据
     * @param table
     * @param list
     * @return
     */
	public static SqlEntity generateInsert(String table, List<LinkedHashMap<String,Object>> list) {
		if (StringUtil.isEmpty(table) || CollectionUtil.isEmpty(list)) {
			logger.error(" 表名table 或 参数list 不能为空 ! ");
			throw new RuntimeException();
		}
    	StringBuffer sql = new StringBuffer(40);
    	sql.append(" insert into ");
    	sql.append(table);
    	
    	// 生成 insert 的column 列名语句  例如: ( id , name , password , age )
    	
		Map<String,Object> map = list.get(0);
		Iterator<String> iterator = map.keySet().iterator();
		sql.append(" (");
		while (iterator.hasNext()) {
			String key = (String) iterator.next(); 
			sql.append(" " + key + " ,");
		}
		sql = new StringBuffer(sql.substring(0,sql.lastIndexOf(",")));
		sql.append(") values");
    	
    	// 生成 insert 的参数语句  例如: ( ? , ? , ? ) , ( ? , ? , ? )
		StringBuffer value = new StringBuffer(40);
    	int length = list.get(0).size();
    	for (int i = 0, j = list.size(); i < j; i++) {
    		value.append(" (");
    		for (int m = 0; m < length; m++) {
    			value.append(" ? ,");
    		}
    		value = new StringBuffer(value.substring(0,value.lastIndexOf(",")));
    		value.append(") , ");
    	}
    	value = new StringBuffer(value.substring(0,value.lastIndexOf(",")));
    	
    	// 生成 insert 的参数
    	List<Object> param = new ArrayList<Object>();
		for (Map<String,Object> temp : list) {
			param.addAll(temp.values());
    	}
    	return new SqlEntity(sql.toString() + value.toString(), param);
    }
	
	public static String generateLimit(int pageNumber, int pageSize) {
		String limit = "";
		limit += " limit " + pageNumber + "," + pageSize;
		return limit;
	}
	
	public static String generateCount(String sql) {
		if (StringUtil.isEmpty(sql)) {
			logger.error(" Sql语句不能为空 ! ");
    		throw new RuntimeException();
		}
		return " select count(*) from (" + sql + ") generateCount ";
	}
}

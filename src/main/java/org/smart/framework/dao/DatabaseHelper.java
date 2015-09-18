/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.InstanceFactory;
import org.smart.framework.core.ConfigHelper;
import org.smart.framework.dao.bean.Pager;
import org.smart.framework.dao.bean.SqlEntity;
import org.smart.framework.ds.DataSourceFactory;
import org.smart.framework.util.CastUtil;
import org.smart.framework.util.StringUtil;

/**
 * 封装数据库相关操作
 * 
 * @author zhaohang
 */
public class DatabaseHelper {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

	/**
     * 定义一个局部线程变量（使每个线程都拥有自己的连接）
     */
	private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();
	
	/**
     * 获取数据源工厂
     */
    private static final DataSourceFactory dataSourceFactory = InstanceFactory.getDataSourceFactory();

    /**
     * 获取数据访问器
     */
    private static final DataAccessor dataAccessor = InstanceFactory.getDataAccessor();
    
    /**
     * 数据库类型
     */
    private static final String databaseType = ConfigHelper.getString("smart.framework.jdbc.type");
    
    
    /**
     * 获取数据库类型
     */
    public static String getDatabaseType() {
        return databaseType;
    }

    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }
    
    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn;
        try {
            // 先从 ThreadLocal 中获取 Connection
            conn = connContainer.get();
            if (conn == null) {
                // 若不存在，则从 DataSource 中获取 Connection
                conn = getDataSource().getConnection();
                // 将 Connection 放入 ThreadLocal 中
                if (conn != null) {
                    connContainer.set(conn);
                }
            }
        } catch (SQLException e) {
            logger.error("获取数据库连接出错 ! ", e);
            throw new RuntimeException(e);
        }
        return conn;
    }
    
    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                logger.error("开启事务出错 ! ", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                logger.error("提交事务出错 ! ", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.remove();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                logger.error("回滚事务出错 ! ", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.remove();
            }
        }
    }
    
    
    
    
    /** =================================== 提供查询的接口  ====================================== **/
    
    
    
    
    /**
     * 查询单条记录 (带条件)
     * @param sql 语句
     * @param param 条件
     */
	public static Map<String,Object> single(String sql, Map<String,Object> param) {
    	SqlEntity sqlEntity = SqlHelper.parseSql(sql, param);
    	long start = System.currentTimeMillis();
    	printSQL(sqlEntity.getSql(), sqlEntity.getParam());
    	Map<String,Object> result = dataAccessor.single(sqlEntity.getSql(), sqlEntity.getParam());
    	printSQL(start);
    	return result;
    }
    
    /**
     * 查询多条记录 (不带条件)
     * @param sql 语句
     * @param param 条件
     */
    public static List<Map<String,Object>> list(String sql) {
    	return list(sql, null);
    }
    
    /**
     * 查询多条记录 (带条件)
     * @param sql 语句
     * @param param 条件
     */
    public static List<Map<String,Object>> list(String sql, Map<String,Object> param) {
    	SqlEntity sqlEntity = SqlHelper.parseSql(sql,param);
    	long start = System.currentTimeMillis();
    	printSQL(sqlEntity.getSql(), sqlEntity.getParam());
    	List<Map<String,Object>> result = dataAccessor.list(sqlEntity.getSql(), sqlEntity.getParam());
    	printSQL(start);
    	return result;
    }
    
    /**
     * 根据表名查询单条记录(自动生成sql)
     * @param table 表名
     */
    public static List<Map<String,Object>> list4Table(String table) {
    	
    	String sql = SqlHelper.generateQuery(table);
    	long start = System.currentTimeMillis();
    	printSQL(sql, null);
    	List<Map<String,Object>> result = dataAccessor.list(sql, null);
    	printSQL(start);
    	return result;
    }
    
    /**
     * 增加单记录
     * @param table 表名
     * @param param 单条数据
     */
    public static int add(String table, LinkedHashMap<String,Object> param) {
    	List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
    	list.add(param);
    	return add(table,list);
    }
    
    /**
     * 增加多条记录
     * @param table 表名
     * @param list 多条数据
     */
	public static int add(String table, List<LinkedHashMap<String,Object>> list) {
    	SqlEntity sqlEntity = SqlHelper.generateInsert(table,list);
    	long start = System.currentTimeMillis();
    	printSQL(sqlEntity.getSql(), sqlEntity.getParam());
    	int result = dataAccessor.add(sqlEntity.getSql(), sqlEntity.getParam());
    	printSQL(start);
    	return result;
    }
    
    /**
     * 修改记录
     * @param sql 语句
     * @param param 条件
     * @return
     */
    public static int update(String sql, Map<String,Object> param) {
    	if (StringUtil.isEmpty(sql)) {
    		logger.error(" SQL语句不能为空 ! ");
    		throw new RuntimeException();
    	}
    	SqlEntity sqlEntity = SqlHelper.parseSql(sql,param);
    	long start = System.currentTimeMillis();
    	printSQL(sqlEntity.getSql(), sqlEntity.getParam());
    	int result = dataAccessor.update(sqlEntity.getSql(), sqlEntity.getParam());
    	printSQL(start);
    	return result;
    }
    
    /**
     * 查询分页数据
     * @param sql
     * @param param
     * @return
     */
    public static Pager<Map<String,Object>> pager(String sql, Map<String,Object> param) {
    	int pageNumber = CastUtil.castInt(param.get("pageNumber"));
    	int pageSize = CastUtil.castInt(param.get("pageSize"));
    	return pager(sql, param, pageNumber, pageSize);
    }
    
    /**
     * 查询分页数据
     * @param sql
     * @param param
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public static Pager<Map<String,Object>> pager(String sql, Map<String,Object> param, int pageNumber, int pageSize) {
    	String sort = CastUtil.castString(param.get("sort"));
    	return pager(sql, param, pageNumber, pageSize, sort);
    }
    
    /**
     * 查询分页数据
     * @param sql
     * @param param
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public static Pager<Map<String,Object>> pager(String sql, Map<String,Object> param, int pageNumber, int pageSize ,String sort) {
    	if (pageNumber < 0 || pageSize < 1) {
    		pageNumber = Pager.DEFAULT_PAGENUMBER;
    		pageSize = Pager.DEFAULT_PAGESIZE;
    	}
    	if (StringUtil.isNotEmpty(sort)) {
    		sql += SqlHelper.generateOrder(sort);
    	}
    	String countSql = SqlHelper.generateCount(sql);
    	sql += SqlHelper.generateLimit(pageNumber * pageSize, pageSize);
    	SqlEntity sqlEntity = SqlHelper.parseSql(sql, param);
    	long start = System.currentTimeMillis();
    	printSQL(sqlEntity.getSql(), countSql, sqlEntity.getParam());
    	Pager<Map<String,Object>> pager = dataAccessor.pager(sqlEntity.getSql(), countSql, sqlEntity.getParam(), pageNumber, pageSize);
    	printSQL(start);
    	return pager;
    }
    
    
    private static void printSQL(long start) {
    	logger.debug("                       [Smart] COST  : {} ", System.currentTimeMillis() - start + " ms");
    }
    
    private static void printSQL(String sql, List<Object> param) {
        logger.debug("                       [Smart] SQL   : {} ", sql.trim());
        logger.debug("                       [Smart] PARAM : {} ", param.toString());
    }
    
    private static void printSQL(String sql, String countSql, List<Object> param) {
        logger.debug("                       [Smart] SQL   : {} ", sql.trim());
        logger.debug("                       [Smart] SQL   : {} ", countSql.trim());
        logger.debug("                       [Smart] PARAM : {} ", param.toString());
    }
}

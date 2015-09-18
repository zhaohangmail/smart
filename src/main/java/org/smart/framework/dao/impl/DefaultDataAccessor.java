/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.dao.BasicRowProcessorEx;
import org.smart.framework.dao.DataAccessor;
import org.smart.framework.dao.DatabaseHelper;
import org.smart.framework.dao.bean.Pager;
import org.smart.framework.util.CollectionUtil;

/**
 * 默认数据访问器
 * <br/>
 * 基于 Apache Commons DbUtils 实现
 * 
 * @author zhaohang
 */
public class DefaultDataAccessor implements DataAccessor{

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataAccessor.class);

	private final QueryRunner queryRunner;
	
	public DefaultDataAccessor() {
		DataSource dataSource = DatabaseHelper.getDataSource();
		queryRunner = new QueryRunner(dataSource);
	}

	@Override
	public Map<String,Object> single(String sql, List<Object> list) {
		try {
			if (CollectionUtil.isEmpty(list)) {
				return queryRunner.query(sql, new MapHandler(new BasicRowProcessorEx()));
			} else {
				return queryRunner.query(sql, new MapHandler(new BasicRowProcessorEx()), list.toArray());
			}
		} catch (SQLException e) {
			logger.error(" 查询单条数据出错 ! ");
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public List<Map<String,Object>> list(String sql, List<Object> list) {
		try {
			if (CollectionUtil.isEmpty(list)) {
				return queryRunner.query(sql, new MapListHandler(new BasicRowProcessorEx()));
			} else {
				return queryRunner.query(sql, new MapListHandler(new BasicRowProcessorEx()), list.toArray());
			}
		} catch (SQLException e) {
			logger.error(" 查询多条数据出错 ! ");
			throw new RuntimeException();
		}
	}
	
	@Override
	public int add(String sql, List<Object> param) {
		try {
			return queryRunner.update(sql, param.toArray());
		} catch (SQLException e) {
			logger.error(" 插入数据出错 ! ");
			throw new RuntimeException();
		}
	}
	
	@Override
	public int update(String sql, List<Object> param) {
		try {
			return queryRunner.update(sql, param.toArray());
		} catch (SQLException e) {
			logger.error(" 修改数据出错 ! ");
			throw new RuntimeException();
		}
	}
	
	@Override
	public Pager<Map<String,Object>> pager(String sql, String countSql, List<Object> list, int pageNumber, int pageSize) {
		try {
			
			if (CollectionUtil.isEmpty(list)) {
				List<Map<String,Object>> result = queryRunner.query(sql, new MapListHandler(new BasicRowProcessorEx()));
				Map<String,Object> countResult = queryRunner.query(countSql, new MapHandler(new BasicRowProcessorEx()));
				return new Pager<Map<String, Object>>(pageNumber, pageSize, (long)countResult.get("count(*)"), result);
			} else {
				List<Map<String,Object>> result = queryRunner.query(sql, new MapListHandler(new BasicRowProcessorEx()), list);
				Map<String,Object> countResult = queryRunner.query(countSql, new MapHandler(new BasicRowProcessorEx()));
				return new Pager<Map<String,Object>>(pageNumber, pageSize, (long)countResult.get("count(*)"), result);
			}
		} catch (SQLException e) {
			logger.error(" 查询分页数据出错 ! ");
			throw new RuntimeException();
		}
	}


}

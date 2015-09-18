/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.ds;
import javax.sql.DataSource;

/**
 * 数据源工厂
 * 
 * @author zhaohang
 */
public interface DataSourceFactory {

	/**
     * 获取数据源
     *
     * @return 数据源
     */
    DataSource getDataSource();
}

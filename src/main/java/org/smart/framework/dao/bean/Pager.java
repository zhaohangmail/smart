/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.dao.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.core.bean.BaseBean;

/**
 * 分页对象
 *
 * @author huangyong
 * @since 1.0
 */
public class Pager<T> extends BaseBean {

    private static final long serialVersionUID = -3331210109764851619L;
    
    private static final Logger logger = LoggerFactory.getLogger(Pager.class);
    
	private int pageNumber;     // 页面编号
    private int pageSize;       // 每页条数
    private long totalRecord;   // 总记录数
    private long totalPage;     // 总页面数
    private List<T> recordList; // 数据列表
    public static final int DEFAULT_PAGENUMBER = 0;
    public static final int DEFAULT_PAGESIZE = 10;

    public Pager() {
    }

    public Pager(int pageNumber, int pageSize, long totalRecord, List<T> recordList) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.recordList = recordList;
        if (pageNumber * pageSize >= totalRecord) {
        	logger.error(" 当前页面编号已大于最大页面编号 ! ");
        	throw new RuntimeException();
        }
        if (pageSize != 0) {
            totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    // ----------------------------------------------------------------------------------------------------

    public boolean isFirstPage() {
        return pageNumber == 1;
    }

    public boolean isLastPage() {
        return pageNumber == totalPage;
    }

    public boolean isPrevPage() {
        return pageNumber > 1 && pageNumber <= totalPage;
    }

    public boolean isNextPage() {
        return pageNumber < totalPage;
    }
}

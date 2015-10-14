package xie.web.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 页码信息
 * 
 * <pre>
 * Pattern : 
 * Thread Safe : No
 * 
 * Change History
 * 
 * Name              Date                 Description
 * --------          ------------         ----------------
 * Johnson Yang 	2 , 5 , 2010         Create the class
 * 
 * 
 * </pre>
 * 
 * @author kenst.xu
 * @version 1.0
 * 
 * @see
 */
public class PageInfoBean implements Serializable {
	// 序列号
	private static final long serialVersionUID = -6114756075714827410L;
	// 每一页显示的最大记录数
	public final static int COMMON_RECORD_COUNT_IN_ONE_PAGE = 10;
	// 当前页码
	private int curPageNo;
	// 总页数
	private int totalPageNo;
	// 一页显示的记录数
	private int recordCountInOnePage;
	// 总记录数
	private int totalRecordCount = 0;

	public PageInfoBean() {
		super();
	}

	public PageInfoBean(final int curPageNo) {
		super();
		this.curPageNo = curPageNo;
		this.recordCountInOnePage = COMMON_RECORD_COUNT_IN_ONE_PAGE;
	}

	public PageInfoBean(final int curPageNo, final int recordCountInOnePage) {
		super();
		this.curPageNo = curPageNo;
		this.recordCountInOnePage = recordCountInOnePage;
	}

	public int getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(final int curPageNo) {
		this.curPageNo = curPageNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(final int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}

	public void setRecordCountInOnePage(final int recordCountInOnePage) {
		this.recordCountInOnePage = recordCountInOnePage;
	}

	public int getRecordCountInOnePage() {
		return recordCountInOnePage;
	}

	public void setTotalRecordCount(final int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * 
	 * 修正数据
	 */
	public void doReviseData() {
		if (curPageNo <= 0) {
			curPageNo = 1;
		}
		if (recordCountInOnePage <= 0) {
			recordCountInOnePage = COMMON_RECORD_COUNT_IN_ONE_PAGE;
		}

		if (totalRecordCount < 0) {
			totalRecordCount = 0;
		}

		totalPageNo = (int) Math.ceil((double) totalRecordCount / (double) recordCountInOnePage);
		if (totalPageNo == 0) {
			totalPageNo = 1;
		}

		if (curPageNo > totalPageNo) {
			curPageNo = totalPageNo;
		}
	}

	/**
	 * 从allRecordList提取记录到当前页面。 请注意页面分割规则,在这个PageInfoBean已经设置当前页面。
	 * 
	 * @param <T> 记录类型
	 * @param allRecordList 所有的记录
	 * @return 当前页的记录集合
	 * @author 020004
	 */
	public <T> List<T> extractRecords(final List<T> allRecordList) {
		final List<T> result = new ArrayList<T>();
		this.setTotalRecordCount(allRecordList.size());
		this.doReviseData();
		final int beginNo = (getCurPageNo() - 1) * getRecordCountInOnePage();
		for (int i = 0; i < getRecordCountInOnePage(); i++) {
			if (beginNo + i >= getTotalRecordCount()) {
				break;
			}
			result.add(allRecordList.get(beginNo + i));
		}
		return result;
	}

	/**
	 * 从allRecordList提取记录到当前页面，同时获取当前页的最大记录数。
	 * 
	 * @param <T> 记录类型
	 * @param allRecordList 所有的记录集合
	 * @param maxRecordCount 一页最大的记录数
	 * @return 当前页的记录集合
	 */
	public <T> List<T> extractRecords(final List<T> allRecordList, final int maxRecordCount) {
		final List<T> result = new ArrayList<T>();
		this.setTotalRecordCount(allRecordList.size() > maxRecordCount ? maxRecordCount : allRecordList.size());
		this.doReviseData();
		final int beginNo = (getCurPageNo() - 1) * getRecordCountInOnePage();
		for (int i = 0; i < getRecordCountInOnePage(); i++) {
			if (beginNo + i >= getTotalRecordCount()) {
				break;
			}
			result.add(allRecordList.get(beginNo + i));
		}
		return result;
	}
}

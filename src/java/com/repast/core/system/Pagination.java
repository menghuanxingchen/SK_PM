package com.repast.core.system;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T extends Object> {
	
	private List<T> records;

	private Long totalCount = new Long(0);

	private Integer pageSize = 10;

	private Integer pageIndex = 0;

	private int[] pageBar;
	
	private Integer pageMaxSize=0;

	public Pagination() {

	}

	public Integer getPageMaxSize() {
		return pageMaxSize;
	}

	public void setPageMaxSize(Integer pageMaxSize) {
		this.pageMaxSize = pageMaxSize;
	}

	public Pagination(List<T> records, Long totalCount, Integer pageSize,Integer pageIndex) {
		this.records = records;
		this.totalCount = totalCount;
		this.pageSize = Pagination.getPageSize(pageSize);
		this.pageIndex = pageIndex;
		getPageBar();
	}

	public void createPagination(List<T> records, Long totalCount,Integer pageSize, Integer pageIndex) {
		this.records = records;
		this.totalCount = totalCount;
		this.pageSize = Pagination.getPageSize(pageSize);
		this.pageIndex = pageIndex;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public static Integer getStartNumber(Integer pageIndex, Integer pageSize) {
		return (pageIndex - 1) * pageSize;
	}

	public static Integer getPageSize(Integer pageSize) {
		if (pageSize.compareTo(0) > 0) {
			return pageSize;
		} else {
			return 100;
		}
	}

	public Integer getStartNumber() {
		return (pageIndex - 1) * pageSize;
	}

	public Integer getPageSize() {
		if (pageSize.compareTo(0) > 0) {
			return pageSize;
		} else {
			return -1;
		}
	}

	public int[] getPageBar() {
		int startPage = 0;
		int endPage = 0;
		int totalSize = 0;
		if(getTotalCount()%getPageSize()==0){
			totalSize = (int) (getTotalCount()/getPageSize());
		}else{
			totalSize = (int) (getTotalCount()/getPageSize())+1;
		}
		if (totalSize <= 10) {
			startPage = 1;
			endPage = totalSize;
		} else {
			startPage = getPageIndex().intValue() - 4;
			endPage = getPageIndex().intValue() + 5;
			if (startPage < 1) {
				startPage = 1;
				endPage = 10;
			}
			if (endPage > totalSize) {
				endPage = totalSize;
				startPage = totalSize - 9;
			}
		}
		setPageMaxSize(totalSize);
		pageBar = new int[endPage - startPage + 1];
		int index = 0;
		for (int i = startPage; i <= endPage; i++) {
			pageBar[index++] = i;
		}
		return pageBar;
	}

	public void addAll(List<T> rs) {
		if (this.records == null) {
			this.records = new ArrayList<T>();
		}
		this.records.addAll(rs);
		this.pageSize = this.pageSize + rs.size();
	}
}

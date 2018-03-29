package com.project.page;


import org.apache.ibatis.session.RowBounds;

public class PageBean extends RowBounds {
	// 总记录数
	private Integer total=0;
	// 查询的起始位置
	private Integer offset=0;
	// 每次查询多少行记录
	private Integer limit=10;
	//当前页
	private Integer page=1;
	
	public int getPage() {
		return page==0?1:page;
	}

	public PageBean setPage(int page) {
		this.page=page;
		return this;
	}

	public PageBean setLimit(int limit) {
		this.limit=limit<=0?this.limit:limit;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return this.limit;
	}

	public void setMeToDefault() {
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}

	public int getSelectCount() {
		return limit + offset;
	}

}

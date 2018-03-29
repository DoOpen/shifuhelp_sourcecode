package com.project.bean.others;

public class AjaxResult {
	private String status;
	private String error;
	private Object data;
	private Integer total=0;
	public String getStatus() {
		return status;
	}
	public AjaxResult setStatus(String status) {
		this.status = status;
		return this;
	}
	public String getError() {
		return error;
	}
	public AjaxResult setError(String error) {
		this.error = error;
		return this;
	}
	public Object getData() {
		return data;
	}
	public AjaxResult setData(Object data) {
		this.data = data;
		return this;
	}
	public Integer getTotal() {
		return total;
	}
	public AjaxResult setTotal(Integer total) {
		this.total = total;
		return this;
	}
}

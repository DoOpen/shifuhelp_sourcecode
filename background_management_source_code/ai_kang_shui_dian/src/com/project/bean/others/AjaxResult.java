package com.project.bean.others;

/**
 * ajax返回结果
 * @author 彭方林
 * @date 2018年4月2日
 */
public class AjaxResult {
	private String status;//状态码  ok:成功   error:失败   pending:等待
	private String error;//错误信息
	private Object data;//结果集
	private Integer total=0;//分页总条数
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

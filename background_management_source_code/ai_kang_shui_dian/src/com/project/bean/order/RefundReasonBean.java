package com.project.bean.order;

/**
 * 退款原因
 * @author 彭方林
 * @date 2018年4月2日
 */
public class RefundReasonBean {
	private Integer reason_id;
	private String reason_name;//退款原因
	private String create_time;//创建时间
	private String is_delete;//1删除
	private Integer sort;//权重
	public Integer getReason_id() {
		return reason_id;
	}
	public RefundReasonBean setReason_id(Integer reason_id) {
		this.reason_id = reason_id;
		return this;
	}
	public String getReason_name() {
		return reason_name;
	}
	public RefundReasonBean setReason_name(String reason_name) {
		this.reason_name = reason_name;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public RefundReasonBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public RefundReasonBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public Integer getSort() {
		return sort;
	}
	public RefundReasonBean setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
}

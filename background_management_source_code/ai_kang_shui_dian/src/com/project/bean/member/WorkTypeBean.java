package com.project.bean.member;

public class WorkTypeBean {
	private Integer type_id;
	private String type_name;
	private String is_delete;
	private String create_time;
	private String sort;
	public Integer getType_id() {
		return type_id;
	}
	public WorkTypeBean setType_id(Integer type_id) {
		this.type_id = type_id;
		return this;
	}
	public String getType_name() {
		return type_name;
	}
	public WorkTypeBean setType_name(String type_name) {
		this.type_name = type_name;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public WorkTypeBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public WorkTypeBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public WorkTypeBean setSort(String sort) {
		this.sort = sort;
		return this;
	}
}

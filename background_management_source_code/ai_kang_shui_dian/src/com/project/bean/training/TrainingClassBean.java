package com.project.bean.training;

/**
 * 培训分类
 * @author 彭方林
 * @date 2018年4月2日
 */
public class TrainingClassBean {
	private Integer class_id;
	private String class_name;//分类名称
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String sort;//权重
	public String getSort() {
		return sort;
	}
	public TrainingClassBean setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public TrainingClassBean setClass_id(Integer class_id) {
		this.class_id = class_id;
		return this;
	}
	public String getClass_name() {
		return class_name;
	}
	public TrainingClassBean setClass_name(String class_name) {
		this.class_name = class_name;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public TrainingClassBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public TrainingClassBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
}

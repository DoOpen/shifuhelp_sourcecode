package com.project.bean.workOrder;

import java.util.List;

/**
 * 工单服务分类
 * @author 彭方林
 * @date 2018年4月2日
 */
public class ServiceClassBean {
	private Integer class_id;
	private String class_name;//分类名称
	private String class_state;//分类状态
	private String class_img;//分类图片
	private Integer class_parent_id;//父节点id
	private String class_create_time;//创建时间
	private String class_is_delete;//1删除
	private String class_sort;//权重
	private String class_update_time;//修改时间
	private String class_price;//分类单价
	private String class_desc;//介绍
	private String class_unit;//单位
	public String getClass_unit() {
		return class_unit;
	}
	public ServiceClassBean setClass_unit(String class_unit) {
		this.class_unit = class_unit;
		return this;
	}
	public List<ServiceClassBean> serviceClassBeans;
	
	public String getClass_is_delete() {
		return class_is_delete;
	}
	public ServiceClassBean setClass_is_delete(String class_is_delete) {
		this.class_is_delete = class_is_delete;
		return this;
	}
	public String getClass_sort() {
		return class_sort;
	}
	public ServiceClassBean setClass_sort(String class_sort) {
		this.class_sort = class_sort;
		return this;
	}
	public List<ServiceClassBean> getServiceClassBeans() {
		return serviceClassBeans;
	}
	public ServiceClassBean setServiceClassBeans(List<ServiceClassBean> serviceClassBeans) {
		this.serviceClassBeans = serviceClassBeans;
		return this;
	}
	public String getClass_desc() {
		return class_desc;
	}
	public ServiceClassBean setClass_desc(String class_desc) {
		this.class_desc = class_desc;
		return this;
	}
	public Integer getClass_parent_id() {
		return class_parent_id;
	}
	public ServiceClassBean setClass_parent_id(Integer class_parent_id) {
		this.class_parent_id = class_parent_id;
		return this;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public ServiceClassBean setClass_id(Integer class_id) {
		this.class_id = class_id;
		return this;
	}
	public String getClass_name() {
		return class_name;
	}
	public ServiceClassBean setClass_name(String class_name) {
		this.class_name = class_name;
		return this;
	}
	public String getClass_state() {
		return class_state;
	}
	public ServiceClassBean setClass_state(String class_state) {
		this.class_state = class_state;
		return this;
	}
	public String getClass_img() {
		return class_img;
	}
	public ServiceClassBean setClass_img(String class_img) {
		this.class_img = class_img;
		return this;
	}
	public String getClass_create_time() {
		return class_create_time;
	}
	public ServiceClassBean setClass_create_time(String class_create_time) {
		this.class_create_time = class_create_time;
		return this;
	}
	public String getClass_update_time() {
		return class_update_time;
	}
	public ServiceClassBean setClass_update_time(String class_update_time) {
		this.class_update_time = class_update_time;
		return this;
	}
	public String getClass_price() {
		return class_price;
	}
	public ServiceClassBean setClass_price(String class_price) {
		this.class_price = class_price;
		return this;
	}
}

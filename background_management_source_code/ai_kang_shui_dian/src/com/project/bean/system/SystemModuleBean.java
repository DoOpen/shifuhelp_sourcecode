package com.project.bean.system;

import java.util.List;

/**
 * 系统模块
 * @author 彭方林
 * @date 2018年4月2日
 */
public class SystemModuleBean{
	private Integer module_id;
	private String module_name;//模块名称
	private String module_url;//模块url
	private Integer parent_id;//父节点id
	private String sort;//权重
	private String is_delete;//1删除
	private String create_time;//创建时间
	private List<SystemModuleBean> systemModuleBeans;
	public Integer getModule_id() {
		return module_id;
	}
	public SystemModuleBean setModule_id(Integer module_id) {
		this.module_id = module_id;
		return this;
	}
	public String getModule_name() {
		return module_name;
	}
	public SystemModuleBean setModule_name(String module_name) {
		this.module_name = module_name;
		return this;
	}
	public String getModule_url() {
		return module_url;
	}
	public SystemModuleBean setModule_url(String module_url) {
		this.module_url = module_url;
		return this;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public SystemModuleBean setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public SystemModuleBean setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public SystemModuleBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public SystemModuleBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public List<SystemModuleBean> getSystemModuleBeans() {
		return systemModuleBeans;
	}
	public SystemModuleBean setSystemModuleBeans(List<SystemModuleBean> systemModuleBeans) {
		this.systemModuleBeans = systemModuleBeans;
		return this;
	}
}

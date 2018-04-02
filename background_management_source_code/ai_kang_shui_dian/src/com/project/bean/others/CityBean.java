package com.project.bean.others;

import java.util.List;

/**
 * 省市区地址
 * @author 彭方林
 * @date 2018年4月2日
 */
public class CityBean {
	private Integer id;
	private String name;//名称
	private Integer parent_id;//父节点id
	private String code;//地区编码
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String update_time;//修改时间
	private List<CityBean> cityBeans;
	public Integer getId() {
		return id;
	}
	public CityBean setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public CityBean setName(String name) {
		this.name = name;
		return this;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public CityBean setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
		return this;
	}
	public String getCode() {
		return code;
	}
	public CityBean setCode(String code) {
		this.code = code;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public CityBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public CityBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public CityBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public List<CityBean> getCityBeans() {
		return cityBeans;
	}
	public CityBean setCityBeans(List<CityBean> cityBeans) {
		this.cityBeans = cityBeans;
		return this;
	}
}

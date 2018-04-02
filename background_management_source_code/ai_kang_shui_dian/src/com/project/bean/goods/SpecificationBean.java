package com.project.bean.goods;

import java.util.List;

/**
 * 商品规格总表
 * @author 彭方林
 * @date 2018年4月2日
 */
public class SpecificationBean {
	private Integer specification_id;
	private String specification_name;//规格名称
	private String is_delete;//1删除
	private String sort;//权重
	private String create_time;//创建时间
	private String update_time;//修改时间
	private Integer parent_id;//父规格id
	private List<SpecificationBean> specificationBeans;
	private SpecificationBean specificationBean;
	
	public SpecificationBean getSpecificationBean() {
		return specificationBean;
	}
	public SpecificationBean setSpecificationBean(SpecificationBean specificationBean) {
		this.specificationBean = specificationBean;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public SpecificationBean setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public List<SpecificationBean> getSpecificationBeans() {
		return specificationBeans;
	}
	public SpecificationBean setSpecificationBeans(List<SpecificationBean> specificationBeans) {
		this.specificationBeans = specificationBeans;
		return this;
	}
	public Integer getSpecification_id() {
		return specification_id;
	}
	public SpecificationBean setSpecification_id(Integer specification_id) {
		this.specification_id = specification_id;
		return this;
	}
	public String getSpecification_name() {
		return specification_name;
	}
	public SpecificationBean setSpecification_name(String specification_name) {
		this.specification_name = specification_name;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public SpecificationBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public SpecificationBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public SpecificationBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public SpecificationBean setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
		return this;
	}
}

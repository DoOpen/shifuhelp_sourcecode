package com.project.bean.information;

public class InformationImgBean {
	private Integer information_img_id;
	private Integer information_id;
	private Integer goods_id;
	private String goods_name;
	private Integer is_delete;
	private String create_time;
	private String information_img;

	
	public String getGoods_name() {
		return goods_name;
	}
	public InformationImgBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
	public Integer getInformation_img_id() {
		return information_img_id;
	}
	public InformationImgBean setInformation_img_id(Integer information_img_id) {
		this.information_img_id = information_img_id;
		return this;
	}
	public Integer getInformation_id() {
		return information_id;
	}
	public InformationImgBean setInformation_id(Integer information_id) {
		this.information_id = information_id;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public InformationImgBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public InformationImgBean setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public InformationImgBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getInformation_img() {
		return information_img;
	}
	public InformationImgBean setInformation_img(String information_img) {
		this.information_img = information_img;
		return this;
	}
}

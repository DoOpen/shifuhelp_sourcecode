package com.project.bean.goods;

public class GoodsImgBean {
	private Integer img_id;
	private Integer goods_id;//商品id
	private String img_url;//图片路径
	private String sort;//权重
	private String create_time;//
	private String update_time;//
	private String is_delete;//是否删除
	public Integer getImg_id() {
		return img_id;
	}
	public GoodsImgBean setImg_id(Integer img_id) {
		this.img_id = img_id;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public GoodsImgBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public String getImg_url() {
		return img_url;
	}
	public GoodsImgBean setImg_url(String img_url) {
		this.img_url = img_url;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public GoodsImgBean setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public GoodsImgBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public GoodsImgBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public GoodsImgBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	
}

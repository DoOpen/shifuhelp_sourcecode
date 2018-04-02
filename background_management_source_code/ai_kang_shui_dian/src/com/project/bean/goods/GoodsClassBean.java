package com.project.bean.goods;

import java.util.List;
/**
 * 商品分类
 * @author 彭方林
 * @date 2018年4月2日
 */
public class GoodsClassBean {
	private Integer class_id;
	private String class_name;//分类名称
	private String class_desc;//分类描述
	private String class_img;//分类图标
	private String class_url;//类别url
	private Integer parent_id;//父节点id
	private String class_uuid;//分类的uuid
	private String parent_uuid;//所有父节点的uuid组合
	private String sort;//权重
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String is_delete;//是否删除
	private String is_recommend;//是否首页推荐
	private String send_range;//派送区域
	public String getSend_range() {
		return send_range;
	}
	public GoodsClassBean setSend_range(String send_range) {
		this.send_range = send_range;
		return this;
	}
	private List<GoodsClassBean> goodsClassBeans;
	private List<GoodsBean> goodsBeans;
	public Integer getClass_id() {
		return class_id;
	}
	public GoodsClassBean setClass_id(Integer class_id) {
		this.class_id = class_id;
		return this;
	}
	public String getClass_name() {
		return class_name;
	}
	public GoodsClassBean setClass_name(String class_name) {
		this.class_name = class_name;
		return this;
	}
	public String getClass_desc() {
		return class_desc;
	}
	public GoodsClassBean setClass_desc(String class_desc) {
		this.class_desc = class_desc;
		return this;
	}
	public String getClass_img() {
		return class_img;
	}
	public GoodsClassBean setClass_img(String class_img) {
		this.class_img = class_img;
		return this;
	}
	public String getClass_url() {
		return class_url;
	}
	public GoodsClassBean setClass_url(String class_url) {
		this.class_url = class_url;
		return this;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public GoodsClassBean setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
		return this;
	}
	public String getClass_uuid() {
		return class_uuid;
	}
	public GoodsClassBean setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public GoodsClassBean setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public GoodsClassBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public GoodsClassBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public GoodsClassBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getIs_recommend() {
		return is_recommend;
	}
	public GoodsClassBean setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
		return this;
	}
	public List<GoodsClassBean> getGoodsClassBeans() {
		return goodsClassBeans;
	}
	public GoodsClassBean setGoodsClassBeans(List<GoodsClassBean> goodsClassBeans) {
		this.goodsClassBeans = goodsClassBeans;
		return this;
	}
	public List<GoodsBean> getGoodsBeans() {
		return goodsBeans;
	}
	public GoodsClassBean setGoodsBeans(List<GoodsBean> goodsBeans) {
		this.goodsBeans = goodsBeans;
		return this;
	}
	public String getParent_uuid() {
		return parent_uuid;
	}
	public GoodsClassBean setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
		return this;
	}
	
}

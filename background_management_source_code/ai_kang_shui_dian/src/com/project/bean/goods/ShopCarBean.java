package com.project.bean.goods;

public class ShopCarBean {
	private Integer car_id;//购物车id
	private Integer member_id;//用户id
	private Integer goods_id;//商品id
	private String goods_name;//商品名称
	private String goods_img;//商品图片
	private Integer specification_id;//规格id
	private String specification_ids;//规格数组
	private String specification_names;//规格名称数组
	private String specification_img;//规格图片
	private Integer goods_num;//数量
	private String create_time;//创建时间
	private String update_time;//更新时间
	private String is_delete;//1删除
	private Float specification_price;//规格价格
	private Integer specification_stock;//规格库存
	private String car_ids;
	private String buy_type;
	public String getBuy_type() {
		return buy_type;
	}
	public ShopCarBean setBuy_type(String buy_type) {
		this.buy_type = buy_type;
		return this;
	}
	public String getCar_ids() {
		return car_ids;
	}
	public ShopCarBean setCar_ids(String car_ids) {
		this.car_ids = car_ids;
		return this;
	}
	public Integer getSpecification_stock() {
		return specification_stock;
	}
	public ShopCarBean setSpecification_stock(Integer specification_stock) {
		this.specification_stock = specification_stock;
		return this;
	}
	public Integer getCar_id() {
		return car_id;
	}
	public ShopCarBean setCar_id(Integer car_id) {
		this.car_id = car_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public ShopCarBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getSpecification_img() {
		return specification_img;
	}
	public ShopCarBean setSpecification_img(String specification_img) {
		this.specification_img = specification_img;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public ShopCarBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public ShopCarBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public ShopCarBean setGoods_img(String goods_img) {
		this.goods_img = goods_img;
		return this;
	}
	public Integer getSpecification_id() {
		return specification_id;
	}
	public ShopCarBean setSpecification_id(Integer specification_id) {
		this.specification_id = specification_id;
		return this;
	}
	public String getSpecification_ids() {
		return specification_ids;
	}
	public ShopCarBean setSpecification_ids(String specification_ids) {
		this.specification_ids = specification_ids;
		return this;
	}
	public String getSpecification_names() {
		return specification_names;
	}
	public ShopCarBean setSpecification_names(String specification_names) {
		this.specification_names = specification_names;
		return this;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public ShopCarBean setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public ShopCarBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public ShopCarBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public ShopCarBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public Float getSpecification_price() {
		return specification_price;
	}
	public ShopCarBean setSpecification_price(Float specification_price) {
		this.specification_price = specification_price;
		return this;
	}
}

package com.project.bean.order;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsSpecificationBean;

public class OrderGoodsBean {
	private Integer order_goods_id;
	private Integer order_id;
	private Integer goods_id;
	private Integer goods_num;
	private String goods_name;
	private String goods_img;
	private Integer specification_id;
	private String specification_ids;
	private String specification_names;
	private String specification_img;
	private Float specification_price;
	private Float group_price;
	private Integer goods_group_id;
	private Integer give_integral_value;
	private String total_price;
	private String is_delete;
	private String create_time;
	private String update_time;
	private String is_refund;
	private String show_type;
	private String buy_type;
	private GoodsBean goodsBean;
	private GoodsSpecificationBean goodsSpecificationBean;
	
	public String getShow_type() {
		return show_type;
	}
	public OrderGoodsBean setShow_type(String show_type) {
		this.show_type = show_type;
		return this;
	}
	public String getBuy_type() {
		return buy_type;
	}
	public OrderGoodsBean setBuy_type(String buy_type) {
		this.buy_type = buy_type;
		return this;
	}
	public String getIs_refund() {
		return is_refund;
	}
	public OrderGoodsBean setIs_refund(String is_refund) {
		this.is_refund = is_refund;
		return this;
	}
	public GoodsBean getGoodsBean() {
		return goodsBean;
	}
	public OrderGoodsBean setGoodsBean(GoodsBean goodsBean) {
		this.goodsBean = goodsBean;
		return this;
	}
	public GoodsSpecificationBean getGoodsSpecificationBean() {
		return goodsSpecificationBean;
	}
	public OrderGoodsBean setGoodsSpecificationBean(GoodsSpecificationBean goodsSpecificationBean) {
		this.goodsSpecificationBean = goodsSpecificationBean;
		return this;
	}
	public Integer getOrder_goods_id() {
		return order_goods_id;
	}
	public OrderGoodsBean setOrder_goods_id(Integer order_goods_id) {
		this.order_goods_id = order_goods_id;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public OrderGoodsBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public OrderGoodsBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public OrderGoodsBean setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
		return this;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public OrderGoodsBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public OrderGoodsBean setGoods_img(String goods_img) {
		this.goods_img = goods_img;
		return this;
	}
	public Integer getSpecification_id() {
		return specification_id;
	}
	public OrderGoodsBean setSpecification_id(Integer specification_id) {
		this.specification_id = specification_id;
		return this;
	}
	public String getSpecification_ids() {
		return specification_ids;
	}
	public OrderGoodsBean setSpecification_ids(String specification_ids) {
		this.specification_ids = specification_ids;
		return this;
	}
	public String getSpecification_names() {
		return specification_names;
	}
	public OrderGoodsBean setSpecification_names(String specification_names) {
		this.specification_names = specification_names;
		return this;
	}
	public String getSpecification_img() {
		return specification_img;
	}
	public OrderGoodsBean setSpecification_img(String specification_img) {
		this.specification_img = specification_img;
		return this;
	}
	public Float getSpecification_price() {
		return specification_price;
	}
	public OrderGoodsBean setSpecification_price(Float specification_price) {
		this.specification_price = specification_price;
		return this;
	}
	public String getTotal_price() {
		return total_price;
	}
	public OrderGoodsBean setTotal_price(String total_price) {
		this.total_price = total_price;
		return this;
	}
	
	public Float getGroup_price() {
		return group_price;
	}
	public OrderGoodsBean setGroup_price(Float group_price) {
		this.group_price = group_price;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public OrderGoodsBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public OrderGoodsBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public OrderGoodsBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public Integer getGive_integral_value() {
		return give_integral_value;
	}
	public OrderGoodsBean setGive_integral_value(Integer give_integral_value) {
		this.give_integral_value = give_integral_value;
		return this;
	}
	public Integer getGoods_group_id() {
		return goods_group_id;
	}
	public OrderGoodsBean setGoods_group_id(Integer goods_group_id) {
		this.goods_group_id = goods_group_id;
		return this;
	}
}

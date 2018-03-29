package com.project.bean.order;

import java.util.List;

public class CreateOrderInfoBean {
	private Integer member_id;
	private Integer address_id;
	private String car_ids;
	private Integer is_deduct_integral;
	private Integer member_coupon_id;
	private String order_type;
	private String buy_type;
	private List<OrderBean> orderBeans;
	public String getBuy_type() {
		return buy_type;
	}
	public CreateOrderInfoBean setBuy_type(String buy_type) {
		this.buy_type = buy_type;
		return this;
	}
	public String getOrder_type() {
		return order_type;
	}
	public CreateOrderInfoBean setOrder_type(String order_type) {
		this.order_type = order_type;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public CreateOrderInfoBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getAddress_id() {
		return address_id;
	}
	public CreateOrderInfoBean setAddress_id(Integer address_id) {
		this.address_id = address_id;
		return this;
	}
	
	public String getCar_ids() {
		return car_ids;
	}
	public CreateOrderInfoBean setCar_ids(String car_ids) {
		this.car_ids = car_ids;
		return this;
	}
	public Integer getIs_deduct_integral() {
		return is_deduct_integral;
	}
	public CreateOrderInfoBean setIs_deduct_integral(Integer is_deduct_integral) {
		this.is_deduct_integral = is_deduct_integral;
		return this;
	}
	public Integer getMember_coupon_id() {
		return member_coupon_id;
	}
	public CreateOrderInfoBean setMember_coupon_id(Integer member_coupon_id) {
		this.member_coupon_id = member_coupon_id;
		return this;
	}
	public List<OrderBean> getOrderBeans() {
		return orderBeans;
	}
	public CreateOrderInfoBean setOrderBeans(List<OrderBean> orderBeans) {
		this.orderBeans = orderBeans;
		return this;
	}
	
}

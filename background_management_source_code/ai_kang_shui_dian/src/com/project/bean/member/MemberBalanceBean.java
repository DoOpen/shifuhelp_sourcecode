package com.project.bean.member;

public class MemberBalanceBean {
	private Integer balance_id;
	private Integer member_id;//
	private Integer distributor_id;//
	private String balance_state;//
	private String balance_type;//
	private Float balance_value;//
	private String percent_value;//
	private Integer order_id;//
	private String order_no;//
	private Integer order_goods_id;
	private Float order_actual_price;
	private Float refund_price;
	private String is_delete;//
	private String create_time;//
	private String update_time;//
	public Integer getBalance_id() {
		return balance_id;
	}
	public MemberBalanceBean setBalance_id(Integer balance_id) {
		this.balance_id = balance_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public MemberBalanceBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getDistributor_id() {
		return distributor_id;
	}
	public MemberBalanceBean setDistributor_id(Integer distributor_id) {
		this.distributor_id = distributor_id;
		return this;
	}
	public String getBalance_state() {
		return balance_state;
	}
	public MemberBalanceBean setBalance_state(String balance_state) {
		this.balance_state = balance_state;
		return this;
	}
	public String getBalance_type() {
		return balance_type;
	}
	public MemberBalanceBean setBalance_type(String balance_type) {
		this.balance_type = balance_type;
		return this;
	}
	public Float getBalance_value() {
		return balance_value;
	}
	public MemberBalanceBean setBalance_value(Float balance_value) {
		this.balance_value = balance_value;
		return this;
	}
	public String getPercent_value() {
		return percent_value;
	}
	public MemberBalanceBean setPercent_value(String percent_value) {
		this.percent_value = percent_value;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public MemberBalanceBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public MemberBalanceBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public Integer getOrder_goods_id() {
		return order_goods_id;
	}
	public MemberBalanceBean setOrder_goods_id(Integer order_goods_id) {
		this.order_goods_id = order_goods_id;
		return this;
	}
	public Float getOrder_actual_price() {
		return order_actual_price;
	}
	public MemberBalanceBean setOrder_actual_price(Float order_actual_price) {
		this.order_actual_price = order_actual_price;
		return this;
	}
	public Float getRefund_price() {
		return refund_price;
	}
	public MemberBalanceBean setRefund_price(Float refund_price) {
		this.refund_price = refund_price;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public MemberBalanceBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MemberBalanceBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public MemberBalanceBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}

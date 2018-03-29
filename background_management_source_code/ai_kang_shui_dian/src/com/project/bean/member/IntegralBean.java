package com.project.bean.member;

public class IntegralBean {
	private Integer integral_id;
	private String member_id;
	private String integral_value;
	private String is_delete;
	private String integral_type;
	private String integral_type_show;
	private String create_time;
	private String relation_id;
	private String deduction;
	private String state;
	
	public String getDeduction() {
		return deduction;
	}
	public IntegralBean setDeduction(String deduction) {
		this.deduction = deduction;
		return this;
	}
	public String getState() {
		return state;
	}
	public IntegralBean setState(String state) {
		this.state = state;
		return this;
	}
	public String getIntegral_type_show() {
		return integral_type_show;
	}
	public IntegralBean setIntegral_type_show(String integral_type_show) {
		this.integral_type_show = integral_type_show;
		return this;
	}
	public Integer getIntegral_id() {
		return integral_id;
	}
	public IntegralBean setIntegral_id(Integer integral_id) {
		this.integral_id = integral_id;
		return this;
	}
	public String getMember_id() {
		return member_id;
	}
	public IntegralBean setMember_id(String member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getIntegral_value() {
		return integral_value;
	}
	public IntegralBean setIntegral_value(String integral_value) {
		this.integral_value = integral_value;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public IntegralBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getIntegral_type() {
		return integral_type;
	}
	public IntegralBean setIntegral_type(String integral_type) {
		this.integral_type = integral_type;
		if("sign".equals(integral_type)){
			this.integral_type_show="签到获得";
		}else if("order_add".equals(integral_type)){
			this.integral_type_show=("订单获得");
		}else if("order_cut".equals(integral_type)){
			this.integral_type_show="退单返回";
		}else if("share".equals(integral_type)){
			this.integral_type_show="分享获得";
		}else if("order_use".equals(integral_type)){
			this.integral_type_show="订单使用";
		}else {
			this.integral_type_show="未知";
		}
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public IntegralBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getRelation_id() {
		return relation_id;
	}
	public IntegralBean setRelation_id(String relation_id) {
		this.relation_id = relation_id;
		return this;
	}	
}

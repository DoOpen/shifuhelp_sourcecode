package com.project.bean.member;

/**
 * 积分增减记录
 * @author 彭方林
 * @date 2018年4月2日
 */
public class MemberIntegralBean {
	private Integer integral_id;
	private Integer member_id;//用户id
	private Integer integral_value;//积分增减数值
	private String integral_type;//register_recommend:注册推荐人 work_order_recommend:工单预约推荐  reported:报备 register:注册 sign:签到
	private String integral_type_show;
	private Integer relation_id;//获取方式关联id
	private String state;//add获取 use使用
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String update_time;//修改时间
	public String getState() {
		return state;
	}
	public MemberIntegralBean setState(String state) {
		this.state = state;
		return this;
	}
	public String getIntegral_type_show() {
		return integral_type_show;
	}
	public MemberIntegralBean setIntegral_type_show(String integral_type_show) {
		this.integral_type_show = integral_type_show;
		return this;
	}
	public Integer getIntegral_id() {
		return integral_id;
	}
	public MemberIntegralBean setIntegral_id(Integer integral_id) {
		this.integral_id = integral_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public MemberIntegralBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getIntegral_value() {
		return integral_value;
	}
	public MemberIntegralBean setIntegral_value(Integer integral_value) {
		this.integral_value = integral_value;
		return this;
	}
	public String getIntegral_type() {
		return integral_type;
	}
	public MemberIntegralBean setIntegral_type(String integral_type) {
		this.integral_type = integral_type;
		this.integral_type_show="register_recommend".equals(integral_type)?"注册推荐":
			"work_order_recommend".equals(integral_type)?"工单预约推荐":
				"reported".equals(integral_type)?"信息报备":
				"sign".equals(integral_type)?"签到":
					"goods_buy".equals(integral_type)?"商品兑换":"未知";
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public MemberIntegralBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MemberIntegralBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public MemberIntegralBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public Integer getRelation_id() {
		return relation_id;
	}
	public MemberIntegralBean setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
		return this;
	}
}

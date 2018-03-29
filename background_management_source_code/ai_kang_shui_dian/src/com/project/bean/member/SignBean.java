package com.project.bean.member;

public class SignBean {
	private Integer sign_id;
	private Integer member_id;
	private String create_time;
	private Integer sign_integral;//签到赠送积分
	private Integer sign_continuity_count_month;//一个月内 连续签到次数
	private Integer sign_total_count_month;//一个月内 总共签到次数

	
	public Integer getSign_continuity_count_month() {
		return sign_continuity_count_month;
	}
	public SignBean setSign_continuity_count_month(Integer sign_continuity_count_month) {
		this.sign_continuity_count_month = sign_continuity_count_month;
		return this;
	}
	public Integer getSign_total_count_month() {
		return sign_total_count_month;
	}
	public SignBean setSign_total_count_month(Integer sign_total_count_month) {
		this.sign_total_count_month = sign_total_count_month;
		return this;
	}
	
	public Integer getSign_integral() {
		return sign_integral;
	}
	public SignBean setSign_integral(Integer sign_integral) {
		this.sign_integral = sign_integral;
		return this;
	}
	public Integer getSign_id() {
		return sign_id;
	}
	public SignBean setSign_id(Integer sign_id) {
		this.sign_id = sign_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public SignBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public SignBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
}

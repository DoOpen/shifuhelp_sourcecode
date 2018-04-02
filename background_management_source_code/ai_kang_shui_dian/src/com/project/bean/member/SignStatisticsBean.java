package com.project.bean.member;

import com.project.utils.TimeUtils;
/**
 * 签到状态
 * @author 彭方林
 * @date 2018年4月2日
 */
public class SignStatisticsBean {
	private Integer sign_continuity_count_month;//月连续签到次数
	private Integer sign_total_count_month;//当月签到次数
	private Integer sign_integral;//签到获取积分
	private Integer sign_leak_count;//当月漏签次数
	
	public Integer getSign_leak_count() {
		return sign_leak_count;
	}
	public SignStatisticsBean setSign_leak_count(Integer sign_leak_count) {
		this.sign_leak_count = sign_leak_count;
		return this;
	}
	public int getSign_continuity_count_month() {
		return sign_continuity_count_month;
	}
	public SignStatisticsBean setSign_continuity_count_month(Integer sign_continuity_count_month) {
		this.sign_continuity_count_month = sign_continuity_count_month;
		return this;
	}
	public Integer getSign_total_count_month() {
		return sign_total_count_month;
	}
	public SignStatisticsBean setSign_total_count_month(Integer sign_total_count_month) {
		this.sign_total_count_month = sign_total_count_month;
		int a=TimeUtils.getDayWithMonth()-sign_total_count_month;
		this.sign_leak_count=a>0?a:0;
		return this;
	}
	public Integer getSign_integral() {
		return sign_integral;
	}
	public SignStatisticsBean setSign_integral(Integer sign_integral) {
		this.sign_integral = sign_integral;
		return this;
	}
	
}

package com.project.bean.others;

import java.util.List;

/**
 * 意见反馈
 * @author 彭方林
 * @date 2018年4月2日
 */
public class AdviceBean {
	private Integer advice_id;
	private String advice_content;//建议内容
	private Integer member_id;//用户id
	private String create_time;//创建时间
	private String update_time;//修改时间
	private Integer is_delete;//1删除
	private String member_nick_name;//用户昵称
	private List<AdviceImgBean> adviceImgBeans;
	
	public String getMember_nick_name() {
		return member_nick_name;
	}
	public AdviceBean setMember_nick_name(String member_nick_name) {
		this.member_nick_name = member_nick_name;
		return this;
	}
	public List<AdviceImgBean> getAdviceImgBeans() {
		return adviceImgBeans;
	}
	public AdviceBean setAdviceImgBeans(List<AdviceImgBean> adviceImgBeans) {
		this.adviceImgBeans = adviceImgBeans;
		return this;
	}
	public Integer getAdvice_id() {
		return advice_id;
	}
	public AdviceBean setAdvice_id(Integer advice_id) {
		this.advice_id = advice_id;
		return this;
	}
	public String getAdvice_content() {
		return advice_content;
	}
	public AdviceBean setAdvice_content(String advice_content) {
		this.advice_content = advice_content;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public AdviceBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public AdviceBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public AdviceBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public AdviceBean setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
		return this;
	}
}

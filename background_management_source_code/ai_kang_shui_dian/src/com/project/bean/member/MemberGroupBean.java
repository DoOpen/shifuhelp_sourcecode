package com.project.bean.member;

import java.util.List;

import com.project.bean.order.OrderBean;
/**
 * 团购信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class MemberGroupBean {
	private Integer member_group_id;
	private Integer member_id;//用户id
	private Integer goods_id;//商品id
	private Integer goods_group_id;//商品关联规格
	private Integer group_need_count;//需要人数
	private Integer group_now_count;//现在人数
	private Integer group_need_time;//团购等待时间（单位分钟）
	private String group_state;//0:未支付 1:支付 2:满了 3:过期了
	private String end_time;//团购等待结束时间
	private String end_time_mill;//团购等待结束时间 毫秒数
	private String qrcode_img;//二维码
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String update_time;//修改时间
	private MemberBean memberBean;
	private OrderBean orderBean;
	private List<MemberBean> memberBeans;
	public Integer getMember_group_id() {
		return member_group_id;
	}
	public MemberGroupBean setMember_group_id(Integer member_group_id) {
		this.member_group_id = member_group_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public MemberGroupBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public MemberGroupBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public Integer getGoods_group_id() {
		return goods_group_id;
	}
	public MemberGroupBean setGoods_group_id(Integer goods_group_id) {
		this.goods_group_id = goods_group_id;
		return this;
	}
	public Integer getGroup_need_count() {
		return group_need_count;
	}
	public MemberGroupBean setGroup_need_count(Integer group_need_count) {
		this.group_need_count = group_need_count;
		return this;
	}
	public Integer getGroup_now_count() {
		return group_now_count;
	}
	public MemberGroupBean setGroup_now_count(Integer group_now_count) {
		this.group_now_count = group_now_count;
		return this;
	}
	public Integer getGroup_need_time() {
		return group_need_time;
	}
	public MemberGroupBean setGroup_need_time(Integer group_need_time) {
		this.group_need_time = group_need_time;
		return this;
	}
	public String getGroup_state() {
		return group_state;
	}
	public MemberGroupBean setGroup_state(String group_state) {
		this.group_state = group_state;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public MemberGroupBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
	public String getEnd_time_mill() {
		return end_time_mill;
	}
	public MemberGroupBean setEnd_time_mill(String end_time_mill) {
		this.end_time_mill = end_time_mill;
		return this;
	}
	public String getQrcode_img() {
		return qrcode_img;
	}
	public MemberGroupBean setQrcode_img(String qrcode_img) {
		this.qrcode_img = qrcode_img;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public MemberGroupBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MemberGroupBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public MemberGroupBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public MemberGroupBean setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
		return this;
	}
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public MemberGroupBean setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
		return this;
	}
	public List<MemberBean> getMemberBeans() {
		return memberBeans;
	}
	public MemberGroupBean setMemberBeans(List<MemberBean> memberBeans) {
		this.memberBeans = memberBeans;
		return this;
	} 
	
}

package com.project.bean.member;

public class MemberReportedBean {
	private Integer reported_id;
	private Integer member_id;
	private String member_account;
	private String member_real_name;
	private String reported_name;
	private String reported_phone;
	private String province;
	private String city;
	private String district;
	private String detail;
	private Integer is_delete;
	private String reported_img1;
	private String reported_img2;
	private String reported_img3;
	private String create_time;
	private String update_time;
	private String reported_state;
	private String refuse_note;
	private String reported_note;
	private String reported_state_show;
	public String getReported_note() {
		return reported_note;
	}
	public MemberReportedBean setReported_note(String reported_note) {
		this.reported_note = reported_note;
		return this;
	}
	public String getRefuse_note() {
		return refuse_note;
	}
	public MemberReportedBean setRefuse_note(String refuse_note) {
		this.refuse_note = refuse_note;
		return this;
	}
	public String getMember_account() {
		return member_account;
	}
	public MemberReportedBean setMember_account(String member_account) {
		this.member_account = member_account;
		return this;
	}
	public String getMember_real_name() {
		return member_real_name;
	}
	public MemberReportedBean setMember_real_name(String member_real_name) {
		this.member_real_name = member_real_name;
		return this;
	}
	public String getReported_state_show() {
		return reported_state_show;
	}
	public MemberReportedBean setReported_state_show(String reported_state_show) {
		this.reported_state_show = reported_state_show;
		return this;
	}
	public String getReported_state() {
		return reported_state;
	}
	public MemberReportedBean setReported_state(String reported_state) {
		this.reported_state = reported_state;
		if("wait_audit".equals(reported_state)) {
			this.reported_state_show="待审核";
		}else if("accept".equals(reported_state)) {
			this.reported_state_show="已受理";
		}else if("success".equals(reported_state)) {
			this.reported_state_show="成功";
		}else if("refuse".equals(reported_state)) {
			this.reported_state_show="拒绝";
		}
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public MemberReportedBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getReported_id() {
		return reported_id;
	}
	public MemberReportedBean setReported_id(Integer reported_id) {
		this.reported_id = reported_id;
		return this;
	}
	public String getReported_name() {
		return reported_name;
	}
	public MemberReportedBean setReported_name(String reported_name) {
		this.reported_name = reported_name;
		return this;
	}
	public String getReported_phone() {
		return reported_phone;
	}
	public MemberReportedBean setReported_phone(String reported_phone) {
		this.reported_phone = reported_phone;
		return this;
	}
	public String getProvince() {
		return province;
	}
	public MemberReportedBean setProvince(String province) {
		this.province = province;
		return this;
	}
	public String getCity() {
		return city;
	}
	public MemberReportedBean setCity(String city) {
		this.city = city;
		return this;
	}
	public String getDistrict() {
		return district;
	}
	public MemberReportedBean setDistrict(String district) {
		this.district = district;
		return this;
	}
	public String getDetail() {
		return detail;
	}
	public MemberReportedBean setDetail(String detail) {
		this.detail = detail;
		return this;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public MemberReportedBean setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getReported_img1() {
		return reported_img1;
	}
	public MemberReportedBean setReported_img1(String reported_img1) {
		this.reported_img1 = reported_img1;
		return this;
	}
	public String getReported_img2() {
		return reported_img2;
	}
	public MemberReportedBean setReported_img2(String reported_img2) {
		this.reported_img2 = reported_img2;
		return this;
	}
	public String getReported_img3() {
		return reported_img3;
	}
	public MemberReportedBean setReported_img3(String reported_img3) {
		this.reported_img3 = reported_img3;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MemberReportedBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public MemberReportedBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}

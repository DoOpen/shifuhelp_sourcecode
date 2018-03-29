package com.project.bean.member;

import java.util.List;

public class MemberBean {
	private Integer member_id;
	private String member_account;
	private String member_password;
	private String member_token;
	private String member_real_name;
	private String member_nick_name;
	private String member_phone;
	private String id_number;//身份证号
	private String special_skill;//特殊技能
	private String recommend_phone;//推荐人手机号
	private String custome_refuse_note;//客服审核拒绝原因
	private String member_type;
	private String member_create_time;
	private String member_update_time;
	private String member_is_delete;
	private String member_head_image;
	private String member_sex;
	private String member_age;
	private String member_work_type;
	private String member_work_age;
	private String member_state;
	private Integer member_integral;
	private String member_bank_name;
	private String member_bank_open_name;
	private String member_bank__user_name;
	private String member_bank_code;
	private String member_bank_phone;
	private String member_certificate;
	private String member_certificate_content;
	private Float member_freeze_money;
	private Float member_extract_money;
	private Float member_deposit_money;
	private String member_alipay;
	private String member_alipay_real_name;
	private String member_we_chat;
	private String member_we_chat_real_name;
	private String member_open_id;
	private String member_service_name;
	private String member_service_phone;
	private String member_service_country;
	private String member_service_province;
	private String member_service_city;
	private String member_service_district;
	private String member_service_detail;
	private String member_qrcode_img;
	private String member_pay_password;
	private Integer merchants_account_id;
	private String member_is_star;
	private String member_is_star_show;
	private String month_time;
	private String month_integral;
	private String member_service_longitude;
	private String member_service_latitude;
	private Integer member_service_number;
	private Integer member_service_good_number;
	private String member_good_rate;
	private String nead_deposit;
	private String is_sign;
	private String member_old_password;
	private String star_worker_info;
	private String star_worker_info_content;
	private String member_state_show;
	private String customer_note;
	private String is_disable;
	private String is_disable_show;
	private String disable_note;
	private String district;
	private String audit_pass_time;//客服审核通过时间
	private Float assessment_star1;//服务满意度
	private Float assessment_star2;//速度满意度
	private Float assessment_star3;//做工满意度
	private String process_show;//工艺展示
	private String process_show_content;
	private String distance;//距离
	private String last_update_head_img_time;//上次更新头像时间
	private List<AddressBean> addressBeans;
	
	public String getLast_update_head_img_time() {
		return last_update_head_img_time;
	}
	public MemberBean setLast_update_head_img_time(String last_update_head_img_time) {
		this.last_update_head_img_time = last_update_head_img_time;
		return this;
	}
	public String getDistance() {
		return distance;
	}
	public MemberBean setDistance(String distance) {
		this.distance = distance;
		return this;
	}
	public String getProcess_show() {
		return process_show;
	}
	public MemberBean setProcess_show(String process_show) {
		this.process_show = process_show;
		return this;
	}
	public String getProcess_show_content() {
		return process_show_content;
	}
	public MemberBean setProcess_show_content(String process_show_content) {
		this.process_show_content = process_show_content;
		return this;
	}
	public Float getAssessment_star1() {
		return assessment_star1;
	}
	public MemberBean setAssessment_star1(Float assessment_star1) {
		this.assessment_star1 = assessment_star1;
		return this;
	}
	public Float getAssessment_star2() {
		return assessment_star2;
	}
	public MemberBean setAssessment_star2(Float assessment_star2) {
		this.assessment_star2 = assessment_star2;
		return this;
	}
	public Float getAssessment_star3() {
		return assessment_star3;
	}
	public MemberBean setAssessment_star3(Float assessment_star3) {
		this.assessment_star3 = assessment_star3;
		return this;
	}
	public String getAudit_pass_time() {
		return audit_pass_time;
	}
	public MemberBean setAudit_pass_time(String audit_pass_time) {
		this.audit_pass_time = audit_pass_time;
		return this;
	}
	public String getMember_certificate_content() {
		return member_certificate_content;
	}
	public MemberBean setMember_certificate_content(String member_certificate_content) {
		this.member_certificate_content = member_certificate_content;
		return this;
	}
	public String getStar_worker_info_content() {
		return star_worker_info_content;
	}
	public MemberBean setStar_worker_info_content(String star_worker_info_content) {
		this.star_worker_info_content = star_worker_info_content;
		return this;
	}
	public String getIs_disable_show() {
		return is_disable_show;
	}
	public MemberBean setIs_disable_show(String is_disable_show) {
		this.is_disable_show = is_disable_show;
		return this;
	}
	public String getCustome_refuse_note() {
		return custome_refuse_note;
	}
	public MemberBean setCustome_refuse_note(String custome_refuse_note) {
		this.custome_refuse_note = custome_refuse_note;
		return this;
	}
	public String getId_number() {
		return id_number;
	}
	public MemberBean setId_number(String id_number) {
		this.id_number = id_number;
		return this;
	}
	public String getSpecial_skill() {
		return special_skill;
	}
	public MemberBean setSpecial_skill(String special_skill) {
		this.special_skill = special_skill;
		return this;
	}
	public String getRecommend_phone() {
		return recommend_phone;
	}
	public MemberBean setRecommend_phone(String recommend_phone) {
		this.recommend_phone = recommend_phone;
		return this;
	}
	public List<AddressBean> getAddressBeans() {
		return addressBeans;
	}
	public MemberBean setAddressBeans(List<AddressBean> addressBeans) {
		this.addressBeans = addressBeans;
		return this;
	}
	public String getMember_is_star_show() {
		return member_is_star_show;
	}
	public MemberBean setMember_is_star_show(String member_is_star_show) {
		this.member_is_star_show = member_is_star_show;
		return this;
	}
	public String getDisable_note() {
		return disable_note;
	}
	public MemberBean setDisable_note(String disable_note) {
		this.disable_note = disable_note;
		return this;
	}
	public String getIs_disable() {
		return is_disable;
	}
	public MemberBean setIs_disable(String is_disable) {
		this.is_disable = is_disable;
		this.is_disable_show="0".equals(is_disable)?"启用":
			"1".equals(is_disable)?"冻结":"未知";
		return this;
	}
	public String getCustomer_note() {
		return customer_note;
	}
	public MemberBean setCustomer_note(String customer_note) {
		this.customer_note = customer_note;
		return this;
	}
	public String getMember_state_show() {
		return member_state_show;
	}
	public MemberBean setMember_state_show(String member_state_show) {
		this.member_state_show = member_state_show;
		return this;
	}
	public String getStar_worker_info() {
		return star_worker_info;
	}
	public MemberBean setStar_worker_info(String star_worker_info) {
		this.star_worker_info = star_worker_info;
		return this;
	}
	public String getMember_we_chat_real_name() {
		return member_we_chat_real_name;
	}
	public MemberBean setMember_we_chat_real_name(String member_we_chat_real_name) {
		this.member_we_chat_real_name = member_we_chat_real_name;
		return this;
	}
	public String getMember_alipay_real_name() {
		return member_alipay_real_name;
	}
	public MemberBean setMember_alipay_real_name(String member_alipay_real_name) {
		this.member_alipay_real_name = member_alipay_real_name;
		return this;
	}
	public String getMember_old_password() {
		return member_old_password;
	}
	public MemberBean setMember_old_password(String member_old_password) {
		this.member_old_password = member_old_password;
		return this;
	}
	public String getIs_sign() {
		return is_sign;
	}
	public MemberBean setIs_sign(String is_sign) {
		this.is_sign = is_sign;
		return this;
	}
	public String getNead_deposit() {
		return nead_deposit;
	}
	public MemberBean setNead_deposit(String nead_deposit) {
		this.nead_deposit = nead_deposit;
		return this;
	}
	public String getMember_good_rate() {
		return member_good_rate;
	}
	public MemberBean setMember_good_rate(String member_good_rate) {
		this.member_good_rate=member_good_rate;
		return this;
	}
	public Integer getMember_service_number() {
		return member_service_number;
	}
	public MemberBean setMember_service_number(Integer member_service_number) {
		this.member_service_number = member_service_number;
		return this;
	}
	public Integer getMember_service_good_number() {
		return member_service_good_number;
	}
	public MemberBean setMember_service_good_number(Integer member_service_good_number) {
		this.member_service_good_number = member_service_good_number;
		return this;
	}
	public String getMember_service_longitude() {
		return member_service_longitude;
	}
	public MemberBean setMember_service_longitude(String member_service_longitude) {
		this.member_service_longitude = member_service_longitude;
		return this;
	}
	public String getMember_service_latitude() {
		return member_service_latitude;
	}
	public MemberBean setMember_service_latitude(String member_service_latitude) {
		this.member_service_latitude = member_service_latitude;
		return this;
	}
	public String getMonth_time() {
		return month_time;
	}
	public MemberBean setMonth_time(String month_time) {
		this.month_time = month_time;
		return this;
	}
	public String getMonth_integral() {
		return month_integral;
	}
	public MemberBean setMonth_integral(String month_integral) {
		this.month_integral = month_integral;
		return this;
	}
	public String getMember_is_star() {
		return member_is_star;
	}
	public MemberBean setMember_is_star(String member_is_star) {
		this.member_is_star = member_is_star;
		this.member_is_star_show="0".equals(member_is_star)?"否":"是";
		return this;
	}
	public String getMember_bank_open_name() {
		return member_bank_open_name;
	}
	public MemberBean setMember_bank_open_name(String member_bank_open_name) {
		this.member_bank_open_name = member_bank_open_name;
		return this;
	}
	public Integer getMerchants_account_id() {
		return merchants_account_id;
	}
	public MemberBean setMerchants_account_id(Integer merchants_account_id) {
		this.merchants_account_id = merchants_account_id;
		return this;
	}
	public String getMember_pay_password() {
		return member_pay_password;
	}
	public MemberBean setMember_pay_password(String member_pay_password) {
		this.member_pay_password = member_pay_password;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public MemberBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getMember_account() {
		return member_account;
	}
	public MemberBean setMember_account(String member_account) {
		this.member_account = member_account;
		return this;
	}
	public String getMember_token() {
		return member_token;
	}
	public MemberBean setMember_token(String member_token) {
		this.member_token = member_token;
		return this;
	}
	public String getMember_password() {
		return member_password;
	}
	public MemberBean setMember_password(String member_password) {
		this.member_password = member_password;
		return this;
	}
	public String getMember_real_name() {
		return member_real_name;
	}
	public MemberBean setMember_real_name(String member_real_name) {
		this.member_real_name = member_real_name;
		return this;
	}
	public String getMember_nick_name() {
		return member_nick_name;
	}
	public MemberBean setMember_nick_name(String member_nick_name) {
		this.member_nick_name = member_nick_name;
		return this;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public MemberBean setMember_phone(String member_phone) {
		this.member_phone = member_phone;
		return this;
	}
	public String getMember_type() {
		return member_type;
	}
	public MemberBean setMember_type(String member_type) {
		this.member_type = member_type;
		return this;
	}
	public String getMember_create_time() {
		return member_create_time;
	}
	public MemberBean setMember_create_time(String member_create_time) {
		this.member_create_time = member_create_time;
		return this;
	}
	public String getMember_update_time() {
		return member_update_time;
	}
	public MemberBean setMember_update_time(String member_update_time) {
		this.member_update_time = member_update_time;
		return this;
	}
	public String getMember_is_delete() {
		return member_is_delete;
	}
	public MemberBean setMember_is_delete(String member_is_delete) {
		this.member_is_delete = member_is_delete;
		return this;
	}
	public String getMember_head_image() {
		return member_head_image;
	}
	public MemberBean setMember_head_image(String member_head_image) {
		this.member_head_image = member_head_image;
		return this;
	}
	public String getMember_sex() {
		return member_sex;
	}
	public MemberBean setMember_sex(String member_sex) {
		this.member_sex = member_sex;
		return this;
	}
	public String getMember_age() {
		return member_age;
	}
	public MemberBean setMember_age(String member_age) {
		this.member_age = member_age;
		return this;
	}
	public String getMember_work_type() {
		return member_work_type;
	}
	public MemberBean setMember_work_type(String member_work_type) {
		this.member_work_type = member_work_type;
		return this;
	}
	public String getMember_work_age() {
		return member_work_age;
	}
	public MemberBean setMember_work_age(String member_work_age) {
		this.member_work_age = member_work_age;
		return this;
	}
	public String getMember_state() {
		return member_state;
	}
	public MemberBean setMember_state(String member_state) {
		this.member_state = member_state;
		if("-1".equals(member_state)){
			this.member_state_show="未审核";
		}else if("0".equals(member_state)){
			this.member_state_show="审核中";
		}else if("1".equals(member_state)){
			this.member_state_show="审核通过";
		}else{
			this.member_state_show="未知";
		}
		return this;
	}
	public Integer getMember_integral() {
		return member_integral;
	}
	public MemberBean setMember_integral(Integer member_integral) {
		this.member_integral = member_integral;
		return this;
	}
	public String getMember_bank_name() {
		return member_bank_name;
	}
	public MemberBean setMember_bank_name(String member_bank_name) {
		this.member_bank_name = member_bank_name;
		return this;
	}
	public String getMember_bank__user_name() {
		return member_bank__user_name;
	}
	public MemberBean setMember_bank__user_name(String member_bank__user_name) {
		this.member_bank__user_name = member_bank__user_name;
		return this;
	}
	public String getMember_bank_code() {
		return member_bank_code;
	}
	public MemberBean setMember_bank_code(String member_bank_code) {
		this.member_bank_code = member_bank_code;
		return this;
	}
	public String getMember_bank_phone() {
		return member_bank_phone;
	}
	public MemberBean setMember_bank_phone(String member_bank_phone) {
		this.member_bank_phone = member_bank_phone;
		return this;
	}
	public String getMember_certificate() {
		return member_certificate;
	}
	public MemberBean setMember_certificate(String member_certificate) {
		this.member_certificate = member_certificate;
		return this;
	}
	public Float getMember_freeze_money() {
		return member_freeze_money;
	}
	public MemberBean setMember_freeze_money(Float member_freeze_money) {
		this.member_freeze_money = member_freeze_money;
		return this;
	}
	public Float getMember_extract_money() {
		return member_extract_money;
	}
	public MemberBean setMember_extract_money(Float member_extract_money) {
		this.member_extract_money = member_extract_money;
		return this;
	}
	public Float getMember_deposit_money() {
		return member_deposit_money;
	}
	public MemberBean setMember_deposit_money(Float member_deposit_money) {
		this.member_deposit_money = member_deposit_money;
		return this;
	}
	public String getMember_alipay() {
		return member_alipay;
	}
	public MemberBean setMember_alipay(String member_alipay) {
		this.member_alipay = member_alipay;
		return this;
	}
	public String getMember_we_chat() {
		return member_we_chat;
	}
	public MemberBean setMember_we_chat(String member_we_chat) {
		this.member_we_chat = member_we_chat;
		return this;
	}
	public String getMember_open_id() {
		return member_open_id;
	}
	public MemberBean setMember_open_id(String member_open_id) {
		this.member_open_id = member_open_id;
		return this;
	}
	public String getMember_service_name() {
		return member_service_name;
	}
	public MemberBean setMember_service_name(String member_service_name) {
		this.member_service_name = member_service_name;
		return this;
	}
	public String getMember_service_phone() {
		return member_service_phone;
	}
	public MemberBean setMember_service_phone(String member_service_phone) {
		this.member_service_phone = member_service_phone;
		return this;
	}
	public String getMember_service_country() {
		return member_service_country;
	}
	public MemberBean setMember_service_country(String member_service_country) {
		this.member_service_country = member_service_country;
		return this;
	}
	public String getMember_service_province() {
		return member_service_province;
	}
	public MemberBean setMember_service_province(String member_service_province) {
		this.member_service_province = member_service_province;
		return this;
	}
	public String getMember_service_city() {
		return member_service_city;
	}
	public MemberBean setMember_service_city(String member_service_city) {
		this.member_service_city = member_service_city;
		return this;
	}
	public String getMember_service_district() {
		return member_service_district;
	}
	public MemberBean setMember_service_district(String member_service_district) {
		this.member_service_district = member_service_district;
		return this;
	}
	public String getMember_service_detail() {
		return member_service_detail;
	}
	public MemberBean setMember_service_detail(String member_service_detail) {
		this.member_service_detail = member_service_detail;
		return this;
	}
	public String getMember_qrcode_img() {
		return member_qrcode_img;
	}
	public MemberBean setMember_qrcode_img(String member_qrcode_img) {
		this.member_qrcode_img = member_qrcode_img;
		return this;
	}
	public String getDistrict() {
		return district;
	}
	public MemberBean setDistrict(String district) {
		this.district = district;
		return this;
	}
	
}

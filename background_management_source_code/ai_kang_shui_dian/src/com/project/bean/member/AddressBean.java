package com.project.bean.member;

/**
 * 收货地址
 * @author 彭方林
 * @date 2018年4月2日
 */
public class AddressBean {
	private Integer address_id;
	private Integer member_id;//用户id
	private String address_flag;//地址标签
	private String address_mobile;//收货人手机号
	private String address_name;//收货人姓名
	private String address_province;//省
	private String address_city;//市
	private String address_district;//区
	private String address_detail;//详细地址
	private String address_zip_code;//邮编
	private String address_longitude;//经度
	private String address_latitude;//维度
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String is_default;//1默认地址
	private String is_delete;//1删除
	public Integer getAddress_id() {
		return address_id;
	}
	public AddressBean setAddress_id(Integer address_id) {
		this.address_id = address_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public AddressBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getAddress_flag() {
		return address_flag;
	}
	public AddressBean setAddress_flag(String address_flag) {
		this.address_flag = address_flag;
		return this;
	}
	public String getAddress_mobile() {
		return address_mobile;
	}
	public AddressBean setAddress_mobile(String address_mobile) {
		this.address_mobile = address_mobile;
		return this;
	}
	public String getAddress_name() {
		return address_name;
	}
	public AddressBean setAddress_name(String address_name) {
		this.address_name = address_name;
		return this;
	}
	public String getAddress_province() {
		return address_province;
	}
	public AddressBean setAddress_province(String address_province) {
		this.address_province = address_province;
		return this;
	}
	public String getAddress_city() {
		return address_city;
	}
	public AddressBean setAddress_city(String address_city) {
		this.address_city = address_city;
		return this;
	}
	public String getAddress_district() {
		return address_district;
	}
	public AddressBean setAddress_district(String address_district) {
		this.address_district = address_district;
		return this;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public AddressBean setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
		return this;
	}
	public String getAddress_zip_code() {
		return address_zip_code;
	}
	public AddressBean setAddress_zip_code(String address_zip_code) {
		this.address_zip_code = address_zip_code;
		return this;
	}
	public String getAddress_longitude() {
		return address_longitude;
	}
	public AddressBean setAddress_longitude(String address_longitude) {
		this.address_longitude = address_longitude;
		return this;
	}
	public String getAddress_latitude() {
		return address_latitude;
	}
	public AddressBean setAddress_latitude(String address_latitude) {
		this.address_latitude = address_latitude;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public AddressBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public AddressBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_default() {
		return is_default;
	}
	public AddressBean setIs_default(String is_default) {
		this.is_default = is_default;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public AddressBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
}

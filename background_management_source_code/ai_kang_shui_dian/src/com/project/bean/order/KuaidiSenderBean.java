package com.project.bean.order;

public class KuaidiSenderBean {
	private String Name;
	private String Mobile;
	private String ProvinceName;
	private String CityName;
	private String ExpAreaName;
	private String Address;
	private String PostCode;

	public String getPostCode() {
		return PostCode;
	}
	public KuaidiSenderBean setPostCode(String postCode) {
		PostCode = postCode;
		return this;
	}
	public String getName() {
		return Name;
	}
	public KuaidiSenderBean setName(String name) {
		Name = name;
		return this;
	}
	public String getMobile() {
		return Mobile;
	}
	public KuaidiSenderBean setMobile(String mobile) {
		Mobile = mobile;
		return this;
	}
	public String getProvinceName() {
		return ProvinceName;
	}
	public KuaidiSenderBean setProvinceName(String provinceName) {
		ProvinceName = provinceName;
		return this;
	}
	public String getCityName() {
		return CityName;
	}
	public KuaidiSenderBean setCityName(String cityName) {
		CityName = cityName;
		return this;
	}
	public String getExpAreaName() {
		return ExpAreaName;
	}
	public KuaidiSenderBean setExpAreaName(String expAreaName) {
		ExpAreaName = expAreaName;
		return this;
	}
	public String getAddress() {
		return Address;
	}
	public KuaidiSenderBean setAddress(String address) {
		Address = address;
		return this;
	}
}

package com.project.bean.order;

/**
 * 快递鸟发件信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class KuaidiSenderBean {
	private String Name;//Name
	private String Mobile;//手机
	private String ProvinceName;//发件省（如广东省，不要缺少“省”）
	private String CityName;//发件市（如深圳市，不要缺少“市”）
	private String ExpAreaName;//发件区（如福田区，不要缺少“区”或“县”）
	private String Address;//发件人详细地址
	private String PostCode;//发件人邮编

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

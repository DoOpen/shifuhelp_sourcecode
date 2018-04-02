package com.project.bean.order;

/**
 * 快递鸟收件信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class KuaidiReceiverBean {
	private String Name;//收件人
	private String Mobile;//手机
	private String ProvinceName;//收件省 (如广东省，不要缺少“省”；如是直辖市，请直接传北京、上海等； 如是自治区，请直接传广西壮族自治区等)
	private String CityName;//收件市(如深圳市，不要缺少“市”)
	private String ExpAreaName;//收件区/县(如福田区，不要缺少“区”或“县”)
	private String Address;//收件人详细地址
	private String PostCode;//收件地邮编(ShipperCode为EMS、YZPY时必填)

	public String getPostCode() {
		return PostCode;
	}
	public KuaidiReceiverBean setPostCode(String postCode) {
		PostCode = postCode;
		return this;
	}
	public String getName() {
		return Name;
	}
	public KuaidiReceiverBean setName(String name) {
		Name = name;
		return this;
	}
	public String getMobile() {
		return Mobile;
	}
	public KuaidiReceiverBean setMobile(String mobile) {
		Mobile = mobile;
		return this;
	}
	public String getProvinceName() {
		return ProvinceName;
	}
	public KuaidiReceiverBean setProvinceName(String provinceName) {
		ProvinceName = provinceName;
		return this;
	}
	public String getCityName() {
		return CityName;
	}
	public KuaidiReceiverBean setCityName(String cityName) {
		CityName = cityName;
		return this;
	}
	public String getExpAreaName() {
		return ExpAreaName;
	}
	public KuaidiReceiverBean setExpAreaName(String expAreaName) {
		ExpAreaName = expAreaName;
		return this;
	}
	public String getAddress() {
		return Address;
	}
	public KuaidiReceiverBean setAddress(String address) {
		Address = address;
		return this;
	}
	
	
}

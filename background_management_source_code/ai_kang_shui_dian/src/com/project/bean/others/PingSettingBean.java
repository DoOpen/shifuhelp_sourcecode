package com.project.bean.others;

/**
 * ping++配置
 * @author 彭方林
 * @date 2018年4月2日
 */
public class PingSettingBean {
	private Integer ping_id;
	private String app_id;//appID
	private String app_key;//支付的正式key
	private String public_key;//公钥
	public Integer getPing_id() {
		return ping_id;
	}
	public PingSettingBean setPing_id(Integer ping_id) {
		this.ping_id = ping_id;
		return this;
	}
	public String getApp_id() {
		return app_id;
	}
	public PingSettingBean setApp_id(String app_id) {
		this.app_id = app_id;
		return this;
	}
	public String getApp_key() {
		return app_key;
	}
	public PingSettingBean setApp_key(String app_key) {
		this.app_key = app_key;
		return this;
	}
	public String getPublic_key() {
		return public_key;
	}
	public PingSettingBean setPublic_key(String public_key) {
		this.public_key = public_key;
		return this;
	}
}


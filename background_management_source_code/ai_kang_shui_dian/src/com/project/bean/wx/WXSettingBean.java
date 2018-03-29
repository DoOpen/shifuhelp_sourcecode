package com.project.bean.wx;

public class WXSettingBean {
	private Integer weixin_id;
	private String weixin_appid;
	private String weixin_secret;
	private String weixin_type;
	
	public String getWeixin_type() {
		return weixin_type;
	}
	public WXSettingBean setWeixin_type(String weixin_type) {
		this.weixin_type = weixin_type;
		return this;
	}
	public Integer getWeixin_id() {
		return weixin_id;
	}
	public WXSettingBean setWeixin_id(Integer weixin_id) {
		this.weixin_id = weixin_id;
		return this;
	}
	public String getWeixin_appid() {
		return weixin_appid;
	}
	public WXSettingBean setWeixin_appid(String weixin_appid) {
		this.weixin_appid = weixin_appid;
		return this;
	}
	public String getWeixin_secret() {
		return weixin_secret;
	}
	public WXSettingBean setWeixin_secret(String weixin_secret) {
		this.weixin_secret = weixin_secret;
		return this;
	}	
}

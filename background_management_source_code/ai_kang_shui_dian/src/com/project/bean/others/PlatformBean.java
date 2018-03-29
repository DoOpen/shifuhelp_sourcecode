package com.project.bean.others;

public class PlatformBean {
	private Integer platform_id;
	private String platform_url;
	private String platform_abbreviation;//公司缩写
	private String platform_type;
	private String platform_mobile;
	private String platform_weixin;
	
	public String getPlatform_mobile() {
		return platform_mobile;
	}
	public PlatformBean setPlatform_mobile(String platform_mobile) {
		this.platform_mobile = platform_mobile;
		return this;
	}
	public String getPlatform_weixin() {
		return platform_weixin;
	}
	public PlatformBean setPlatform_weixin(String platform_weixin) {
		this.platform_weixin = platform_weixin;
		return this;
	}
	public String getPlatform_abbreviation() {
		return platform_abbreviation;
	}
	public PlatformBean setPlatform_abbreviation(String platform_abbreviation) {
		this.platform_abbreviation = platform_abbreviation;
		return this;
	}
	public String getPlatform_type() {
		return platform_type;
	}
	public PlatformBean setPlatform_type(String platform_type) {
		this.platform_type = platform_type;
		return this;
	}
	public Integer getPlatform_id() {
		return platform_id;
	}
	public PlatformBean setPlatform_id(Integer platform_id) {
		this.platform_id = platform_id;
		return this;
	}
	public String getPlatform_url() {
		return platform_url;
	}
	public PlatformBean setPlatform_url(String platform_url) {
		this.platform_url = platform_url;
		return this;
	}
	
}

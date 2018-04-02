package com.project.bean.system;

/**
 * app版本信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class AppVersionBean {
	private Integer version_id;
	private String version_name;//版本名称
	private String version_type;//版本类型   android：安卓  ios:ios
	private String version_no;//版本号
	private String update_content;//更新内容
	private String download_address;//第三方下载地址
	private String server_address;//服务器下载地址
	private String must_update;//1强制更新
	private String update_time;//修改时间
	private String server_update;//1从服务器下载本次更新的版本
	public String getServer_update() {
		return server_update;
	}
	public AppVersionBean setServer_update(String server_update) {
		this.server_update = server_update;
		return this;
	}
	public Integer getVersion_id() {
		return version_id;
	}
	public AppVersionBean setVersion_id(Integer version_id) {
		this.version_id = version_id;
		return this;
	}
	public String getVersion_name() {
		return version_name;
	}
	public AppVersionBean setVersion_name(String version_name) {
		this.version_name = version_name;
		return this;
	}
	public String getVersion_type() {
		return version_type;
	}
	public AppVersionBean setVersion_type(String version_type) {
		this.version_type = version_type;
		return this;
	}
	public String getVersion_no() {
		return version_no;
	}
	public AppVersionBean setVersion_no(String version_no) {
		this.version_no = version_no;
		return this;
	}
	public String getUpdate_content() {
		return update_content;
	}
	public AppVersionBean setUpdate_content(String update_content) {
		this.update_content = update_content;
		return this;
	}
	public String getDownload_address() {
		return download_address;
	}
	public AppVersionBean setDownload_address(String download_address) {
		this.download_address = download_address;
		return this;
	}
	public String getServer_address() {
		return server_address;
	}
	public AppVersionBean setServer_address(String server_address) {
		this.server_address = server_address;
		return this;
	}
	public String getMust_update() {
		return must_update;
	}
	public AppVersionBean setMust_update(String must_update) {
		this.must_update = must_update;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public AppVersionBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}
